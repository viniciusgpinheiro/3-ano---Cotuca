package com.example.produtoAPI.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="unidade")
public class Unidade {

    @Id
    @Column
    private Integer unitId;

    @Column
    private String description;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "unitId=" + unitId +
                ", description='" + description + '\'' +
                '}';
    }
}