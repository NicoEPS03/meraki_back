package com.meraki.back.service;

import com.meraki.back.entity.City;
import com.meraki.back.entity.Sport;

import java.util.List;

public interface IGeneralService {
    public List<Sport> listaDeportes();

    public List<City> listarCiudadesDeporte(int sportId);
}
