package com.practicas.ia_api.application.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDTO {
    @NotBlank(message = "El nombre del producto es requerido")
    private String name;

    private String description;

    @NotBlank(message = "El precio del producto es requerido")
    private Double price;

    @NotBlank(message = "El stock del producto es requerido")
    private Integer stock;

    @NotBlank(message = "La categoría del producto es requerida")
    private String category;
}
