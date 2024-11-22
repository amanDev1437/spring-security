package com.aman.springsecdemo.service.Impl;


import com.aman.springsecdemo.dto.IngestDataDto;
import com.aman.springsecdemo.dto.MelDto;
import com.aman.springsecdemo.model.IngestData;
import com.aman.springsecdemo.repository.IngestDataRepo;
import com.aman.springsecdemo.repository.MelRepo;
import com.aman.springsecdemo.service.IngestDataService;
import com.aman.springsecdemo.service.MelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@Service
public class IngestDataServiceImpl implements IngestDataService {

    @Autowired
    private IngestDataRepo ingestDataRepo;

    @Autowired
    private MelService melService;

    @Autowired
    private MelRepo melRepo;

    @Override
    public IngestData createIngestData(IngestDataDto ingestDataDto) {

        IngestData ingestData = convertToModel(ingestDataDto);

        return ingestDataRepo.save(ingestData);
    }

    @Override
    public List<IngestDataDto> getAllIngestData() {

        List<IngestData> ingestData =  ingestDataRepo.findAll();

        return ingestData.stream().map(this::covertToDto).toList();
    }

    @Async
    public void pushMel(IngestDataDto ingestDataDto) {
        if (ingestDataDto.getCat().equals("MEL")) {

            List<Object> list = (List<Object>) ingestDataDto.getData();

            list.forEach(item -> {
                if (item instanceof Map) {

                    Map<String, Object> map = (Map<String, Object>) item;

                    MelDto mel = new MelDto();

                    mel.setMelType((String) map.get("melType"));
                    mel.setMelReferenceNumber((String) map.get("melReferenceNumber"));
                    mel.setControlNumber((String) map.get("controlNumber"));

                    String startDate = (String) map.get("startDate");
                    if(startDate !=null && !startDate.isEmpty()){
                        try{
                            mel.setStartDate(LocalDate.parse((String) map.get("startDate")));
                        }catch (DateTimeParseException e){
                            System.out.println("Invalid startDate format: "+startDate);
                        }
                    }

                    String endDate = (String)map.get("endDate");
                    if(endDate !=null && !endDate.isEmpty()){
                        try {
                            mel.setEndDate(LocalDate.parse(endDate));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid endDate format: " + endDate);
                        }

                    }

                    mel.setFailureMessage((String) map.get("failureMessage"));

                    melService.createMel(mel);
                }
            });
        }
    }


    private IngestData convertToModel(IngestDataDto ingestDataDto){

        IngestData ingestData = new IngestData();

        BeanUtils.copyProperties(ingestDataDto,ingestData);

        return ingestData;
    }

    private IngestDataDto covertToDto(IngestData ingestData){
        IngestDataDto ingestDataDto = new IngestDataDto();

        BeanUtils.copyProperties(ingestData,ingestDataDto);

        return ingestDataDto;
    }
}
