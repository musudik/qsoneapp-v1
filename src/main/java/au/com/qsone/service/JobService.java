package au.com.qsone.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.qsone.entity.Invoice;
import au.com.qsone.entity.InvoiceItem;
import au.com.qsone.entity.Item;
import au.com.qsone.entity.Job;
import au.com.qsone.entity.Property;
import au.com.qsone.repository.IInvoiceRepository;
import au.com.qsone.repository.IItemRepository;
import au.com.qsone.repository.IJobRepository;
import au.com.qsone.repository.IPropertyRepository;
import au.com.qsone.web.dto.EmailTemplateRequest;

@Service
@Transactional
public class JobService {

	@Autowired
    private DocumentService documentService;
	
	@Autowired
    private EmailService emailService;
	 
    @Autowired
    private IJobRepository repo;
    
    @Autowired
    private IPropertyRepository propertyRepo;
    
    @Autowired
    private IInvoiceRepository invoiceRepo;
    
    @Autowired
    private IItemRepository itemRepo;

    public List<Job> listAll() {
        return repo.findAll();
    }

    public Page<Job> findPaginated(String search, Pageable pageable) {
        List<Job> Jobs = repo.searchJob(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Job> list;

        if (Jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, Jobs.size());
            list = Jobs.subList(startItem, toIndex);
        }

        Page<Job> bookPage = new PageImpl<Job>(list, PageRequest.of(currentPage, pageSize), Jobs.size());

        return bookPage;
    }


    public void save(Job Job) {
        repo.save(Job);
    }

    public Job get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
    
    public List<Invoice> listInvoices() {
        return invoiceRepo.findAll();
    }
    
    public void saveInvoice(Invoice invoice) throws MessagingException, IOException {
    	
    	Date now = new Date();
        invoice.getInvoiceItems().forEach(i -> updateDefaults(i, now));
        
        if (invoice.getInvoiceId() == null) {
        	invoice.setCreatedBy(getCurrentUser());
        	invoice.setCreatedDate(now);
        } else {
        	invoice.setUpdatedBy(getCurrentUser());
        	invoice.setUpdatedDate(now);
        }
        invoice.setSubTotal(BigDecimal.TEN);
        invoice.setTerms("Terms");
        invoice.setTaxTotal(BigDecimal.ONE);
        invoice.setTaxCode("BLA09");
        invoice.setTitle("First Invoice");
        
        Job job = get(Long.parseLong(invoice.getJobId()));
        Property property = propertyRepo.findById(Long.parseLong(job.getPropertyId())).get();
        //Save Invoice
        invoice = invoiceRepo.save(invoice);
        
        String invoiceDocument = invoice.getInvoiceId() + ".pdf";
        //generate document
        generateDocument(invoice, job, property, invoiceDocument);
        
        job.setStatus("QUOTE_SEND");
        job.setInvoiceFile(invoiceDocument);
      	job.setUpdatedBy(getCurrentUser());
       	job.setUpdatedDate(new Date());
        save(job);
        
        //send quote
        sendInvoice(job, property);
    }
    
    
    private void sendInvoice(Job job, Property property) throws MessagingException, IOException {
		Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("owner", property.getOwner());
        //templateModel.put("address", property.getFindAddress());
        templateModel.put("jobId", job.getId());
        templateModel.put("qsonelogo", "/dist/img/qsone.png");
		emailService.sendAttachmentMessageUsingThymeleaf(property.getEmail(),  "QSONE: Invoice for #"+job.getId(), templateModel, "template-invoice", job.getInvoiceFile());
	}
    
    private InvoiceItem updateDefaults(InvoiceItem invoiceItem, Date now ) {
		 if (invoiceItem.getInvoiceItemId() == null) {
	        	invoiceItem.setCreatedBy(getCurrentUser());
	        	invoiceItem.setCreatedDate(now);
       } else {
       	invoiceItem.setUpdatedBy(getCurrentUser());
       	invoiceItem.setUpdatedDate(now);
       }
		return invoiceItem;
	}
    
    private boolean generateDocument(final Invoice invoice, final Job job, final Property property, String invoiceDocument) {

    	EmailTemplateRequest request = new EmailTemplateRequest();
    	request.setOrganisationName("QSONE Ltd.");
    	request.setOrganisationPostalAddress("123 Rosewood Avenue \n 80412, \n WA");
        request.setJob(job);
        request.setInvoice(invoice);
        request.setProperty(property);
        request.setCreatedBy("Admin");
        request.setTitle("Quote");
		request.setFileName(invoiceDocument);
        request.setTemplate("src/main/resources/templates/email/quote.vm");
        request.setLogo("src/main/resources/static/dist/img/qsone.png");
		/*
		 * URL url = new URL("image.png"); HtmlEmail email = new HtmlEmail(); String cid
		 * = email.embed(url, "Foo");
		 */
        return documentService.generateEmailAttachments(request);
    }   

    public Invoice getInvoice(long id) {
        return invoiceRepo.findById(id).get();
    }

    public void deleteInvoice(long id) {
    	invoiceRepo.deleteById(id);
    }
    
    public List<Item> listItems() {
        return itemRepo.findAll();
    }
    
    public void saveItem(Item item) {
    	itemRepo.save(item);
    }

    public Item getItem(long id) {
        return itemRepo.findById(id).get();
    }

    public void deleteItem(long id) {
    	itemRepo.deleteById(id);
    }
    
    private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}