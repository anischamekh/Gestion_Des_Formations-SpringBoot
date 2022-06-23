package com.cni.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.SimpleMailMessage;

import com.cni.projet.entity.Formation;
import com.cni.projet.entity.Mail;
import com.cni.projet.entity.Participant;
import com.cni.projet.entity.User;

public interface MailRepository extends JpaRepository<Mail, Long> {

	

	SimpleMailMessage save(SimpleMailMessage msg);

}
