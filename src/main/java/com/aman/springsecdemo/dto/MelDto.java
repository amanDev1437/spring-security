package com.aman.springsecdemo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MelDto {


        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String tailNo;

        private String melType;

        private String melReferenceNumber;

        private String controlNumber;

        private LocalDate startDate;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private LocalDate endDate;

        private String failureMessage;
}
