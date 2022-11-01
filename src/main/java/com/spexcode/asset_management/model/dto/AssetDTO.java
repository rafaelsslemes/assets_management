package com.spexcode.asset_management.model.dto;

import com.spexcode.asset_management.model.Asset;

// A summary structure that represents a class
// tipcaly with some fields
public class AssetDTO {
    private Long id;
    private String description;

    public AssetDTO(Asset asset) {
        this.id = asset.getId();
        this.description = asset.getDescription();
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
