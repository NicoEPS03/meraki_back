package com.meraki.back.service;

import com.meraki.back.dto.FamilyDto;
import com.meraki.back.entity.Family;
import com.meraki.back.exception.ModelNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFamilyService extends ICrud<Family, Integer> {
    public List<FamilyDto> retornarPaginadoFamily(int idAthlete);
    public Page<FamilyDto> retornarPaginadoALL(int page, int size);
    public FamilyDto retonarPorIdDto(Integer id) throws ModelNotFoundException;
}
