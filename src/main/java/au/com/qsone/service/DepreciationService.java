package au.com.qsone.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import au.com.qsone.web.dto.DepreciationAmount;
import au.com.qsone.web.dto.DepreciationRequest;
import au.com.qsone.web.dto.DepreciationResponse;
import au.com.qsone.web.dto.DepreciationResult;
import au.com.qsone.web.dto.DocumentRequest;

@Service
public class DepreciationService {

        @Autowired
        private DocumentService documentService;

        /**
         * Rules:
         * 
         * 1. Formulae to calclulate PC and DV.
         * 
         * Diminishing Value Method = Asset's Opening Value × (days held ÷ 365) × (200%
         * ÷ Asset’s effective life) Prime Cost Method = Asset's Opening Value × (days
         * held ÷ 365) × (100% ÷ Asset’s effective life)
         * 
         * Note: days held = no.of days the asset is held for that financial year.
         * 
         * 2. Year 1 is calculated by no.of days held based on the financial year end
         * date i.e. less than daysPerYear.(pro-rated) The residual amount = DV per
         * entire year * (days held ÷ daysPerYear)
         * 
         * Note: for leap years daysPerYear will be 366 for other years it is 365.
         * 
         * 3. For DV method, the current Opening Value will be substracted from the
         * previous year's DV amount.
         * 
         * 4. The residual amount from Year 1 will be added to the next Year to check if
         * the value is less than lowValuePool, If yes then that year will be the last
         * amount to be represented i.e. DV + residual Amount (OR) PC + residual Amount
         * 
         * 5. Sum of all reamining life year amounts for PC (OR) DV should be equal to
         * Asset's Opening Value Amount.
         * 
         * 6. Lease available date is always less than or equal to first taxable year.
         */
        public ResponseEntity<DepreciationResponse> calclulatePrimeCost(final DepreciationRequest request) {

                final BigDecimal openingValue = request.getOpeningValueCost();
                final BigDecimal lowValuePool = request.getLowValuePool();
                final LocalDate installDate = request.getProperty().getYearOfConstruction();
                final LocalDate availableDate = request.getProperty().getFirstLeaseDate();
                final LocalDate firstTaxableDate = getFirstTaxableEndDate(availableDate);
                final LocalDate fromFirstTaxableDate = request.getFirstTaxableEndDate();
                request.setFirstTaxableEndDate(fromFirstTaxableDate != null ? fromFirstTaxableDate : firstTaxableDate);

                final Period period = Period.between(installDate, availableDate);
                final int years = Math.abs(period.getYears());

                final int usefulLife = request.getProperty().getUsefulLifeInYears();
                final int remainingLife = usefulLife - years;
                int runningLife = remainingLife;

                BigDecimal openingValueDV = request.getOpeningValueCost();
                BigDecimal capital = BigDecimal.TEN;
                BigDecimal pcSum = BigDecimal.ZERO;
                BigDecimal pcResidualAmt = BigDecimal.ZERO;
                BigDecimal dvResidualAmt = BigDecimal.ZERO;

                List<DepreciationResult> results = new ArrayList<>();

                for (int i = 0; i < usefulLife; i++) {
                        DepreciationResult result = new DepreciationResult();
                        result.setFromDate("Jul-" + firstTaxableDate.plusYears(Long.valueOf(i) - 1).getYear());
                        result.setToDate("Jun-" + firstTaxableDate.plusYears(Long.valueOf(i)).getYear());

                        final boolean isLeapYear = java.time.Year.of(firstTaxableDate.getYear()).isLeap();
                        final long daysHeld = getDaysHeld(availableDate.plusYears(Long.valueOf(i)), firstTaxableDate,
                                        isLeapYear);

                        result.setYearCount(daysHeld > 364 ? "Year " + (results.size() + 1) : daysHeld + " days");

                        // Prime Cost Calculation
                        BigDecimal pc = BigDecimal.ZERO;
                        final float percentage = ((float) daysHeld) / 365;
                        // Prime Cost = Asset’s cost × (days held ÷ 365) × (100% ÷ asset’s effective
                        // life)
                        if (openingValue.doubleValue() > pcSum.doubleValue()) {
                                pc = calculateDepreciationAmount(openingValue, 100, daysHeld, isLeapYear,
                                                remainingLife);
                                pcSum = pcSum.add(pc);

                                if (daysHeld < 365) {
                                        pcResidualAmt = pc;
                                        pc = scaleAndRound(BigDecimal.valueOf(pc.doubleValue() * (percentage)));
                                        pcResidualAmt = pcResidualAmt.subtract(pc);
                                }
                                if (pcResidualAmt.add(pc).compareTo(lowValuePool) <= 0) {
                                        pc = pcResidualAmt.add(pc);
                                }
                        }
                        if (pc.doubleValue() == 0 && pcResidualAmt.doubleValue() > 0) {
                                pc = pcResidualAmt;
                                pcResidualAmt = BigDecimal.ZERO;
                        }
                        result.setPrimeCostMethod(new DepreciationAmount(pc, capital, pc.add(capital)));

                        // -----------------------------------------------------------------------------
                        // -----------------------------------------------------------------------------

                        // Diminishing Value Calculation
                        BigDecimal dv = BigDecimal.ZERO;

                        // Diminishing Value = Base value × (days held ÷ 365) × (200% ÷ asset’s
                        // effective life)
                        if (openingValueDV.add(dvResidualAmt).compareTo(lowValuePool) <= 0) {
                                dv = openingValueDV.add(dvResidualAmt);
                                openingValueDV = BigDecimal.ZERO;
                        } else {
                                if (runningLife > 0) {
                                        dv = calculateDepreciationAmount(openingValueDV, 200, daysHeld, isLeapYear,
                                                        remainingLife);
                                        if (daysHeld >= 365) {
                                                openingValueDV = scaleAndRound(openingValueDV.subtract(dv));
                                        }
                                        runningLife--;
                                        if (daysHeld < 365) {
                                                dvResidualAmt = dv;
                                                dv = scaleAndRound(BigDecimal.valueOf(dv.doubleValue() * (percentage)));
                                                dvResidualAmt = dvResidualAmt.subtract(dv);
                                                openingValueDV = scaleAndRound(openingValueDV.subtract(dv));
                                        }

                                        if (dvResidualAmt.add(dv).compareTo(lowValuePool) <= 0) {
                                                dv = dvResidualAmt.add(dv);
                                        }
                                        if (dv.doubleValue() == 0 && dvResidualAmt.doubleValue() > 0) {
                                                dv = dvResidualAmt;
                                                dvResidualAmt = BigDecimal.ZERO;
                                        }
                                } else if (runningLife == 0) {
                                        dv = scaleAndRound(openingValueDV);
                                        dvResidualAmt = BigDecimal.ZERO;
                                        openingValueDV = BigDecimal.ZERO;
                                        runningLife--;
                                }
                        }

                        result.setDiminishingMethod(new DepreciationAmount(dv, capital, dv.add(capital)));

                        if (fromFirstTaxableDate != null) {
                                if (firstTaxableDate.plusYears(Long.valueOf(i)).getYear() >= fromFirstTaxableDate
                                                .getYear()) {
                                        results.add(result);
                                }
                        } else {
                                results.add(result);
                        }
                }

                DepreciationResult totalResult = new DepreciationResult();
                totalResult.setYearCount("TOTALS");
                totalResult.setToDate("");
                totalResult.setFromDate("");
                totalResult.setPrimeCostMethod(getTotalPrimeCostValues(results));
                totalResult.setDiminishingMethod(getTotalDiminishingValues(results));
                results.add(totalResult);

                DepreciationResponse response = new DepreciationResponse(request.getProperty(), results);

                generateDocument(response, request);

                return ResponseEntity.ok().body(response);
        }

