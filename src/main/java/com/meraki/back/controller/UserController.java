package com.meraki.back.controller;

import com.meraki.back.entity.User;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping(value = "/getPage/{page}/{size}" ,produces = "application/json")
    @ApiOperation(value = "Get all users", notes = "Return all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = User.class)})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<User> listUser = service.retornarPaginado(page, size);
        return new ResponseEntity<Page<User>>(listUser, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get user", notes = "Retorn user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = User.class ),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the user") })
    public ResponseEntity<?> retornarUser(@PathVariable("id") int id ) throws ModelNotFoundException, Exception {
        User user = service.retonarPorId(id);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert user", notes = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The user created correctly", response = User.class ),
            @ApiResponse(code = 409, message = "Conflict. The document already created") })
    public ResponseEntity<?> guardar (@Valid @RequestBody User user) throws IntegridadException, Exception {
        service.guardar(user);

        return new ResponseEntity<Object>(user, HttpStatus.CREATED);
    }
    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit user", notes = "Edit a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The user edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the user"),
            @ApiResponse(code = 409, message = "Conflict. The document already created")})
    public ResponseEntity<?> editar (@Valid @RequestBody User user) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(user);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    @ApiOperation(value = "Delete user", notes = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The user deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the user") })
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
