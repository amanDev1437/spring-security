package com.aman.springsecdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mel")
public class Mel {


    @NotEmpty(message = "tailNo is required")
    private String tailNo;

    @NotEmpty(message = "melType is required")
    private String melType;

    @NotEmpty(message = "melReferenceNumber is required")
    private String melReferenceNumber;

    @NotEmpty(message = "controlNumber is required")
    private String controlNumber;

    private LocalDate startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate endDate;

    private String failureMessage;
}
