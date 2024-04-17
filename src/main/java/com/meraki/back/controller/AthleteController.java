package com.meraki.back.controller;

import com.meraki.back.dto.AthleteDto;
import com.meraki.back.entity.Athlete;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IAthleteService;
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

@RestController
@RequestMapping("/athlete")
@Validated
public class AthleteController {
    @Autowired
    private IAthleteService service;

    @GetMapping(value = "/getPageAdmin/{page}/{size}", produces = "application/json")
    @Operation(description = "Get all athletes admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Athlete.class)) })})
    public ResponseEntity<?> retonarPaginadoAdmin(@PathVariable int page, @PathVariable int size) {
        Page<AthleteDto> listAthlete = service.retornarPaginadoDtoAdmin(page, size);
        return new ResponseEntity<Page<AthleteDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageClub/{page}/{size}/{club}", produces = "application/json")
    @Operation(description = "Get all athletes club")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Athlete.class)) } )})
    public ResponseEntity<?> retonarPaginadoClub(@PathVariable int page, @PathVariable int size, @PathVariable int club) {
        Page<AthleteDto> listAthlete = service.retornarPaginadoDtoClub(page, size, club);
        return new ResponseEntity<Page<AthleteDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Get athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> retornarAthlete(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Athlete athlete = service.retonarPorId(id);

        return new ResponseEntity<Object>(athlete, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Insert athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The athlete created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Athlete.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created"),
            @ApiResponse(responseCode = "409", description = "Conflict. The club dont exist"),
            @ApiResponse(responseCode = "409", description = "Conflict. Maximum number of athletes")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Athlete athlete) throws IntegridadException, Exception {
        service.guardar(athlete);

        return new ResponseEntity<Object>(athlete, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The athlete edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created")})
    public ResponseEntity<?> editar(@Valid @RequestBody Athlete athlete) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(athlete);

        return new ResponseEntity<Object>(athlete, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The athlete deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
