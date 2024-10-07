package com.meraki.back.service.imp;

import com.meraki.back.dto.UserDto;
import com.meraki.back.entity.City;
import com.meraki.back.entity.DocumentType;
import com.meraki.back.entity.Sport;
import com.meraki.back.entity.User;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.ICityRepo;
import com.meraki.back.repository.IDocumentTypeRepo;
import com.meraki.back.repository.ISportRepo;
import com.meraki.back.repository.IUserRepo;
import com.meraki.back.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IGeneralServiceImp implements IGeneralService {
    @Autowired
    private ISportRepo sportRepo;
    @Autowired
    private ICityRepo cityRepo;
    @Autowired
    private IDocumentTypeRepo documentRepo;
    @Autowired
    private IUserRepo userRepo;
    @Override
    public List<Sport> listaDeportes() {
        List <Sport> sports = sportRepo.findAllSportOrderAsc();
        return sports;
    }

    @Override
    public List<City> listarCiudadesDeporte(int sportId) {
        List<City> cities = cityRepo.listsCitys(sportId);
        return cities;
    }

    @Override
    public List<City> listaCiudades() {
        List <City> cities = cityRepo.findAll();
        return cities;
    }

    @Override
    public List<City> listaCiudadesId(Integer id) {
        List <City> cities = cityRepo.listsCityId(id);
        return cities;
    }

    @Override
    public List<DocumentType> listaDocumentos() {
        List <DocumentType> documentTypes = documentRepo.findAll();
        return documentTypes;
    }

    @Override
    public User login(UserDto userDto) throws ModelNotFoundException{
        User user = userRepo.login(userDto.getDocument(), userDto.getPassword());
        if (Objects.isNull(user)){
            throw new ModelNotFoundException("User not found");
        }
        return user;
    }
}
