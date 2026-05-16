package com.practicas.ia_api.application.service.impl;

import com.practicas.ia_api.application.dto.product.CreateProductDTO;
import com.practicas.ia_api.application.dto.product.ProductResponseDTO;
import com.practicas.ia_api.application.dto.product.UpdateProductDTO;
import com.practicas.ia_api.application.service.interfaces.IProductService;
import com.practicas.ia_api.domain.entity.Product;
import com.practicas.ia_api.domain.repository.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService implements IProductService {
    private IProductRepository productRepository;

    @Override
    public ProductResponseDTO saveProduct(CreateProductDTO createProductDTO) {
        log.info("Creando nuevo producto connombre: {}", createProductDTO.getName());

        Product product = Product.builder()
                .name(createProductDTO.getName())
                .description(createProductDTO.getDescription())
                .price(createProductDTO.getPrice())
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Producto creado exitosamente con ID: {}", savedProduct.getId());

        return mapToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
       if(id == null) {
           log.warn("ID de producto es nulo");
           throw new IllegalArgumentException("ID de producto no puede ser nulo");
       }

        return productRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> {
                    log.warn("Producto no encontrado con ID: {}", id);
                    return new IllegalArgumentException("Producto no encontrado");
                });
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, UpdateProductDTO updateProductDTO) {
        if(id == null) {
            log.warn("ID de producto es nulo");
            throw new IllegalArgumentException("ID de producto no puede ser nulo");
        }

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updateProductDTO.getName());
                    product.setDescription(updateProductDTO.getDescription());
                    product.setPrice(updateProductDTO.getPrice());
                    product.setSku(updateProductDTO.getStock());
                    Product updatedProduct = productRepository.save(product);
                    log.info("Producto actualizado exitosamente con ID: {}", id);
                    return mapToResponseDTO(updatedProduct);
                })
                .orElseThrow(() -> {
                    log.warn("Producto no encontrado con ID: {}", id);
                    return new IllegalArgumentException("Producto no encontrado");
                });
    }

    @Override
    public void deleteProduct(UUID id) {
        if(id == null) {
            log.warn("ID de producto es nulo");
            throw new IllegalArgumentException("ID de producto no puede ser nulo");
        }

        productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    productRepository.deleteById(id);
                    log.info("Producto eliminado exitosamente con ID: {}", id);
                }, () -> {
                    log.warn("Producto no encontrado con ID: {}", id);
                    throw new IllegalArgumentException("Producto no encontrado");
                });

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
