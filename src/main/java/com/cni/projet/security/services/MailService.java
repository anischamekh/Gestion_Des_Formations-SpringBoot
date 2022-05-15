package com.cni.projet.security.services;

import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.cni.projet.entity.Mail;
import com.cni.projet.repository.MailRepository;

@Service
@Transactional
public class MailService {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);   
	    mailSender.setUsername("gestion.formation2022@gmail.com");
	    mailSender.setPassword("admin.gdf@123");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MailRepository mailRepository;
	
    @Autowired
    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }
    
    
    public long sendMail(Mail mail) throws MailException {
    	
		System.out.println("send email...");
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(mail.getDestinaire());
		msg.setSubject(mail.getObjet());
    	msg.setText(mail.getMessage());
    	javaMailSender.send(msg);
    	
    	return mailRepository.save(mail).getId();
    }

	public List<Mail> getAll() {
		return mailRepository.findAll();
	}
    
}
