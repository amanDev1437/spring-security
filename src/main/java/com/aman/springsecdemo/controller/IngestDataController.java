package com.aman.springsecdemo.controller;

import com.aman.springsecdemo.dto.IngestDataDto;
import com.aman.springsecdemo.model.IngestData;
import com.aman.springsecdemo.service.IngestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngestDataController {

    @Autowired
    private IngestDataService ingestDataService;


    @PostMapping("/ingestData")
    public ResponseEntity<?> createIngestData(@RequestBody IngestDataDto ingestDataDto) {

        IngestData createdIngestData = ingestDataService.createIngestData(ingestDataDto);

        ingestDataService.pushMel(ingestDataDto);

        return new ResponseEntity<>(createdIngestData, HttpStatus.CREATED);
    }

    @GetMapping("/ingestData")
    public ResponseEntity<List<IngestDataDto>> getAllIngestData(){

        List<IngestDataDto> ingestDataList = ingestDataService.getAllIngestData();

        return new ResponseEntity<>(ingestDataList,HttpStatus.OK);

    }


}
