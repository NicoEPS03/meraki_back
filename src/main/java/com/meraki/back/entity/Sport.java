package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
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

}
