package com.meraki.back.service.imp;


import com.meraki.back.entity.Club;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IClubService;
import org.springframework.data.domain.Page;

public class IClubServiceImp implements IClubService {
    @Override
    public Page<Club> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Club retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(Club objeto) throws IntegridadException {

    }

    @Override
    public void editar(Club objeto) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {

    }

    @Override
    public void eliminar(int idObjeto) throws ModelNotFoundException, ArgumentRequiredException {

    }
}
