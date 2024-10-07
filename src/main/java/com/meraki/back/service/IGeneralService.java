package com.meraki.back.service;

import com.meraki.back.dto.UserDto;
import com.meraki.back.entity.City;
import com.meraki.back.entity.DocumentType;
import com.meraki.back.entity.Sport;
import com.meraki.back.entity.User;
import com.meraki.back.exception.ModelNotFoundException;

import java.util.List;

public interface IGeneralService {
    public List<Sport> listaDeportes();

    public List<City> listarCiudadesDeporte(int sportId);

    public List<City> listaCiudades();

    public List<City> listaCiudadesId(Integer Id);

    public List<DocumentType> listaDocumentos();

    public User login(UserDto userDto) throws ModelNotFoundException;
}
