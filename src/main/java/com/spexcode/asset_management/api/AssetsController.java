package com.spexcode.asset_management.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spexcode.asset_management.model.Asset;

@RestController
@RequestMapping("/assets/v1/items")
public class AssetsController {

    @Autowired
    AssetsService service;

    @GetMapping("/static")
    public List<Asset> get() {
        return service.getItems();
    }

    @GetMapping
    public Iterable<Asset> getFromDatabase() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Asset> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/type/{type}")
    public Iterable<Asset> getByType(@PathVariable("type") String type){
        return service.getByType(type);
    }

    @PostMapping("/register/")
    public String registerAsset(@RequestBody Asset asset) {
        asset = service.register(asset);

        return "Asset Registered! ID=" + asset.getId();
        
    }
    
}
