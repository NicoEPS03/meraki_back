package com.meraki.back.service.imp;

import com.meraki.back.dto.AthleteDto;
import com.meraki.back.entity.Athlete;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IAthleteRepo;
import com.meraki.back.repository.IClubRepo;
import com.meraki.back.repository.IUserRepo;
import com.meraki.back.service.IAthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IAthleteServiceImp implements IAthleteService {

    @Autowired
    private IAthleteRepo repoAthlete;
    @Autowired
    private IClubRepo repoClub;
    @Autowired
    private IUserRepo repoUser;

    private Boolean validarExistenciaPorId(int id) {
        return repoAthlete.existsById(id);
    }

    @Override
    public Page<Athlete> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Athlete retonarPorId(Integer id) throws ModelNotFoundException {
        if (this.repoAthlete.existsById(id)) {
            Athlete athlete = (Athlete) this.repoAthlete.findById(id).get();
            return athlete;
        } else
            throw new ModelNotFoundException("Athlete not found");
    }

    @Override
    public void guardar(Athlete athlete) throws IntegridadException {
        if (repoAthlete.findByDocumentAndState(athlete.getDocument(), true) != null || repoUser.findByDocumentAndState(athlete.getDocument(),true) != null) {
            throw new IntegridadException("Document all ready exist");
        }
        if (repoClub.findById(athlete.getClub().getId()).isEmpty()) {
            throw new IntegridadException("Club dont exist");
        }
        if (repoAthlete.searchAthlete(athlete.getClub().getId()) > 20) {
            throw new IntegridadException("Maximum number of athletes");
        }
        athlete.setState(true);
        this.repoAthlete.save(athlete);
    }

    @Override
    public void editar(Athlete athlete) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if (athlete.getId() != null) {
            if (validarExistenciaPorId(athlete.getId())) {
                if (repoAthlete.searchDocument(athlete.getId(), athlete.getDocument()) != 1 && repoUser.findByDocument(athlete.getDocument()) == null) {
                    athlete.setState(true);
                    this.repoAthlete.save(athlete);
                } else {
                    throw new IntegridadException("Document all ready exist");
                }
            } else
                throw new ModelNotFoundException("Athlete not found");
        } else {
            throw new ArgumentRequiredException("IdAthlete is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if (validarExistenciaPorId(id)) {
            Athlete athlete = this.repoAthlete.findById(id).get();
            athlete.setState(false);
            this.repoAthlete.save(athlete);
        } else
            throw new ModelNotFoundException("Athlete not found");
    }

    @Override
    public Page<AthleteDto> retornarPaginadoDtoAdmin(int page, int size) {
        Page<Athlete> resultado = repoAthlete.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "club")));
        Page<AthleteDto> athleteDtos = resultado.map(this::convertToAthleteDto);
        return athleteDtos;
    }

    @Override
    public Page<AthleteDto> retornarPaginadoDtoClub(int page, int size, int club) {
        Page<Athlete> resultado = repoAthlete.findAllStateTrue(club, PageRequest.of(page, size));
        Page<AthleteDto> athleteDtos = resultado.map(this::convertToAthleteDto);
        return athleteDtos;
    }

    private AthleteDto convertToAthleteDto(final Athlete athlete) {
        final AthleteDto athleteDto = new AthleteDto();
        athleteDto.setId(athlete.getId());
        athleteDto.setName(athlete.getName());
        athleteDto.setLastName(athlete.getLastName());
        athleteDto.setClub(athlete.getClub().getName());
        athleteDto.setDocument(athlete.getDocument());
        return athleteDto;
    }
}
