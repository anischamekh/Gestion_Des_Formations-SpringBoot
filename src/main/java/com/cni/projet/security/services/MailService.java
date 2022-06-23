package com.cni.projet.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.cni.projet.entity.Formation;
import com.cni.projet.entity.Mail;
import com.cni.projet.entity.Participant;
import com.cni.projet.entity.Theme;
import com.cni.projet.entity.User;
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
	    mailSender.setPassword("atsjeidibowfyieh");
	    
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
	private FormateurService formateurService;
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private FormationService formationService;
	
	
    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }
    
    public SimpleMailMessage sendAccount(Long id) throws MailException {
    	
    	System.out.println("send email...");
		String obj = "Compte";
		
		User user = formateurService.findFormateurById(id);
    	SimpleMailMessage msg = new SimpleMailMessage();
    	
			msg.setTo(user.getEmail());
			msg.setSubject(obj);
			
			msg.setText("Bonjour Monsieur, Madame, " + user.getFirstName()+" "+user.getLastName() + "\n" + 
    				"Votre identifiant est: " + user.getUsername() + "\n" + 
    				"Votre Mot de passe est: " + user.getPassword() + "\n" + 
    				"Pour vous connecter à votre compte accéder à " + "\n" +
    				"http://localhost:4200/login");
    	javaMailSender.send(msg);
    	return mailRepository.save(msg);
    	
    }  
    
    
    public SimpleMailMessage sendFormation(int idP,int idF) throws MailException {
    	
    	System.out.println("send email...");
		String obj = "Formation";
		
		Participant participant = participantService.findParticipantById(idP);
		Formation formation = formationService.findFormationById(idF);
    	SimpleMailMessage msg = new SimpleMailMessage();
    	
			msg.setTo(participant.getEmail());
			msg.setSubject(obj);
			
			msg.setText("Bonjour Monsieur, Madame, " + participant.getFirstName()+" "+participant.getLastName() + "\n" + 
    				"Vous avez inscrit à notre formation: " + formation.getTitre() + "\n" + 
    				"Cette formation débutera le " + formation.getDateDebut() + "\n" + 
    				"Pour participer à cette formation accéder à " + "\n" +
    				formation.getLien());
    	javaMailSender.send(msg);
    	return mailRepository.save(msg);
    	
    }  
    
    
    public SimpleMailMessage sendEvaluation(int id) throws MailException {
    	
    	System.out.println("send email...");
		String obj = "Evaluation";
		
		Participant participant = participantService.findParticipantById(id);
    	SimpleMailMessage msg = new SimpleMailMessage();
    	
			msg.setTo(participant.getEmail());
			msg.setSubject(obj);
			
			msg.setText("Bonjour Monsieur, Madame, " + participant.getFirstName()+" "+participant.getLastName() + "\n" + 
    				"Si vous voulez évaluer notre formation merci d'accéder à " + "\n" + 
    				"http://localhost:4200/evaluation" + "\n" + 
    				"Votre avis nous intéresse !");
    	javaMailSender.send(msg);
    	return mailRepository.save(msg);
    	
    } 
    
    
    
    public long sendMail(Mail mail) throws MailException {
    	
		System.out.println("send email...");
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(mail.getDestinaire());
		msg.setSubject(mail.getObjet());
    	msg.setText(mail.getMessage()+" "+mail.getObjet());
    	javaMailSender.send(msg);
    	
    	return mailRepository.save(mail).getId();
    }

	public List<Mail> getAll() {
		return mailRepository.findAll();
	}
    
}
