package com.practicas.ia_api.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {
    private  String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}
