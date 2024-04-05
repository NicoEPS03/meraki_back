package com.meraki.back.controller;

import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.ClubImages;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IClubImagesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/clubImages")
@Validated
public class ClubImagesController {
    @Autowired
    private IClubImagesService service;

    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get all club images", notes = "Return all club images by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Athlete.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> retornarClubImages(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        List<ClubImages> clubImages = service.retonarPorIdClub(id);

        return new ResponseEntity<Object>(clubImages, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert club image", notes = "Create a new club image")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The club image created correctly", response = Athlete.class),
            @ApiResponse(code = 409, message = "Conflict. The document already created")})
    public ResponseEntity<?> guardar(@Valid @RequestBody ClubImages clubImages) throws IntegridadException, Exception {
        service.guardar(clubImages);

        return new ResponseEntity<Object>(clubImages, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit club image", notes = "Edit a athlete")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The club image edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete"),
            @ApiResponse(code = 409, message = "Conflict. The document already created")})
    public ResponseEntity<?> editar(@Valid @RequestBody ClubImages clubImages) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(clubImages);

        return new ResponseEntity<Object>(clubImages, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete club image", notes = "Delete club image by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The club image deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
