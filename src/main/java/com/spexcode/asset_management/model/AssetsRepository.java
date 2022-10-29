package com.spexcode.asset_management.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AssetsRepository extends CrudRepository<Asset, Long>{

    List<Asset> findByType(String type);
    
}
