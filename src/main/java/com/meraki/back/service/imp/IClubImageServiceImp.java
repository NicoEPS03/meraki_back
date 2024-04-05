package com.meraki.back.service.imp;

import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.Club;
import com.meraki.back.entity.ClubImages;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IClubImagesRepo;
import com.meraki.back.repository.IClubRepo;
import com.meraki.back.service.IClubImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IClubImageServiceImp implements IClubImagesService {
    @Autowired
    private IClubImagesRepo repoClubImages;
    @Autowired
    private IClubRepo repoClub;

    private Boolean validarExistenciaPorId(int id) {
        return repoClubImages.existsById(id);
    }

    private Boolean validarEquipoActivo(int id) {
        Club club = this.repoClub.findById(id).get();
        return club.getState();
    }

    @Override
    public Page<ClubImages> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public ClubImages retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(ClubImages clubImages) throws IntegridadException {
        if (!validarEquipoActivo(clubImages.getClub().getId())) {
            throw new IntegridadException("Inactive Club");
        }
        if (repoClub.findById(clubImages.getClub().getId()).isEmpty()) {
            throw new IntegridadException("Club dont exist");
        }
        if (repoClubImages.numberOtherImages(clubImages.getClub().getId()) >= 2 && clubImages.getOther()) {
            throw new IntegridadException("Maximum number of images");
        }
        if (repoClubImages.numberBannerImages(clubImages.getClub().getId()) >= 1 && clubImages.getBanner()) {
            throw new IntegridadException("Maximum number of banner image");
        }
        if (repoClubImages.numberLogoImages(clubImages.getClub().getId()) >= 1 && clubImages.getLogo()) {
            throw new IntegridadException("Maximum number of logo image");
        }
        clubImages.setState(true);
        this.repoClubImages.save(clubImages);
    }

    @Override
    public void editar(ClubImages clubImages) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if (clubImages.getId() != null) {
            if (validarExistenciaPorId(clubImages.getId())) {
                if (repoClub.findById(clubImages.getClub().getId()).isEmpty()) {
                    throw new IntegridadException("Club dont exist");
                }
                clubImages.setState(true);
                this.repoClubImages.save(clubImages);
            } else
                throw new ModelNotFoundException("Images not found");
        } else {
            throw new ArgumentRequiredException("IdImages is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if (validarExistenciaPorId(id)) {
            ClubImages clubImages = this.repoClubImages.findById(id).get();
            clubImages.setState(false);
            this.repoClubImages.save(clubImages);
        } else
            throw new ModelNotFoundException("Image not found");
    }

    @Override
    public List<ClubImages> retonarPorIdClub(int idClub) {
        List<ClubImages> clubImagesList = this.repoClubImages.clubImages(idClub);
        return clubImagesList;
    }
}