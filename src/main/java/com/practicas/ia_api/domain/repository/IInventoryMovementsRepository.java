package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.InventoryMovement;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInventoryMovementsRepository {
    InventoryMovement save(InventoryMovement inventoryMovements);
    Optional<InventoryMovement> findById(String id);
}
