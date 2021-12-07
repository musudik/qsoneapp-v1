package au.com.qsone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import au.com.qsone.web.dto.DocumentRequest;
import au.com.qsone.web.dto.PrimeCost;
import au.com.qsone.web.dto.PrimeCostRequest;
import au.com.qsone.web.dto.PrimeCostResponse;
import au.com.qsone.web.dto.PrimeCostResult;

@Service
public class PrimeCostService {

    @Autowired
    private DocumentService documentService;

    public ResponseEntity<PrimeCostResponse> calclulatePrimeCost(PrimeCostRequest request) {

        List<PrimeCostResult> results = new ArrayList<>();
        List<PrimeCost> primeCosts = new ArrayList<>();

        PrimeCostResponse response = new PrimeCostResponse();

        generateDocument(response);

        return ResponseEntity.ok().body(response);
    }

    private boolean generateDocument(PrimeCostResponse response) {

        DocumentRequest request = new DocumentRequest();
        request.setAsset(response.getAsset());
        request.setBody(response.getResults());
        request.setCreatedBy("Admin");
        request.setTitle("Prime Cost Report");
        request.setFileName("PrimeCostReport.pdf");
        request.setTemplate("src/main/resources/templates/PrimeCost.vm");

        return documentService.generatePdf(request);
    }
}
