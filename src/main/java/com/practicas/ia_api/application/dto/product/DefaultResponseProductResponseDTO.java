package com.practicas.ia_api.application.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DefaultResponseProductResponseDTO", description = "Wrapper para respuestas de producto")
public class DefaultResponseProductResponseDTO {
    private String message;
    private ProductResponseDTO data;
    private boolean success;
}

