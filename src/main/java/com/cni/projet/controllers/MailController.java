package com.cni.projet.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Mail;
import com.cni.projet.entity.User;
import com.cni.projet.security.services.MailService;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {
	
	
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/addMail")
	public Long sendMail(@RequestBody Mail mail) {
		System.out.println("send mail");
		return mailService.sendMail(mail);
	}
	
	@PostMapping("/sendAccount/{id}")
	public SimpleMailMessage sendAccount(@PathVariable Long id) {
		System.out.println("send mail");
		return mailService.sendAccount(id);
	}
	
	@PostMapping("/sendFormation/{idP}/{idF}")
	public SimpleMailMessage sendFormation(@PathVariable int idP,@PathVariable int idF) {
		System.out.println("send mail");
		return mailService.sendFormation(idP,idF);
	}
	
	@PostMapping("/sendEvaluation/{id}")
	public SimpleMailMessage sendEvaluation(@PathVariable int id) {
		System.out.println("send mail");
		return mailService.sendEvaluation(id);
	}
	
	@GetMapping("/allMails")
	public List<Mail>list(){
		System.out.println("Get all mails");
		return mailService.getAll();
		
	}
	

}
