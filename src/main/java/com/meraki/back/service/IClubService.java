package com.meraki.back.service;

import com.meraki.back.dto.ClubAdminDto;
import com.meraki.back.dto.ClubFilterDto;
import com.meraki.back.entity.Club;
import org.springframework.data.domain.Page;

public interface IClubService extends ICrud<Club, Integer>{
    public Page<ClubAdminDto> retornarPaginadoAdminDto(int page, int size);
    public Page<ClubFilterDto> retornarPaginadoFilterDto(int page, int size);
    public Page<ClubFilterDto> retornarPaginadoSport(int page, int size, int sport);
    public Page<ClubFilterDto> retornarPaginadoCity(int page, int size, int city);
    public Page<ClubFilterDto> retornarPaginadoSportAndCity(int page, int size, int sport, int city);
}
