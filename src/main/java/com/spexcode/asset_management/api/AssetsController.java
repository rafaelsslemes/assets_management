package com.spexcode.asset_management.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spexcode.asset_management.model.Asset;
import com.spexcode.asset_management.model.dto.AssetDTO;

@RestController
@RequestMapping("/assets/v1/items")
public class AssetsController {

    @Autowired
    AssetsService service;

    @GetMapping("/static")
    public List<Asset> get() {
        return service.getItems();
    }

    @GetMapping("/dto")
    public List<AssetDTO> getDTO() {
        return service.getAllDTO();
    }

    @GetMapping
    public ResponseEntity<Iterable<Asset>> getFromDatabase() {

        // Complete sigature
        //return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

        // Short signature
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable("id") Long id) {
        Optional<Asset> optional = service.getById(id);

        //// Traditional If Syntax
        // if(optional.isPresent()){
        //     return ResponseEntity.ok(optional.get());
        // }
        // return ResponseEntity.notFound().build();

        //// Ternary If Syntax
        // return optional.isPresent() ?
        //     ResponseEntity.ok(optional.get()) :
        //     ResponseEntity.notFound().build();

        //// Lambda Syntax
        return optional.map(
            asset -> ResponseEntity.ok(asset))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Asset>> getByType(@PathVariable("type") String type){

        List<Asset> assets = service.getByType(type);

        
        return assets.isEmpty() ? 
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(assets); 
    }

    @GetMapping("/typeDTO/{type}")
    public ResponseEntity<List<AssetDTO>> getByTypeDTO(@PathVariable("type") String type){

        List<AssetDTO> assets = service.getByTypeDTO(type);

        return assets.isEmpty() ? 
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(assets); 
    }

    @PostMapping("/register/")
    public String registerAsset(@RequestBody Asset asset) {
        asset = service.register(asset);
        return "Asset Registered! ID=" + asset.getId();
        
    }

    // Same path of GetById but the request type must be PUT
    @PutMapping("/{id}")
    public String updateAsset(@PathVariable("id") Long id, @RequestBody Asset asset) {
        asset = service.update(asset);
        return "Asset Updated! ID=" + asset.getId();
        
    }

    // Same path of GetById but the request type must be DELETE
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return service.delete(id);
    }
    
}
