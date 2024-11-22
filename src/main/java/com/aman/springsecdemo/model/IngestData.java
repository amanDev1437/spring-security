package com.aman.springsecdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class IngestData {

    @Id
    private String id;

    private String cat;

    private LocalDateTime ingestDateAndTime;

    private Object data;


}