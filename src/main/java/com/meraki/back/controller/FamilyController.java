package com.meraki.back.controller;

import com.meraki.back.dto.AthleteDto;
import com.meraki.back.dto.FamilyDto;
import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.Family;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IFamilyService;
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
@RequestMapping("/family")
@Validated
public class FamilyController {
    @Autowired
    private IFamilyService service;

    @GetMapping(value = "/getPage/{page}/{size}", produces = "application/json")
    @ApiOperation(value = "Get all familys of all athletes", notes = "Return all of all athletes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class)})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<FamilyDto> listAthlete = service.retornarPaginadoALL(page, size);
        return new ResponseEntity<Page<FamilyDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageAthlete/{page}/{size}/{athlete}", produces = "application/json")
    @ApiOperation(value = "Get all familys of one athlete", notes = "Return all familys of one athlete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class)})
    public ResponseEntity<?> retonarPaginadoAthlete(@PathVariable int page, @PathVariable int size, @PathVariable int athlete) {
        Page<FamilyDto> listAthlete = service.retornarPaginadoFamily(page, size, athlete);
        return new ResponseEntity<Page<FamilyDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get family", notes = "Return family by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Family.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the family")})
    public ResponseEntity<?> retornarFamily(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        FamilyDto family = service.retonarPorIdDto(id);

        return new ResponseEntity<Object>(family, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert family", notes = "Create a new family")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The family created correctly", response = Athlete.class),
            @ApiResponse(code = 409, message = "Conflict. The document already created"),
            @ApiResponse(code = 409, message = "Conflict. The athlete dont exist"),
            @ApiResponse(code = 409, message = "Conflict. The email all ready exists")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Family family) throws IntegridadException, Exception {
        service.guardar(family);

        return new ResponseEntity<Object>(family, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit family", notes = "Edit a family")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The athlete edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the familiar"),
            @ApiResponse(code = 409, message = "Conflict. The document already created"),
            @ApiResponse(code = 409, message = "Conflict. The athlete dont exist")})
    public ResponseEntity<?> editar(@Valid @RequestBody Family family) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(family);

        return new ResponseEntity<Object>(family, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete family", notes = "Delete family by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The family deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the family")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
