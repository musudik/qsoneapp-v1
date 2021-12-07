package au.com.qsone.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import au.com.qsone.web.dto.DocumentRequest;

@Service
public class DocumentService {

    public boolean generatePdf(DocumentRequest request) {
        boolean generated = false;
        PdfWriter pdfWriter = null;

        try {
            // initialize velocity engine
            VelocityEngine ve = new VelocityEngine();
            ve.init();

            // add that list to a VelocityContext
            VelocityContext context = new VelocityContext();
            context.put("property", request.getProperty());
            context.put("results", request.getBody());
            context.put("lowValuePool", request.getLowValuePool());
            context.put("firstTaxableEndDate", request.getFirstTaxableEndDate());
            context.put("openingValueCost", request.getOpeningValueCost());

            // get the Template
            Template t = ve.getTemplate(request.getTemplate());

            // render the template into a Writer, here a StringWriter
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            String html = writer.toString();

            Document document = new Document();
            document.addAuthor(request.getCreatedBy());
            document.addCreationDate();
            document.addProducer();
            document.addCreator(request.getCreatedBy());
            document.addTitle(request.getTitle());
            document.setPageSize(PageSize.LETTER);

            OutputStream file = new FileOutputStream(new File(request.getFileName()));
            pdfWriter = PdfWriter.getInstance(document, file);

            document.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));
            // close the document
            document.close();
            // close the writer
            pdfWriter.close();
            generated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generated;
    }
}
