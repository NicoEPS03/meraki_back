package com.meraki.back.dto;

import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.Family;
import lombok.Data;

import java.util.List;

@Data
public class ExcelAthleteDto {
    private Athlete athlete;
    private List<FamilyDto> familyDtoList;
}
