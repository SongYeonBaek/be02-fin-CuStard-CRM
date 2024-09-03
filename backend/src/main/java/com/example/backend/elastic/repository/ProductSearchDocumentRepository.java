package com.example.backend.elastic.repository;

import com.example.backend.elastic.model.entity.ProductSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductSearchDocumentRepository extends ElasticsearchRepository<ProductSearchDocument, String> {

}
