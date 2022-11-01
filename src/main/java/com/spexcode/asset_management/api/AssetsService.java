package com.spexcode.asset_management.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.spexcode.asset_management.model.Asset;
import com.spexcode.asset_management.model.AssetsRepository;
import com.spexcode.asset_management.model.dto.AssetDTO;

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

    public List<Asset> getAll() {
        return repository.findAll();
    }

    public List<AssetDTO> getAllDTO() {

        // Using lambdas
        return repository.findAll().stream().map(asset -> new AssetDTO(asset)).collect(Collectors.toList());

        //// Using lists and for
        // List<Asset> assets = repository.findAll();
        // List<AssetDTO> DTOList = new ArrayList<>();

        // for(Asset asset : assets){
        //     DTOList.add(new AssetDTO(asset));
        // }
        // return DTOList;
    }

    public Optional<Asset> getById(Long id) {
        return repository.findById(id);
    }

    public List<Asset> getByType(String type) {
        return repository.findByType(type);
    }

    public List<AssetDTO> getByTypeDTO(String type) {
        return repository.findByType(type).stream().map(asset -> new AssetDTO(asset)).collect(Collectors.toList());
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
