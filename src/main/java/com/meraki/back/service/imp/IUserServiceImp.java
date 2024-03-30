package com.meraki.back.service.imp;

import com.meraki.back.entity.User;
import com.meraki.back.exception.ArgumentRequiredException;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.repository.IUserRepo;
import com.meraki.back.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IUserServiceImp implements IUserService {
    @Autowired
    private IUserRepo repoUser;
    private Boolean validarExistenciaPorId(int id) {
        return repoUser.existsById(id);
    }
    @Override
    public Page<User> retornarPaginado(int page, int size) {
        Page<User> result = repoUser.findAll(PageRequest.of(page,size));
        return result;
    }

    @Override
    public User retonarPorId(Integer id) throws ModelNotFoundException {
        if(this.repoUser.existsById(id)) {
            User user = (User) this.repoUser.findById(id).get();
            return user;
        } else
            throw new ModelNotFoundException("User not found");
    }

    @Override
    public void guardar(User user) throws IntegridadException {
        if (repoUser.findByDocument(user.getDocument()) != null) {
            throw new IntegridadException("Document all ready exist");
        }
        user.setState(true);
        this.repoUser.save(user);
    }

    @Override
    public void editar(User user) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if(user.getId() != null) {
            if(validarExistenciaPorId(user.getId())) {
                if(repoUser.searchDocument(user.getId(), user.getDocument()) != 1) {
                    this.repoUser.save(user);
                }else {
                    throw new IntegridadException("Document all ready exist");
                }
            } else
                throw new ModelNotFoundException("User not found");
        } else {
            throw new ArgumentRequiredException("IdUser is required");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ArgumentRequiredException {
        if(validarExistenciaPorId(id)) {
            User user = this.repoUser.findById(id).get();
            user.setState(false);
            this.repoUser.save(user);
        }else
            throw new ModelNotFoundException("Usuario no encontrado");
    }
}
