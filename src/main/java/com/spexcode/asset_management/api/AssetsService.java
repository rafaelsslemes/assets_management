package com.spexcode.asset_management.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public Asset register(Asset asset) {
        return repository.save(asset);
    }

    public Asset update(Asset asset) {
        Assert.notNull(asset.getId(), "Invalid operation. Asset not registered!");
        
        // It works, but could cause relashionship problems 
        // return repository.save(asset);

        Optional<Asset> optional = repository.findById(asset.getId());

        if(optional.isPresent()){
            Asset assetRegistered = optional.get();
            assetRegistered.setDescription(asset.getDescription());
            assetRegistered.setType(asset.getType());

            return repository.save(assetRegistered);
        
        }
        
        throw new RuntimeException("Invalid operation. Asset not registered!");
    }

    public String delete(Long id) {
        Assert.notNull(id, "Invalid operation. None id received!");

        Optional<Asset> optional = repository.findById(id);

        if(optional.isPresent()){
            repository.deleteById(id);
            return "Asset deleted!";
        }
        
        return "Asset not registered!";
    }
}
