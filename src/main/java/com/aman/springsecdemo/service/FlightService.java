package com.aman.springsecdemo.service;


import com.aman.springsecdemo.dto.FlightDto;
import com.aman.springsecdemo.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {

    int createFlight(FlightDto flightDto);

    List<FlightDto> getFlights(int page,int size);

    Object getFlights(int page, int size, String captain);

    List<FlightDto> getFlights();

    Object getFlights(String captain);


    Flight updateFlight(String id, Flight flightDto);

    void deleteFlight(String id);



}
