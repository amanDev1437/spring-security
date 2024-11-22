package com.aman.springsecdemo.controller;


import com.aman.springsecdemo.dto.FlightDto;
import com.aman.springsecdemo.model.Flight;
import com.aman.springsecdemo.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class FlightController {


    @Autowired
    private FlightService flightService;


    @PostMapping("/flight")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto, BindingResult result) {

        int createdFlight = flightService.createFlight(flightDto);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDto>> getAllFlight(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size){

        if(page==null || size==null || page<=0){
            List<FlightDto> flights = flightService.getFlights();
            return ResponseEntity.ok(flights);
        }

        List<FlightDto> flights = flightService.getFlights(page-1,size);

        return ResponseEntity.ok(flights);

    }

    @GetMapping("/flights/captain")
    public ResponseEntity<?> getAllFlight(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size,@RequestParam(required = false) String captain){

        if(captain == null || page==null || size==null){
            List<FlightDto> flights = flightService.getFlights();
            return ResponseEntity.ok(flights);
        }

        if(page<1){
            Object flights = flightService.getFlights(captain);
            return ResponseEntity.ok(flights);
        }

        Object flights = flightService.getFlights(page-1,size,captain);

        return ResponseEntity.ok(flights);

    }



    @PutMapping("/flight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String id, @Valid @RequestBody Flight flight, BindingResult bindingResult) {
            Flight updatedFlight = flightService.updateFlight(id, flight);
            return ResponseEntity.ok(updatedFlight);

    }

    @DeleteMapping("flight/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
