package com.example.backend.elastic.repository;

import com.example.backend.elastic.model.entity.CustomerDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerDocumentRepository extends ElasticsearchRepository<CustomerDocument, String> {

    Page<CustomerDocument> findByLevel(Integer level, Pageable pageable);
    List<CustomerDocument> findAll();
}
