package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="University info from http://universities.hipolabs.com/")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @Operation(summary="Get a university's website and country")
    @GetMapping("/get")
    public ResponseEntity<String> getCountryCodes(
        @Parameter(name="name", description="the name of a university", example="University of California, Santa Barbara") @RequestParam String name
    ) throws JsonProcessingException {
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}