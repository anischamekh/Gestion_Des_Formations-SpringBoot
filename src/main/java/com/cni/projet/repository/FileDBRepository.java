package com.cni.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cni.projet.entity.FileDB;


@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}