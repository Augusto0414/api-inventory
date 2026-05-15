package com.practicas.ia_api.application.service.impl;

import com.practicas.ia_api.application.dto.product.CreateProductDTO;
import com.practicas.ia_api.application.dto.product.ProductResponseDTO;
import com.practicas.ia_api.application.dto.product.UpdateProductDTO;
import com.practicas.ia_api.application.service.interfaces.IProductService;
import com.practicas.ia_api.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public class ProductService implements IProductService {
    @Override
    public ProductResponseDTO saveProduct(CreateProductDTO createProductDTO) {
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, UpdateProductDTO updateProductDTO) {
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {

    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        return  ProductResponseDTO.builder()
                .idProduct(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
