package com.meraki.back.service.imp;

import com.meraki.back.entity.City;
import com.meraki.back.entity.DocumentType;
import com.meraki.back.entity.Sport;
import com.meraki.back.repository.ICityRepo;
import com.meraki.back.repository.IDocumentTypeRepo;
import com.meraki.back.repository.ISportRepo;
import com.meraki.back.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IGeneralServiceImp implements IGeneralService {
    @Autowired
    private ISportRepo sportRepo;

    @Autowired
    private ICityRepo cityRepo;
    @Autowired
    private IDocumentTypeRepo documentRepo;
    @Override
    public List<Sport> listaDeportes() {
        List <Sport> sports = sportRepo.findAll();
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
    public List<DocumentType> listaDocumentos() {
        List <DocumentType> documentTypes = documentRepo.findAll();
        return documentTypes;
    }
}
