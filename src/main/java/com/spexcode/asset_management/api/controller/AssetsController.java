package com.spexcode.asset_management.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spexcode.asset_management.api.exception.EntityNotFoundException;
import com.spexcode.asset_management.api.service.AssetsService;
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
    public ResponseEntity<List<AssetDTO>> getDTO() {
        List<AssetDTO> assets = service.getAllDTO();

        return assets.isEmpty() ? 
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(assets); 
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

    @GetMapping("/dto/{id}")
    public ResponseEntity<AssetDTO> getDTOById(@PathVariable("id") Long id) throws EntityNotFoundException {
        AssetDTO assetDTO = service.getDTOById(id);
        return ResponseEntity.ok(assetDTO);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Asset>> getByType(@PathVariable("type") String type){

        List<Asset> assets = service.getByType(type);

        return assets.isEmpty() ? 
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(assets); 
    }

    @GetMapping("/typeDTO/{type}")
    public ResponseEntity<List<AssetDTO>> getByTypeDTO(@PathVariable("type") String type){

        List<AssetDTO> assets = service.getByTypeDTO(type);

        return assets.isEmpty() ? 
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(assets); 
    }

    @PostMapping("/register")
    @Secured({"ROLE_ADMIN"}) // This must be the exact name of role 
    public ResponseEntity<AssetDTO> registerAsset(@RequestBody Asset asset) {
        asset = service.register(asset);
        return ResponseEntity.created(getURI(asset.getId())).build();
    }

    /**
     * Returns he URI to access the registered resource by id
     * @param id the database id from a registered resource
     * @return the URI to access the registered resource
     */
    public static URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentServletMapping().path("/assets/v1/items/{id}").buildAndExpand(id).toUri();
    }

    // Same path of GetById but the request type must be PUT
    @PutMapping("/")
    public ResponseEntity<AssetDTO> updateAsset(@RequestBody Asset asset) {
       
        try {
            AssetDTO assetDTO = service.update(asset);
            return ResponseEntity.ok(assetDTO);

        } catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }

    }

    // Same path of GetById but the request type must be DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
