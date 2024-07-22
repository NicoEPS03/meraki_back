package com.meraki.back.controller;

import com.meraki.back.entity.User;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping(value = "/getPage/{page}/{size}", produces = "application/json")
    @Operation(description = "Return all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  User.class)) })})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<User> listUser = service.retornarPaginado(page, size);
        return new ResponseEntity<Page<User>>(listUser, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Return user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  User.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the user")})
    public ResponseEntity<?> retornarUser(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        User user = service.retonarPorId(id);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The user created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  User.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created")})
    public ResponseEntity<?> guardar(@Valid @RequestBody User user) throws IntegridadException, Exception {
        service.guardar(user);

        return new ResponseEntity<Object>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The user edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the user"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created")})
    public ResponseEntity<?> editar(@Valid @RequestBody User user) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(user);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The user deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the user")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
