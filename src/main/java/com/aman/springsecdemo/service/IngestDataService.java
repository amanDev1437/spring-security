package com.aman.springsecdemo.service;


import com.aman.springsecdemo.dto.IngestDataDto;
import com.aman.springsecdemo.model.IngestData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngestDataService {

    IngestData createIngestData(IngestDataDto ingestDataDto);

    List<IngestDataDto> getAllIngestData();

    void pushMel(IngestDataDto ingestDataDto);

}
