package com.cni.projet.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Mail;
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
	
	
	@GetMapping("/allMails")
	public List<Mail>list(){
		System.out.println("Get all mails");
		return mailService.getAll();
		
	}
	

}
