package com.meraki.back.service.imp;

import com.meraki.back.dto.FamilyDto;
import com.meraki.back.entity.Family;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IAthleteRepo;
import com.meraki.back.repository.IFamilyRepo;
import com.meraki.back.repository.IUserRepo;
import com.meraki.back.service.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class IFamilyServiceImp implements IFamilyService {
    @Autowired
    private IFamilyRepo repoFamily;
    @Autowired
    private IUserRepo repoUser;
    @Autowired
    private IAthleteRepo repoAthlete;

    private Boolean validarExistenciaPorId(int id) {
        return repoFamily.existsById(id);
    }

    @Override
    public Page<Family> retornarPaginado(int page, int size) {
        Page<Family> result = repoFamily.findAll(PageRequest.of(page, size));
        return result;
    }

    @Override
    public Family retonarPorId(Integer id) throws ModelNotFoundException {
        if (this.repoFamily.existsById(id)) {
            Family family = (Family) this.repoFamily.findById(id).get();
            return family;
        } else
            throw new ModelNotFoundException("Family not found");
    }

    @Override
    public FamilyDto retonarPorIdDto(Integer id) throws ModelNotFoundException {
        if (this.repoFamily.existsById(id)) {
            Family family = (Family) this.repoFamily.findById(id).get();
            final FamilyDto familyDto = new FamilyDto();
            familyDto.setId(family.getId());
            familyDto.setIdAthlete(family.getAthlete().getId());
            familyDto.setName(family.getName());
            familyDto.setDocument(family.getDocument());
            familyDto.setDocumentType(family.getDocumentType().getDescription());
            familyDto.setPhone(family.getPhone());
            familyDto.setEmail(family.getEmail());
            familyDto.setCompany(family.getCompany());
            familyDto.setOccupation(family.getOccupation());
            familyDto.setRelationship(family.getRelationship());
            familyDto.setState(family.getState());
            return familyDto;
        } else
            throw new ModelNotFoundException("Family not found");
    }

    @Override
    public void guardar(Family family) throws IntegridadException {
        if (repoFamily.findByDocument(family.getDocument()) != null || repoUser.findByDocument(family.getDocument()) != null || repoFamily.findByDocument(family.getDocument()) != null) {
            throw new IntegridadException("Document all ready exist");
        }
        if (repoFamily.existsEmailInsert(family.getEmail()) != 0) {
            throw new IntegridadException("Email all ready exists");
        }
        if (repoAthlete.findById(family.getAthlete().getId()).isEmpty()) {
            throw new IntegridadException("Athlete dont exist");
        }
        family.setState(true);
        this.repoFamily.save(family);
    }

    @Override
    public void editar(Family family) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if (family.getId() != null) {
            if (validarExistenciaPorId(family.getId())) {
                if (repoAthlete.findById(family.getAthlete().getId()).isEmpty()) {
                    throw new IntegridadException("Athlete dont exist");
                }
                if (repoFamily.searchDocument(family.getId(), family.getDocument()) != 1 && repoUser.findByDocument(family.getDocument()) == null && repoAthlete.findByDocument(family.getDocument()) == null) {
                    family.setState(true);
                    this.repoFamily.save(family);
                } else {
                    throw new IntegridadException("Document all ready exist");
                }
            } else
                throw new ModelNotFoundException("Familiar not found");
        } else {
            throw new ArgumentRequiredException("IdFamily is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if (validarExistenciaPorId(id)) {
            Family family = (Family) this.repoFamily.findById(id).get();
            family.setState(false);
            this.repoFamily.save(family);
        } else
            throw new ModelNotFoundException("Family not found");
    }

    @Override
    public Page<FamilyDto> retornarPaginadoFamily(int page, int size, int idAthlete) {
        Page<Family> result = repoFamily.findAllStateTrue(idAthlete, PageRequest.of(page, size));
        Page<FamilyDto> familyDtos = result.map(this::convertToFamilyDto);
        return familyDtos;
    }

    @Override
    public Page<FamilyDto> retornarPaginadoALL(int page, int size) {
        Page<Family> result = repoFamily.findAll(PageRequest.of(page, size));
        Page<FamilyDto> familyDtos = result.map(this::convertToFamilyDto);
        return familyDtos;
    }

    private FamilyDto convertToFamilyDto(final Family family) {
        final FamilyDto familyDto = new FamilyDto();
        familyDto.setId(family.getId());
        familyDto.setIdAthlete(family.getAthlete().getId());
        familyDto.setName(family.getName());
        familyDto.setDocument(family.getDocument());
        familyDto.setDocumentType(family.getDocumentType().getDescription());
        familyDto.setPhone(family.getPhone());
        familyDto.setEmail(family.getEmail());
        familyDto.setCompany(family.getCompany());
        familyDto.setOccupation(family.getOccupation());
        familyDto.setRelationship(family.getRelationship());
        familyDto.setState(family.getState());
        return familyDto;
    }
}
