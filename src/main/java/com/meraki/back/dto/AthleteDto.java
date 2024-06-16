package com.meraki.back.dto;

import lombok.Data;

@Data
public class AthleteDto {
    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String club;
}
