package com.practicas.ia_api.application.service.interfaces;

import com.practicas.ia_api.application.dto.product.CreateProductDTO;
import com.practicas.ia_api.application.dto.product.ProductResponseDTO;
import com.practicas.ia_api.application.dto.product.UpdateProductDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductResponseDTO saveProduct(CreateProductDTO createProductDTO);
    ProductResponseDTO getProductById(UUID id);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO updateProduct(UUID id, UpdateProductDTO updateProductDTO);
    void deleteProduct(UUID id);
}
