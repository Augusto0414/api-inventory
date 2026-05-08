package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.InventoryMovement;

import java.util.Optional;

public interface IInventoryMovementsRepository {
    InventoryMovement save(InventoryMovement inventoryMovements);
    Optional<InventoryMovement> findById(String id);
}
