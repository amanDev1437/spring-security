package com.aman.springsecdemo.dto;


import com.aman.springsecdemo.model.FlightPlan;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    

    private int id;

    private String flightNumber;

    @NotEmpty(message = "Destination is required")
    private String destination;

    @NotEmpty(message = "Origin is required")
    private String origin;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalTime;

    private String status;

    private String captain;

    private FlightPlan flightPlan;

}
