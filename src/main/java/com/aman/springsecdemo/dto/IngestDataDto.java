package com.aman.springsecdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngestDataDto {


        private String id;

        private String cat;

        private LocalDateTime ingestDateAndTime;

        private Object data;


}
