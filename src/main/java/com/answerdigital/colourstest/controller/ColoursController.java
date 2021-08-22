package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.dto.AddColourDTO;
import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    private final Logger logger = LoggerFactory.getLogger("Colour Controller");

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity<>(coloursRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColour(@PathVariable("id") long id) {
        Optional<Colour> theColour = coloursRepository.findById(id);

        return theColour
                .map(colour -> new ResponseEntity<>(colour, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Colour> addColour(@RequestBody AddColourDTO addColourDTO) {
        Colour colour = new Colour(addColourDTO.getName());

        try {
            return new ResponseEntity<>(coloursRepository.save(colour), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.warn("Exception in addColour. Unable to save colour: ".concat(e.getMessage()));
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
