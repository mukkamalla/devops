package com.valign.payroll.webservice.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
       // emailSender.
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());


        Template t = freemarkerConfig.getTemplate("subscription-email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
    

    public void sendSimpleConfirmMessage(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
       // emailSender.
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());


        Template t = freemarkerConfig.getTemplate("subscription-confirm-email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }

}
