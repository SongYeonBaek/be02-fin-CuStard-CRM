package com.example.backend.log.repository;

import com.example.backend.log.entity.LoginLog;
import com.example.backend.log.entity.ProductDetailLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailLogRespository extends JpaRepository<ProductDetailLog, Long> {
    List<ProductDetailLog> findByCustomerIdx(Long idx);
}


