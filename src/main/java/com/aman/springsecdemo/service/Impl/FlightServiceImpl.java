package com.aman.springsecdemo.service.Impl;


import com.aman.springsecdemo.dto.FlightDto;
import com.aman.springsecdemo.exception.FlightNotFoundException;
import com.aman.springsecdemo.model.Flight;
import com.aman.springsecdemo.repository.FlightRepo;
import com.aman.springsecdemo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Override
    public int createFlight(FlightDto flightDto) {

        Flight flight = this.covertToModel(flightDto);

        return flightRepo.save(flight).getId();

    }

    @Override
    public List<FlightDto> getFlights(int page,int size) {

        Pageable pageable = PageRequest.of(page,size);


        List<Flight> flights = flightRepo.findAll(pageable).getContent();



        return flights.stream().map(this::convertToDto)

                .sorted(Comparator.comparingInt(f -> statusMap.getOrDefault(f.getStatus(),Integer.MAX_VALUE)))
                .toList();


    }

    @Override
    public List<FlightDto> getFlights(){

        List<Flight> flights = flightRepo.findAll();

        return flights.stream().map(this::convertToDto)
                .sorted(Comparator.comparingInt(f -> statusMap.getOrDefault(f.getStatus(),Integer.MAX_VALUE)))
                .toList();

    }


    public List<FlightDto> getFlights(int page, int size, String captain) {
        Pageable pageable = PageRequest.of(page, size);

        List<Flight> list = flightRepo.findAll();

        List<FlightDto> filterList = list.stream().map(this::convertToDto)
                .filter(flight -> flight.getCaptain().equals(captain))
                .sorted(Comparator.comparingInt(f -> statusMap.getOrDefault(f.getStatus(), Integer.MAX_VALUE)))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filterList.size());

        if(start>end){
            return new ArrayList<>();
        }
        List<FlightDto> paginatedList = filterList.subList(start, end);

        return new PageImpl<>(paginatedList, pageable, filterList.size()).toList();
    }

    @Override
    public Object getFlights(String captain) {
        List<Flight> list = flightRepo.findAll();

        return list.stream().map(this::convertToDto)
                .filter(flight -> flight.getCaptain().equals(captain))
                .sorted(Comparator.comparingInt(f -> statusMap.getOrDefault(f.getStatus(), Integer.MAX_VALUE)))
                .toList();
    }


    @Override
    public Flight updateFlight(String id, Flight flight) {
        return flightRepo.findById(id)
                .map(existingFlight->{
                    existingFlight.setFlightNumber(flight.getFlightNumber());
                    existingFlight.setOrigin(flight.getOrigin());
                    existingFlight.setDestination(flight.getDestination());
                    existingFlight.setArrivalTime(flight.getArrivalTime());
                    existingFlight.setStatus(flight.getStatus());
                    existingFlight.setCaptain(flight.getCaptain());
                    existingFlight.setFlightPlan(flight.getFlightPlan());


                    return flightRepo.save(existingFlight);

                }).orElseThrow(()-> new FlightNotFoundException("Flight not found with id"+ id));
    }

    @Override
    public void deleteFlight(String id) {
        flightRepo.deleteById(id);

    }

    private FlightDto convertToDto(Flight flight){

        FlightDto flightDto = new FlightDto();

        flightDto.setId(flight.getId());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setDestination(flight.getDestination());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setStatus(flight.getStatus());
        flightDto.setCaptain(flight.getCaptain());
        flightDto.setFlightPlan(flight.getFlightPlan());

        return flightDto;
    }

    private Flight covertToModel(FlightDto flightDto){

        Flight flight = new Flight();

        flight.setId(flightDto.getId());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setDestination(flightDto.getDestination());
        flight.setOrigin(flightDto.getOrigin());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setStatus(flightDto.getStatus());
        flight.setCaptain(flightDto.getCaptain());
        flight.setFlightPlan(flightDto.getFlightPlan());

        return flight;
    }

    private static final Map<String,Integer> statusMap = new HashMap<>();

    static {
        statusMap.put("YET_TO_DEPART",1);
        statusMap.put("DEPARTED",2);
        statusMap.put("CANCEL",3);
    }

}
