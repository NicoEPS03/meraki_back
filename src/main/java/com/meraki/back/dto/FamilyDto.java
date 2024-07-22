package com.meraki.back.dto;

import com.meraki.back.entity.DocumentType;
import lombok.Data;

@Data
public class FamilyDto {
    private Integer id;
    private Integer idAthlete;
    private String name;
    private String document;
    private DocumentType documentType;
    private String phone;
    private String email;
    private String company;
    private String occupation;
    private String relationship;
    private Boolean state;
}
