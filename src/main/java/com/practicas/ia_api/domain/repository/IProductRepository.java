package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.Product;

import java.util.Optional;

public interface IProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
}
