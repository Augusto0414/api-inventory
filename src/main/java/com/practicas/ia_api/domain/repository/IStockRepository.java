package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository {
    Stock save(Stock stock);
    Stock findById(String id);
}
