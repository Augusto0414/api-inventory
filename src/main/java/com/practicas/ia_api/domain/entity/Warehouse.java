package com.practicas.ia_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UUID;

@Entity
@Table(name = "warehouses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @UUID
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;
}
