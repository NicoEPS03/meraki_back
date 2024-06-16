package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "city")
@ApiModel("Model city")
public class City {
    @Id
    @Column(name = "CT_ID", length = 15, nullable = false, unique = true)
    private Integer id;
    @Column(name = "CT_IDDEP", length = 11, nullable = false)
    private Integer idDep;
    @Column(name = "CT_COD", length = 3, nullable = false)
    private String codigo;
    @Column(name = "CT_NAME", length = 200, nullable = false)
    private String nombre;
    @Column(name = "CT_STATE", length = 15, nullable = false)
    private Boolean estado;

}
