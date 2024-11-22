package com.aman.springsecdemo.service;


import com.aman.springsecdemo.dto.MelDto;
import com.aman.springsecdemo.model.Mel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MelService {

    Mel createMel(MelDto melDto);

    Map<String,Object> getAllMel();

    List<MelDto> getAllMel(int page, int size);

    List<MelDto> getMelByTailNo(String tailNo);

    List<MelDto> getSortedMel(String tailNo);

    List<MelDto> getMelWithoutEnddate();
}
