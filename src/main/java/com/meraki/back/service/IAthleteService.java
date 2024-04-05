package com.meraki.back.service;

import com.meraki.back.dto.AthleteDto;
import com.meraki.back.entity.Athlete;
import org.springframework.data.domain.Page;

public interface IAthleteService extends ICrud<Athlete, Integer> {
    public Page<AthleteDto> retornarPaginadoDtoAdmin(int page, int size);
    public Page<AthleteDto> retornarPaginadoDtoClub(int page, int size, int club);
}
