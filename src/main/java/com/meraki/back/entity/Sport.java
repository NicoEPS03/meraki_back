package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sport")
@ApiModel("Model sport")
public class Sport {
    @Id
    @Column(name = "SP_ID", nullable = false, unique = true)
    private Integer id;
    @Column(name = "SP_NAME", length = 30, nullable = false)
    private String name;
    @Column(name = "SP_TEAM", nullable = false)
    private Boolean team;

    public Sport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTeam() {
        return team;
    }

    public void setTeam(Boolean team) {
        this.team = team;
    }
}
