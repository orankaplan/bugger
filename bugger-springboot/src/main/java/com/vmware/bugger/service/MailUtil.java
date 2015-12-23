package com.vmware.bugger.service;


import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailUtil {

    private String SMTP_HOST = "smtp.vmware.com";
        private String FROM_ADDRESS = "bugger@vmware.com";
    private String FROM_NAME = "FindTheBugger";
    private String SUBJECT = "FindTheBugger has located a bug you may assist solving";

    private Properties properties;
    private Session session;

    public MailUtil(){
        properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        session = Session.getDefaultInstance(properties);
    }

    public boolean SendMail(List<MailMessage> mailMessages) {
        for(MailMessage mailMessage : mailMessages){
            Send(mailMessage.getRecipient(),mailMessage.getMessage());
            System.out.println("Mail sent to:" + mailMessage.getRecipient());
        }
        return true;
    }
    public boolean SendCulpritMail(List<Culprit> mailMessages) {
        for(Culprit culprit : mailMessages){
            Date date = new java.sql.Date(culprit.getModifiedDate());
            Send(culprit.getEmail(),"some culprit msg, name: "+culprit.getName()+ " date: "+date);
            System.out.println("Mail sent to:" + culprit.getEmail());
        }
        return true;
    }
    private boolean Send(String recipient, String message){
        try {
            Message msg = new MimeMessage(session);
            InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
            msg.setFrom(from);
            InternetAddress toAddresses = new InternetAddress(recipient);
            msg.setRecipient(Message.RecipientType.TO, toAddresses);
            msg.setSubject(SUBJECT);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
            return true;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex){
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}