package com.practicas.ia_api.infrastructure.controller;

import com.practicas.ia_api.application.dto.DefaultResponse;
import com.practicas.ia_api.application.dto.product.CreateProductDTO;
import com.practicas.ia_api.application.dto.product.ProductResponseDTO;
import com.practicas.ia_api.application.service.interfaces.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final IProductService productService;
    public ProductResponseDTO responseDTO;

    @PostMapping(name = "Crear un nuevo producto", path = "/create")
    public DefaultResponse<ProductResponseDTO> createProduct(
            @Valid @RequestBody CreateProductDTO createProductDTO
    ){
        try {
            responseDTO = productService.saveProduct(createProductDTO);
            return DefaultResponse.<ProductResponseDTO>builder()
                    .success(true)
                    .message("Producto creado exitosamente")
                    .data(responseDTO)
                    .build();
        }catch (IllegalArgumentException e){
            return DefaultResponse.<ProductResponseDTO>builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
