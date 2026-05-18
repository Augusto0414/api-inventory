package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.Warehouse;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository {
    Warehouse save(Warehouse warehouse);
    Warehouse findById(String id);
}
