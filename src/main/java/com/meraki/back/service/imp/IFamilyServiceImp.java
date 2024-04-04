package com.meraki.back.service.imp;

import com.meraki.back.entity.Family;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IFamilyService;
import org.springframework.data.domain.Page;

public class IFamilyServiceImp implements IFamilyService {
    @Override
    public Page<Family> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Family retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(Family objeto) throws IntegridadException {

    }

    @Override
    public void editar(Family objeto) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {

    }

    @Override
    public void eliminar(int idObjeto) throws ModelNotFoundException, ArgumentRequiredException {

    }
}
