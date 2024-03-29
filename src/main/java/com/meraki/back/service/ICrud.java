package com.meraki.back.service;

import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import org.springframework.data.domain.Page;

public interface ICrud<T,ID> {
    public Page<T> retornarPaginado(int page, int size);

    public T retonarPorId(ID id) throws ModelNotFoundException;

    public void guardar(T objeto)  throws IntegridadException;

    public void editar(T objeto)  throws ArgumentRequiredException, ModelNotFoundException, IntegridadException;

    public void eliminar(int idObjeto) throws ModelNotFoundException, ArgumentRequiredException;
}
