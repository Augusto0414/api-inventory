package com.practicas.ia_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UUID;

@Entity
@Table(name = "stocks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @UUID
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
