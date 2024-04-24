package com.meraki.back.controller;

import com.meraki.back.entity.City;
import com.meraki.back.entity.Sport;
import com.meraki.back.service.IGeneralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
}
