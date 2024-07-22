package com.meraki.back.controller;

import com.meraki.back.dto.FamilyDto;
import com.meraki.back.entity.Family;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IFamilyService;
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

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/family")
@Validated
public class FamilyController {
    @Autowired
    private IFamilyService service;

    @GetMapping(value = "/getPage/{page}/{size}", produces = "application/json")
    @Operation(description = "Return all of all athletes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Family.class)) })})
    public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
        Page<FamilyDto> listAthlete = service.retornarPaginadoALL(page, size);
        return new ResponseEntity<Page<FamilyDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageAthlete/{athlete}", produces = "application/json")
    @Operation(description = "Return all familys of one athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Family.class)) })})
    public ResponseEntity<?> retonarPaginadoAthlete( @PathVariable int athlete) {
        List<FamilyDto> listAthlete = service.retornarPaginadoFamily(athlete);
        return new ResponseEntity<List<FamilyDto>>(listAthlete, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Return family by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Family.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the family")})
    public ResponseEntity<?> retornarFamily(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        FamilyDto family = service.retonarPorIdDto(id);

        return new ResponseEntity<Object>(family, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Create a new family")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The family created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  Family.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created"),
            @ApiResponse(responseCode = "409", description = "Conflict. The athlete dont exist"),
            @ApiResponse(responseCode = "409", description = "Conflict. The email all ready exists")})
    public ResponseEntity<?> guardar(@Valid @RequestBody Family family) throws IntegridadException, Exception {
        service.guardar(family);

        return new ResponseEntity<Object>(family, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit a family")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The athlete edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the familiar"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created"),
            @ApiResponse(responseCode = "409", description = "Conflict. The athlete dont exist")})
    public ResponseEntity<?> editar(@Valid @RequestBody Family family) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(family);

        return new ResponseEntity<Object>(family, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete family by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The family deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the family")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
