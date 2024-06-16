package com.meraki.back.dto;

import com.meraki.back.entity.DocumentType;
import com.meraki.back.entity.User;
import lombok.Data;

@Data
public class CoachDto {
    private Integer id;
    private String name;
    private String lastName;
    private String club;
    private User user;
    private DocumentType documentType;
}
