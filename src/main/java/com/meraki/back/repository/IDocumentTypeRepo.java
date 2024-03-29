package com.meraki.back.repository;

import com.meraki.back.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentTypeRepo extends JpaRepository<DocumentType,Integer> {
}
