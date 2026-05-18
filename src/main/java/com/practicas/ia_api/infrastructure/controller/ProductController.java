package com.practicas.ia_api.infrastructure.controller;

import com.practicas.ia_api.application.dto.DefaultResponse;
import com.practicas.ia_api.application.dto.product.CreateProductDTO;
import com.practicas.ia_api.application.dto.product.ProductResponseDTO;
import com.practicas.ia_api.application.dto.product.DefaultResponseProductResponseDTO;
import com.practicas.ia_api.application.service.interfaces.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "product-controller", description = "Operaciones sobre productos")
public class ProductController {
    private final IProductService productService;
    public ProductResponseDTO responseDTO;

    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto y devuelve su información",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseProductResponseDTO.class)))
            })
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