        private LocalDate getFirstTaxableEndDate(LocalDate availableDate) {
                LocalDate taxEndDate = LocalDate.of(availableDate.getYear(), 06, 30);

                if (availableDate.isAfter(taxEndDate)) {
                        return taxEndDate.plusYears(1);
                }
                return taxEndDate;
        }

        private DepreciationAmount getTotalDiminishingValues(List<DepreciationResult> results) {
                DepreciationAmount diminishingValueAmt = new DepreciationAmount();
                diminishingValueAmt.setPlantAndEquipment(
                                results.stream().map(p -> p.getDiminishingMethod().getPlantAndEquipment())
                                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                diminishingValueAmt.setCaptial(results.stream().map(p -> p.getDiminishingMethod().getCaptial())
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                diminishingValueAmt.setTotal(results.stream().map(p -> p.getDiminishingMethod().getTotal())
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                return diminishingValueAmt;
        }

        private DepreciationAmount getTotalPrimeCostValues(List<DepreciationResult> results) {
                DepreciationAmount primeCostAmt = new DepreciationAmount();
                primeCostAmt.setPlantAndEquipment(
                                scaleAndRound(results.stream().map(p -> p.getPrimeCostMethod().getPlantAndEquipment())
                                                .reduce(BigDecimal.ZERO, BigDecimal::add)));
                primeCostAmt.setCaptial(results.stream().map(p -> p.getPrimeCostMethod().getCaptial())
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                primeCostAmt.setTotal(results.stream().map(p -> p.getPrimeCostMethod().getTotal())
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                return primeCostAmt;
        }

        private long getDaysHeld(final LocalDate cycleDate, final LocalDate firstTaxableDate,
                        final boolean isLeapYear) {
                long diffDays = ChronoUnit.DAYS.between(cycleDate, firstTaxableDate);
                // diffDays = isLeapYear ? 366 - diffDays : 365 - diffDays;
                return diffDays > 0 ? (int) diffDays : isLeapYear ? 366 : 365;
        }

        private BigDecimal calculateDepreciationAmount(final BigDecimal openingVal, final int rate, final long daysHeld,
                        final boolean isLeapYear, final int remainingLife) {
                return scaleAndRound(openingVal
                                .multiply(BigDecimal.valueOf(isLeapYear ? 366 : 365).divide(
                                                BigDecimal.valueOf(isLeapYear ? 366 : 365), RoundingMode.HALF_UP))
                                .multiply(BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(remainingLife),
                                                RoundingMode.HALF_UP))
                                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        }

        private boolean generateDocument(final DepreciationResponse response, final DepreciationRequest depRequest) {

                DocumentRequest request = new DocumentRequest();
                request.setLowValuePool(depRequest.getLowValuePool());
                request.setFirstTaxableEndDate(depRequest.getFirstTaxableEndDate());
                request.setOpeningValueCost(depRequest.getOpeningValueCost());
                request.setProperty(response.getProperty());
                request.setBody(response.getDepreciationResults());
                request.setCreatedBy("Admin");
                request.setTitle("Depreciation Summary");
                request.setFileName("DepreciationSummary.pdf");
                request.setTemplate("src/main/resources/templates/depreciationSummary.vm");

                return documentService.generatePdf(request);
        }

        private BigDecimal scaleAndRound(BigDecimal input) {
                return input.setScale(2, RoundingMode.HALF_UP);
        }
}
