package com.meraki.back.controller;

import com.meraki.back.dto.ClubAdminDto;
import com.meraki.back.dto.ClubFilterDto;
import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.Club;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IAthleteService;
import com.meraki.back.service.IClubService;
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
@RequestMapping("/club")
@Validated
public class ClubController {
    @Autowired
    private IClubService service;

    @GetMapping(value = "getAllExcel/{id}", produces = "application/json")
    @Operation(description = "Get athletes for excel export")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Athlete.class)) })})
    public ResponseEntity<?> retornarAthleteExcel(@PathVariable int id) {
        List<Athlete> athletes = service.retornarAtletas(id);

        return new ResponseEntity<Object>(athletes, HttpStatus.OK);
    }

    @GetMapping(value = "/getNumClubs/{sport}/{city}", produces = "application/json")
    @Operation(description = "Get all clubs on admin view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubAdminDto.class)) })})
    public ResponseEntity<?> retonarNumClubs(@PathVariable int sport, @PathVariable int city) {
        Integer numClubs = service.numClubs(sport, city);
        return new ResponseEntity<Integer>(numClubs, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageAdmin/{page}/{size}", produces = "application/json")
    @Operation(description = "Get all clubs on admin view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubAdminDto.class)) })})
    public ResponseEntity<?> retonarPaginadoAdmin(@PathVariable int page, @PathVariable int size) {
        Page<ClubAdminDto> listClub = service.retornarPaginadoAdminDto(page, size);
        return new ResponseEntity<Page<ClubAdminDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilter/{page}/{size}", produces = "application/json")
    @Operation(description = "Get all clubs on filter view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubAdminDto.class)) })})
    public ResponseEntity<?> retonarPaginadoFilter(@PathVariable int page, @PathVariable int size) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoFilterDto(page, size);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterSport/{page}/{size}/{sport}", produces = "application/json")
    @Operation(description = "Get all clubs on filter sport view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubFilterDto.class)) })})
    public ResponseEntity<?> retonarPaginadoFilterSport(@PathVariable int page, @PathVariable int size, @PathVariable int sport) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoSport(page, size, sport);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterCity/{page}/{size}/{city}", produces = "application/json")
    @Operation(description = "Get all clubs on filter city view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubFilterDto.class)) })})
    public ResponseEntity<?> retonarPaginadoFilterCity(@PathVariable int page, @PathVariable int size, @PathVariable int city) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoCity(page, size, city);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageFilterSportAndCity/{page}/{size}/{sport}/{city}", produces = "application/json")
    @Operation(description = "Get all clubs on filter sport and city view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubFilterDto.class)) })})
    public ResponseEntity<?> retonarPaginadoFilterSportAndCity(@PathVariable int page, @PathVariable int size, @PathVariable int sport, @PathVariable int city) {
        Page<ClubFilterDto> listClub = service.retornarPaginadoSportAndCity(page, size, sport, city);
        return new ResponseEntity<Page<ClubFilterDto>>(listClub, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Get club by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Club.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the club")})
    public ResponseEntity<?> retornarClub(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        Club club = service.retonarPorId(id);

        return new ResponseEntity<Object>(club, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Create a new club")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The club created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Club.class)) })})
    public ResponseEntity<?> guardar(@Valid @RequestBody Club club) throws IntegridadException, Exception {
        service.guardar(club);

        return new ResponseEntity<Object>(club, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit a club")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The club edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the club")})
    public ResponseEntity<?> editar(@Valid @RequestBody Club club) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(club);

        return new ResponseEntity<Object>(club, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete club by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The club deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the club")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
