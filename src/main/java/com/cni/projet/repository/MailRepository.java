package com.cni.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.projet.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Long> {

}
