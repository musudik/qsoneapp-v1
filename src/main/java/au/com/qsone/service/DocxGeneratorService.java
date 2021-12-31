package au.com.qsone.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;

import javax.validation.Valid;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;

import au.com.qsone.entity.Invoice;
import au.com.qsone.entity.Job;

@Service
public class DocxGeneratorService {

	  public byte[] generateDocxFileFromTemplate(String template) throws Exception {

	    InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(template);

	    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

	    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

	    VariablePrepare.prepare(wordMLPackage);

	    HashMap<String, String> variables = new HashMap<>();
	    variables.put("salutation", "Mr");
	    variables.put("firstName", "Leo");
	    variables.put("lastName", "Porlo");
	    //variables.put("InvoiceDate", LocalDate.now().toString());

	    documentPart.variableReplace(variables);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	    wordMLPackage.save(outputStream);

	    return outputStream.toByteArray();
	  }

	public byte[] generateQuote(String template, Invoice invoice, Job job) throws Exception {
		InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(template);

	    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

	    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

	    VariablePrepare.prepare(wordMLPackage);

	    HashMap<String, String> variables = new HashMap<>();
	    variables.put("quoteTitle", invoice.getInvoiceTitle());
	    variables.put("contactPostalAddress", job.getPropertyId());
	    variables.put("contactTypeName", invoice.getContactTypeName());
	    variables.put("contactTypeValue", invoice.getContactTypeValue());
	    //variables.put("lastName", "Porlo");
//	    variables.put("InvoiceDate", invoice.getInvoiceDate().toString());
//	    variables.put("ExpiryDate", invoice.getExpiryDate().toString());
//	    variables.put("ContactAccountNumber", invoice.getTitle());
//	    variables.put("InvoiceNumber", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("OrganisationTaxDisplayNumber", invoice.getTitle());
//	    variables.put("Summary", invoice.getTitle());
//	    variables.put("TaxUnitName", invoice.getTitle());
//	    variables.put("InvoiceCurrency", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
//	    variables.put("Reference", invoice.getTitle());
	    //variables.put("InvoiceDate", LocalDate.now().toString());

	    documentPart.variableReplace(variables);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	    wordMLPackage.save(outputStream);

	    return outputStream.toByteArray();
	}	
}
