package com.example.backend.elastic.repository;

import com.example.backend.elastic.model.entity.ProductDetailDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDetailDocumentRepository extends ElasticsearchRepository<ProductDetailDocument, String> {
    List<ProductDetailDocument> findAllByCustomerIdx(Long idx);
}
