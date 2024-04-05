package com.meraki.back.controller;

import com.meraki.back.dto.ClubAdminDto;
import com.meraki.back.dto.ClubFilterDto;
import com.meraki.back.entity.Club;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IClubService;
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
@RequestMapping("/club")
@Validated
public class ClubController {
    @Autowired
    private IClubService service;

    @GetMapping(value = "/getPageAdmin/{page}/{size}", produces = "application/json")
    @ApiOperation(value = "Get all clubs on admin view", notes = "Return all clubs on admin view")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = ClubAdminDto.class)})
    public ResponseEntity<?> retonarPaginadoAdmin(@PathVariable int page, @PathVariable int size) {
        Page<ClubAdminDto> listClub = service.retornarPaginadoAdminDto(page, size);
        return new ResponseEntity<Page<ClubAdminDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilter/{page}/{size}", produces = "application/json")
    @ApiOperation(value = "Get all clubs on filter view", notes = "Return all clubs on filter view")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = ClubAdminDto.class)})
    public ResponseEntity<?> retonarPaginadoFilter(@PathVariable int page, @PathVariable int size) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoFilterDto(page, size);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterSport/{page}/{size}/{sport}", produces = "application/json")
    @ApiOperation(value = "Get all clubs on filter sport view", notes = "Return all clubs on filter sport view")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = ClubFilterDto.class)})
    public ResponseEntity<?> retonarPaginadoFilterSport(@PathVariable int page, @PathVariable int size, @PathVariable int sport) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoSport(page, size, sport);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterCity/{page}/{size}/{city}", produces = "application/json")
    @ApiOperation(value = "Get all clubs on filter city view", notes = "Return all clubs on city sport view")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = ClubFilterDto.class)})
    public ResponseEntity<?> retonarPaginadoFilterCity(@PathVariable int page, @PathVariable int size, @PathVariable int city) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoCity(page, size, city);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterSportAndCity/{page}/{size}/{sport}/{city}", produces = "application/json")
    @ApiOperation(value = "Get all clubs on filter sport and city view", notes = "Return all clubs on sport and city sport view")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = ClubFilterDto.class)})
    public ResponseEntity<?> retonarPaginadoFilterSportAndCity(@PathVariable int page, @PathVariable int size, @PathVariable int sport, @PathVariable int city) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoSportAndCity(page, size, sport, city);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get club", notes = "Return club by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Club.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the club")})
    public ResponseEntity<?> retornarClub(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Club club = service.retonarPorId(id);

        return new ResponseEntity<Object>(club, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert club", notes = "Create a new club")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The club created correctly", response = Club.class)})
    public ResponseEntity<?> guardar(@Valid @RequestBody Club club) throws IntegridadException, Exception {
        service.guardar(club);

        return new ResponseEntity<Object>(club, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit club", notes = "Edit a club")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The club edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the club")})
    public ResponseEntity<?> editar(@Valid @RequestBody Club club) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(club);

        return new ResponseEntity<Object>(club, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete club", notes = "Delete club by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The club deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the club")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
