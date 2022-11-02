package com.spexcode.asset_management.model.dto;

import org.modelmapper.ModelMapper;

import com.spexcode.asset_management.model.Asset;

// A summary structure that represents a class
// tipcaly with some fields
public class AssetDTO {
    private Long id;
    private String description;

    public static AssetDTO createDTO(Asset asset) {
        return new ModelMapper().map(asset, AssetDTO.class);
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
