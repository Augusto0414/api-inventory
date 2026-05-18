package com.practicas.ia_api.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private UUID idProduct;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private LocalTime createdAt;
    private LocalTime updatedAt;
}
