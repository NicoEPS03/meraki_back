package com.meraki.back.controller;

import com.meraki.back.dto.CoachDto;
import com.meraki.back.entity.Coach;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.ICoachService;
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
@RequestMapping("/coach")
@Validated
public class CoachController {
    @Autowired
    private ICoachService service;

    @GetMapping(value = "/getPage/{page}/{size}", produces = "application/json")
    @ApiOperation(value = "Get all coachs", notes = "Return all coachs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Coach.class)})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<CoachDto> listCoach = service.retornarPaginadoDto(page, size);
        return new ResponseEntity<Page<CoachDto>>(listCoach, HttpStatus.OK);
    }
    @GetMapping(value = "get/{id}", produces = "application/json")
    @ApiOperation(value = "Get coach", notes = "Retorn coach by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Coach.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> retornarCoach(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Coach coach = service.retonarPorId(id);

        return new ResponseEntity<Object>(coach, HttpStatus.OK);
    }

    @GetMapping(value = "getCoachClub/{idClub}", produces = "application/json")
    @ApiOperation(value = "Get coach", notes = "Return coach by IDCLub")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. The response is obtained successfully", response = Coach.class),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> retornarCoachClub(@PathVariable("idClub") int idClub) throws ModelNotFoundException, Exception {
        CoachDto coachDto = service.retornarCoachClub(idClub);

        return new ResponseEntity<Object>(coachDto, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @ApiOperation(value = "Insert coach", notes = "Create a new coach")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. The coach created correctly", response = Coach.class),
            @ApiResponse(code = 409, message = "Conflict. The club dont exist"),
            @ApiResponse(code = 409, message = "Conflict. The club are inactive"),
            @ApiResponse(code = 409, message = "Conflict. The document already created"),
            @ApiResponse(code = 409, message = "Conflict. The coach to this club all ready exist")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Coach coach) throws IntegridadException, Exception {
        service.guardar(coach);

        return new ResponseEntity<Object>(coach, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @ApiOperation(value = "Edit coach", notes = "Edit a coach")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok. The coach edited correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the coach"),
            @ApiResponse(code = 409, message = "Conflict. The club dont exist"),
            @ApiResponse(code = 409, message = "Conflict. The club are inactive"),
            @ApiResponse(code = 409, message = "Conflict. The document already created"),
            @ApiResponse(code = 409, message = "Conflict. The coach to this club all ready exist")})
    public ResponseEntity<?> editar(@Valid @RequestBody Coach coach) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(coach);

        return new ResponseEntity<Object>(coach, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete coach", notes = "Delete coach by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content. The coach deleted correctly"),
            @ApiResponse(code = 404, message = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
