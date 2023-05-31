package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskiesFilteredByYear(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="distilleryId", required = false) Long id,
            @RequestParam(name="age", required = false) Integer age
    ){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year),HttpStatus.OK);
        }
        if(id != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryIdAndAge(id, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
