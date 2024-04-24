package com.meraki.back.service.imp;


import com.meraki.back.dto.ClubAdminDto;
import com.meraki.back.dto.ClubFilterDto;
import com.meraki.back.entity.Club;
import com.meraki.back.entity.Coach;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IClubRepo;
import com.meraki.back.repository.ICoachRepo;
import com.meraki.back.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IClubServiceImp implements IClubService {

    @Autowired
    private IClubRepo repoClub;

    @Autowired
    private ICoachRepo repoCoach;

    private Boolean validarExistenciaPorId(int id) {
        return repoClub.existsById(id);
    }

    @Override
    public Page<ClubAdminDto> retornarPaginadoAdminDto(int page, int size) {
        Page<Club> result = repoClub.findAll(PageRequest.of(page, size));
        Page<ClubAdminDto> resultDto = result.map(this::convertToClubDtoAdmin);
        return resultDto;
    }

    @Override
    public Page<ClubFilterDto> retornarPaginadoFilterDto(int page, int size) {
        Page<Club> result = repoClub.findAllByState(true, PageRequest.of(page, size));
        Page<ClubFilterDto> resultDto = result.map(this::convertToClubDtoFilter);
        return resultDto;
    }

    @Override
    public Page<Club> retornarPaginado(int page, int size) {
        Page<Club> result = repoClub.findAll(PageRequest.of(page, size));
        return result;
    }

    @Override
    public Club retonarPorId(Integer id) throws ModelNotFoundException {
        if (this.repoClub.existsById(id)) {
            Club club = (Club) this.repoClub.findById(id).get();
            return club;
        } else
            throw new ModelNotFoundException("Club not found");
    }

    @Override
    public void guardar(Club club) throws IntegridadException {
        club.setState(true);
        this.repoClub.save(club);
    }

    @Override
    public void editar(Club club) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if (club.getId() != null) {
            if (validarExistenciaPorId(club.getId())) {
                club.setState(true);
                this.repoClub.save(club);
            } else
                throw new ModelNotFoundException("Club not found");
        } else {
            throw new ArgumentRequiredException("IdClub is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if (validarExistenciaPorId(id)) {
            Club club = this.repoClub.findById(id).get();
            club.setState(false);
            this.repoClub.save(club);
        } else
            throw new ModelNotFoundException("Club not found");
    }

    private ClubAdminDto convertToClubDtoAdmin(final Club club) {
        final ClubAdminDto clubDto = new ClubAdminDto();
        clubDto.setId(club.getId());
        clubDto.setName(club.getName());
        clubDto.setMunicipio(club.getCity().getNombre());
        clubDto.setSport(club.getSport().getName());
        return clubDto;
    }

    private ClubFilterDto convertToClubDtoFilter(final Club club) {
        final ClubFilterDto clubDto = new ClubFilterDto();
        clubDto.setId(club.getId());
        clubDto.setName(club.getName());
        clubDto.setDescription(club.getDescription());
        clubDto.setMunicipio(club.getCity().getNombre());
        clubDto.setSport(club.getSport().getName());
        Coach coach = repoCoach.getCoach(club.getId());
        clubDto.setDelegado((Objects.nonNull(coach)) ? repoCoach.getCoach(club.getId()).getName() + repoCoach.getCoach(club.getId()).getLastName() : "Sin delegado");
        return clubDto;
    }

    @Override
    public Page<ClubFilterDto> retornarPaginadoSport(int page, int size, int sport) {
        Page<Club> result = repoClub.searchBySport(sport, PageRequest.of(page, size));
        Page<ClubFilterDto> resultDto = result.map(this::convertToClubDtoFilter);
        return resultDto;
    }

    @Override
    public Page<ClubFilterDto> retornarPaginadoCity(int page, int size, int city) {
        Page<Club> result = repoClub.searchByCity(city, PageRequest.of(page, size));
        Page<ClubFilterDto> resultDto = result.map(this::convertToClubDtoFilter);
        return resultDto;
    }

    @Override
    public Page<ClubFilterDto> retornarPaginadoSportAndCity(int page, int size, int sport, int city) {
        Page<Club> result = repoClub.searchBySportAndCity(sport, city, PageRequest.of(page, size));
        Page<ClubFilterDto> resultDto = result.map(this::convertToClubDtoFilter);
        return resultDto;
    }
}
