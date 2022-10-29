package com.spexcode.asset_management.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spexcode.asset_management.model.Asset;
import com.spexcode.asset_management.model.AssetsRepository;

@Service
public class AssetsService {

    @Autowired
    AssetsRepository repository;

    public List<Asset> getItems() {
        
        List<Asset> items = new ArrayList<>();

        Asset asset1 = new Asset();
        asset1.setId(10L);
        asset1.setDescription("Mesa");   
        items.add(asset1);

        Asset asset2 = new Asset();
        asset2.setId(20L);
        asset2.setDescription("Cadeira");   
        items.add(asset2);

        return items;
        
    }

    public Iterable<Asset> getAll() {
        return repository.findAll();
    }

    public Optional<Asset> getById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Asset> getByType(String type) {
        return repository.findByType(type);
    }
}
