package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.Warehouse;

public interface IWarehouseRepository {
    Warehouse save(Warehouse warehouse);
    Warehouse findById(String id);
}
