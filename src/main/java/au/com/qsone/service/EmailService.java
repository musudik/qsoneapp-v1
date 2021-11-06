package au.com.qsone.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 */
@Service
public class EmailService {

    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    public EmailService(TemplateEngine templateEngine, JavaMailSender emailSender) {
        this.templateEngine = templateEngine;
        this.emailSender = emailSender;
    }


//    @Autowired
//    private SimpleMailMessage template;
//    
    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;
//    
//    @Autowired
//    private FreeMarkerConfigurer freemarkerConfigurer;
//    
    @Value("classpath:/mail-logo.png")
    private Resource resourceFile;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();///            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

//	public void sendSimpleMessageUsingTemplate(String to, String subject, String ...templateModel) { 
//		  String text = String.format(template.getText(), templateModel); 
//		  sendSimpleMessage(to, subject, text); 
//	}
//	 
//    @Override
//    public void sendMessageWithAttachment(String to,
//                                          String subject,
//                                          String text,
//                                          String pathToAttachment) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            // pass 'true' to the constructor to create a multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(NOREPLY_ADDRESS);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);
//
//            emailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//    
//
    public void sendMessageUsingThymeleafTemplate(
        String to, String subject, Map<String, Object> templateModel)
            throws MessagingException, IOException {
    	
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        
        String process = templateEngine.process("template-thymeleaf", thymeleafContext);
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = emailSender.createMimeMessage();
        final MimeMessageHelper message =
            new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject(subject);
        message.setText(process, true);
        message.setTo(to);
        
//        RandomAccessFile file = new RandomAccessFile("src/main/resources/static/dist/img/qsone.png", "r");
//        byte[] imageBytes = new byte[(int) file.length()];
//        file.close();
//        
//        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
//        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
//        message.addInline("qsone.png", imageSource, "image/png ");
        
        emailSender.send(mimeMessage);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.addInline("attachment.png", resourceFile);
        emailSender.send(message);
    }
   
}