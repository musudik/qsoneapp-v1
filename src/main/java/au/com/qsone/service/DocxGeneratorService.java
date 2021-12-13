package au.com.qsone.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;

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
}
