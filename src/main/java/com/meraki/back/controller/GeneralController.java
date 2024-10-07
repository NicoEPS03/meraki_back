package com.meraki.back.controller;

import com.meraki.back.dto.UserDto;
import com.meraki.back.entity.*;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IGeneralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/general")
public class GeneralController {
    @Autowired
    private IGeneralService service;
    @GetMapping(value = "/getSports", produces = "application/json")
    @Operation(description = "Return all sports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Sport.class)) })})
    public ResponseEntity<?> retonarDeportes() {
       List<Sport> sportList = service.listaDeportes();
        return new ResponseEntity<List<Sport>>(sportList, HttpStatus.OK);
    }

    @GetMapping(value = "/getCitiesSport/{id}", produces = "application/json")
    @Operation(description = "Return all cities for one sport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  City.class)) })})
    public ResponseEntity<?> retonarCiudadesDeportes(@PathVariable int id) {
        List<City> cityList = service.listarCiudadesDeporte(id);
        return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
    }

    @GetMapping(value = "/getCities", produces = "application/json")
    @Operation(description = "Return all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  City.class)) })})
    public ResponseEntity<?> retonarCidudaes() {
        List<City> cityList = service.listaCiudades();
        return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
    }

    @GetMapping(value = "/getCities/{id}", produces = "application/json")
    @Operation(description = "Return all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  City.class)) })})
    public ResponseEntity<?> retonarCidudaesId(@PathVariable int id) {
        List<City> cityList = service.listaCiudadesId(id);
        return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
    }

    @GetMapping(value = "/getDocuments", produces = "application/json")
    @Operation(description = "Return all documents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  DocumentType.class)) })})
    public ResponseEntity<?> retonarDocumentos() {
        List<DocumentType> documentTypes = service.listaDocumentos();
        return new ResponseEntity<List<DocumentType>>(documentTypes, HttpStatus.OK);
    }
    @PostMapping(value = "/login", consumes = "application/json")
    @Operation(description = "Login to admin")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) throws ModelNotFoundException {
        User user = service.login(userDto);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
}
