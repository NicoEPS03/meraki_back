package com.meraki.back.controller;

import com.meraki.back.dto.AthleteDto;
import com.meraki.back.entity.Athlete;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IAthleteService;
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
@RequestMapping("/athlete")
@Validated
public class AthleteController {
    @Autowired
    private IAthleteService service;

    @GetMapping(value = "/getPageAdmin/{page}/{size}", produces = "application/json")
    @ApiOperation(value = "Get all athletes admin", notes = "Return all athletes admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class)})
    public ResponseEntity<?> retonarPaginadoAdmin(@PathVariable int page, @PathVariable int size) {
        Page<AthleteDto> listAthlete = service.retornarPaginadoDtoAdmin(page, size);
        return new ResponseEntity<Page<AthleteDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageClub/{page}/{size}/{club}", produces = "application/json")
    @ApiOperation(value = "Get all athletes club", notes = "Return all athletes club")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class)})
    public ResponseEntity<?> retonarPaginadoClub(@PathVariable int page, @PathVariable int size, @PathVariable int club) {
        Page<AthleteDto> listAthlete = service.retornarPaginadoDtoClub(page, size, club);
        return new ResponseEntity<Page<AthleteDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get athlete", notes = "Return athlete by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> retornarAthlete(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Athlete athlete = service.retonarPorId(id);

        return new ResponseEntity<Object>(athlete, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert athlete", notes = "Create a new athlete")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The athlete created correctly", response = Athlete.class),
            @ApiResponse(code = 409, message = "Conflict. The document already created"),
            @ApiResponse(code = 409, message = "Conflict. The club dont exist"),
            @ApiResponse(code = 409, message = "Conflict. Maximum number of athletes")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Athlete athlete) throws IntegridadException, Exception {
        service.guardar(athlete);

        return new ResponseEntity<Object>(athlete, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit athlete", notes = "Edit a athlete")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The athlete edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete"),
            @ApiResponse(code = 409, message = "Conflict. The document already created")})
    public ResponseEntity<?> editar(@Valid @RequestBody Athlete athlete) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(athlete);

        return new ResponseEntity<Object>(athlete, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete athlete", notes = "Delete athlete by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The athlete deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
