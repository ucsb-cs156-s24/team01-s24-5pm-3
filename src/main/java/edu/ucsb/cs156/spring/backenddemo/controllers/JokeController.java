package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Jokes")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get an english language joke in safe mode (non offensive)", description = "JSON return format documented here: https://v2.jokeapi.dev/")
    @GetMapping("/get")
    public ResponseEntity<String> getJokes(
        @Parameter(name="category", description="type of joke", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", description="how many jokes", example="1") @RequestParam String numJokes
    ) throws JsonProcessingException {
        log.info("getJokes: category={} numJokes={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }

}
