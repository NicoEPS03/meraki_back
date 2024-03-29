package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rol")
@ApiModel("Model rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of rol", example = "1")
    @Column(name = "RL_ID")
    private Integer id;
    @NotNull(message = "Description is obligatory")
    @Size(min = 4, max = 20, message = "The description must be between 4 and 20 characters")
    @Column(name = "RL_DESCRIPTION", length = 20, nullable = false)
    @ApiModelProperty(dataType = "String", value = "Description of rol", example = "Administradoer")
    private String description;

    public Rol() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
