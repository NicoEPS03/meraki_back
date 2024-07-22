package com.meraki.back.dto;

import com.meraki.back.entity.ClubImages;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClubFilterDto implements Serializable {
    private Integer id;
    private List<ClubImages> images;
    private String name;
    private String description;
    private String sport;
    private String municipio;
    private String delegado;
}
