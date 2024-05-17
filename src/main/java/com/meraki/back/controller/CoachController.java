package com.meraki.back.controller;

import com.meraki.back.dto.CoachDto;
import com.meraki.back.entity.Coach;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.ICoachService;
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
@RequestMapping("/coach")
@Validated
public class CoachController {
    @Autowired
    private ICoachService service;

    @GetMapping(value = "/getPage/{page}/{size}", produces = "application/json")
    @Operation(description = "Return all coachs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Coach.class)) })})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<CoachDto> listCoach = service.retornarPaginadoDto(page, size);
        return new ResponseEntity<Page<CoachDto>>(listCoach, HttpStatus.OK);
    }
    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Return coach by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Coach.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> retornarCoach(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Coach coach = service.retonarPorId(id);

        return new ResponseEntity<Object>(coach, HttpStatus.OK);
    }

    @GetMapping(value = "getCoachClub/{idClub}", produces = "application/json")
    @Operation(description = "Return coach by IDCLub")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Coach.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> retornarCoachClub(@PathVariable("idClub") int idClub) throws ModelNotFoundException, Exception {
        CoachDto coachDto = service.retornarCoachClub(idClub);

        return new ResponseEntity<Object>(coachDto, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Create a new coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The coach created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Coach.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict. The club dont exist"),
            @ApiResponse(responseCode = "409", description = "Conflict. The club are inactive"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created"),
            @ApiResponse(responseCode = "409", description = "Conflict. The coach to this club all ready exist")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Coach coach) throws IntegridadException, Exception {
        service.guardar(coach);

        return new ResponseEntity<Object>(coach, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit a coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The coach edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the coach"),
            @ApiResponse(responseCode = "409", description = "Conflict. The club dont exist"),
            @ApiResponse(responseCode = "409", description = "Conflict. The club are inactive"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created"),
            @ApiResponse(responseCode = "409", description = "Conflict. The coach to this club all ready exist")})
    public ResponseEntity<?> editar(@Valid @RequestBody Coach coach) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(coach);

        return new ResponseEntity<Object>(coach, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete coach by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The coach deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the coach")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
