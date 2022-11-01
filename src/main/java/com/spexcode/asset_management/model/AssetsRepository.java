package com.spexcode.asset_management.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetsRepository extends JpaRepository<Asset, Long>{

    List<Asset> findByType(String type);
    
}
