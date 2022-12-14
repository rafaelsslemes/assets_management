package com.spexcode.asset_management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_sequence")
    @SequenceGenerator(name="asset_sequence", sequenceName="asset_seq")
    private Long id;

    // Se o nome da coluna for diferente do campo
    // @Column(name = "description")
    private String description;

    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
