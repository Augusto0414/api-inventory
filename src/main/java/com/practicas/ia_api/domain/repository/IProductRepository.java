package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
}
