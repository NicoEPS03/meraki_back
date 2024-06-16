package com.meraki.back.controller;

import com.meraki.back.dto.ClubImagesDto;
import com.meraki.back.entity.ClubImages;
import com.meraki.back.exception.IntegridadException;
import com.meraki.back.exception.ModelNotFoundException;
import com.meraki.back.service.IClubImagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/clubImages")
@Validated
public class ClubImagesController {
    @Autowired
    private IClubImagesService service;

    @GetMapping(value = "get/{id}", produces = "application/json")
    @Operation(description = "Return all club images by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The response is obtained successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation =  ClubImages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> retornarClubImages(@PathVariable("id") int id) throws ModelNotFoundException, Exception {
        List<ClubImagesDto> clubImages = service.retonarPorIdClub(id);

        return new ResponseEntity<Object>(clubImages, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Create a new club image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. The club image created correctly", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClubImages.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created")})
    public ResponseEntity<?> guardar(@Valid @RequestBody ClubImages clubImages) throws IntegridadException, Exception {
        service.guardar(clubImages);

        return new ResponseEntity<Object>(clubImages, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(description = "Edit a athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok. The club image edited correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete"),
            @ApiResponse(responseCode = "409", description = "Conflict. The document already created")})
    public ResponseEntity<?> editar(@Valid @RequestBody ClubImages clubImages) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(clubImages);

        return new ResponseEntity<Object>(clubImages, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Delete club image by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. The club image deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Not Found. Didn't found the athlete")})
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
