package com.ptopalidis.cecloud.platform.pdfgenerator.repository;

import com.ptopalidis.cecloud.platform.pdfgenerator.domain.FileTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileTemplateRepository extends JpaRepository<FileTemplate, Long> {

        Optional<FileTemplate> findByName(String name);
}