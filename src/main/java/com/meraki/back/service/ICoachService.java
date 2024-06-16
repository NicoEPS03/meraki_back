package com.meraki.back.service;

import com.meraki.back.dto.CoachDto;
import com.meraki.back.entity.Coach;
import com.meraki.back.exception.ModelNotFoundException;
import org.springframework.data.domain.Page;

public interface ICoachService extends ICrud<Coach, Integer> {
    public CoachDto retornarCoachClub(int idClub) throws ModelNotFoundException;

    public CoachDto retornarCoachUser(int idUser) throws ModelNotFoundException;

    public Page<CoachDto> retornarPaginadoDto(int page, int size);
}
