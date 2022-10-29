package com.spexcode.asset_management.model;

import org.springframework.data.repository.CrudRepository;

public interface AssetsRepository extends CrudRepository<Asset, Long>{

    Iterable<Asset> findByType(String type);
    
}
