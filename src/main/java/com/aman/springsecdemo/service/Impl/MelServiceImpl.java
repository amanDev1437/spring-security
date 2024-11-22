package com.aman.springsecdemo.service.Impl;


import com.aman.springsecdemo.dto.MelDto;
import com.aman.springsecdemo.model.Mel;
import com.aman.springsecdemo.repository.MelRepo;
import com.aman.springsecdemo.service.MelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MelServiceImpl implements MelService {

    @Autowired
    private MelRepo melRepo;


    @Override
    public Mel createMel(MelDto melDto) {

        Mel mel = convertToModel(melDto);

        return melRepo.save(mel);
    }

    //get all mel without pagination
    @Override
    public Map<String,Object> getAllMel() {

        List<Mel> melList = melRepo.findAll();

        long count = melRepo.findMelWithoutEnddate().stream().count();

        Map<String,Object> map = new HashMap<>();

        //sorted by tailNo
         List<MelDto> melDtoList = melList.stream().map(this::convertToDto)
                .sorted(Comparator.comparingInt(mel -> tailNoMap.getOrDefault(mel.getTailNo(), Integer.MAX_VALUE)))
                .toList();

         map.put("TotalActiveCount",count);
         map.put("MEL",melDtoList);

         return map;

    }


    //get all mel with pagination
    @Override
    public List<MelDto> getAllMel(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        List<Mel> melList = melRepo.findAll(pageable).getContent();

        return melList.stream().map(this::convertToDto)
                .sorted(Comparator.comparingInt(mel->tailNoMap.getOrDefault(mel.getTailNo(),Integer.MAX_VALUE)))
                .toList();
    }


    //find mel by tailNo
    @Override
    public List<MelDto> getMelByTailNo(String tailNo) {

        List<Mel> melList = melRepo.findByTailNo(tailNo);

        return melList.stream().map(this::convertToDto).toList();
    }

    //find mel by tailNo and sorted by melType
    @Override
    public List<MelDto> getSortedMel(String tailNo){

        List<Mel> melList = melRepo.findByTailNo(tailNo);

        return melList.stream().map(this::convertToDto)
                .sorted(Comparator.comparing(MelDto::getMelType))
                .toList();
    }

    @Override
    public List<MelDto>getMelWithoutEnddate() {

        List<Mel> melList = melRepo.findMelWithoutEnddate();

        return melList.stream().map(this::convertToDto).toList();
    }

    //mapping dto to model
    private Mel convertToModel(MelDto melDto){

        Mel mel = new Mel();

        BeanUtils.copyProperties(melDto,mel);

        return mel;
    }

    //mapping model to dto
    private MelDto convertToDto(Mel mel){

        MelDto melDto = new MelDto();

        BeanUtils.copyProperties(mel,melDto,"tailNo");

        return melDto;
    }


    private static Map<String,Integer> tailNoMap = new HashMap<>();

    static {
        tailNoMap.put("VX-EXI",1);
        tailNoMap.put("VT-PZF",2);

    }
}
