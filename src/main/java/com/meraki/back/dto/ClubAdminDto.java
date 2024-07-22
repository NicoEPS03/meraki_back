package com.meraki.back.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ClubAdminDto implements Serializable {
    private Integer id;
    private String name;
    private String sport;
    private String municipio;
    private Boolean coach;
    private Boolean state;
}
