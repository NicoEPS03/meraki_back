package com.meraki.back.service.imp;

import com.meraki.back.dto.CoachDto;
import com.meraki.back.entity.Club;
import com.meraki.back.entity.Coach;
import com.meraki.back.entity.Rol;
import com.meraki.back.entity.User;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IClubRepo;
import com.meraki.back.repository.ICoachRepo;
import com.meraki.back.repository.IUserRepo;
import com.meraki.back.service.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ICoachServiceImp implements ICoachService {
    @Autowired
    private ICoachRepo repoCoach;
    @Autowired
    private IClubRepo repoClub;
    @Autowired
    private IUserRepo repoUser;

    private Boolean validarExistenciaPorId(int id) {
        return repoCoach.existsById(id);
    }

    private Boolean validarEquipoActivo(int id) {
        Club club = this.repoClub.findById(id).get();
        return club.getState();
    }

    @Override
    public Page<Coach> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Page<CoachDto> retornarPaginadoDto(int page, int size) {
        Page<Coach> resultado = repoCoach.findAll(PageRequest.of(page, size));
        Page<CoachDto> coachDtos = resultado.map(this::convertToCoachDto);
        return coachDtos;
    }

    @Override
    public Coach retonarPorId(Integer id) throws ModelNotFoundException {
        if (this.repoCoach.existsById(id)) {
            Coach coach = (Coach) this.repoCoach.findById(id).get();
            return coach;
        } else
            throw new ModelNotFoundException("Coach not found");
    }

    @Override
    public void guardar(Coach coach) throws IntegridadException {
        if (validarEquipoActivo(coach.getClub().getId())) {
            if (repoUser.findByDocument(coach.getUser().getDocument()) != null) {
                throw new IntegridadException("Document all ready exist");
            }
            if (repoCoach.coachExits(coach.getClub().getId()) != 0) {
                throw new IntegridadException("Coach to this all ready exist");
            }
            Rol rol = new Rol();
            rol.setId(2);
            coach.getUser().setRol(rol);
            coach.getUser().setState(true);
            this.repoCoach.save(coach);
        } else {
            throw new IntegridadException("Inactive Club");
        }
    }

    @Override
    public void editar(Coach coach) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if (coach.getId() != null) {
            if (repoClub.findById(coach.getClub().getId()).isEmpty()) {
                throw new IntegridadException("Club dont exist");
            }
            if (validarEquipoActivo(coach.getClub().getId())) {
                if (repoUser.findByDocumentAlready(coach.getUser().getDocument(), coach.getUser().getId()) != null) {
                    throw new IntegridadException("Document all ready exist");
                }
                if (repoCoach.coachAlreadyExits(coach.getClub().getId(), coach.getId()) != 0) {
                    throw new IntegridadException("Coach to this club all ready exist");
                }

                if (validarExistenciaPorId(coach.getId())) {
                    coach.getUser().setState(true);
                    coach.getClub().setState(true);
                    this.repoCoach.save(coach);
                } else
                    throw new ModelNotFoundException("Coach not found");
            } else {
                throw new IntegridadException("Inactive Club");
            }
        } else {
            throw new ArgumentRequiredException("IdCoach is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if (validarExistenciaPorId(id)) {
            Coach coach = this.repoCoach.findById(id).get();
            coach.getUser().setState(false);
            this.repoCoach.save(coach);
        } else
            throw new ModelNotFoundException("Coach not found");
    }

    @Override
    public CoachDto retornarCoachClub(int idClub) throws ModelNotFoundException {
        CoachDto coachDto = new CoachDto();
        Coach coach = this.repoCoach.getCoach(idClub);
        if (Objects.nonNull(coach)) {
            coachDto.setId(coach.getId());
            coachDto.setName(coach.getName());
            coachDto.setLastName(coach.getLastName());
            return coachDto;
        } else {
            CoachDto co = new CoachDto();
            return co;
        }
    }

    private CoachDto convertToCoachDto(final Coach coach) {
        final CoachDto coachDto = new CoachDto();
        coachDto.setId(coach.getId());
        coachDto.setName(coach.getName());
        coachDto.setLastName(coach.getLastName());
        coachDto.setClub(coach.getClub().getName());
        coachDto.setDocument(coach.getUser().getDocument());
        return coachDto;
    }
}
