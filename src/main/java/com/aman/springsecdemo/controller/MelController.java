package com.aman.springsecdemo.controller;

import com.aman.springsecdemo.dto.MelDto;
import com.aman.springsecdemo.model.Mel;
import com.aman.springsecdemo.repository.MelRepo;
import com.aman.springsecdemo.service.MelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
public class MelController {

    @Autowired
    private MelService melService;

    @Autowired
    private MelRepo melRepo;

    @PostMapping("/mel")
    public ResponseEntity<?> createMel(@Valid @RequestBody MelDto melDto, BindingResult result) {

//        try{
//            if (melDto==null){
//                throw new RuntimeException("Object should not be empty");
//            }
//            int createdMel = melService.createMel(melDto);
//            return new ResponseEntity<>(createdMel, HttpStatus.CREATED);
//        }catch (Exception ex){
//
//            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
//        }
        try{
            if(melDto.getMelType()==null){
                throw new RuntimeException("Object cannot be null");
            }
            Mel createdMel = melService.createMel(melDto);
            return new ResponseEntity<>(createdMel, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>("errors: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/melList")
    public ResponseEntity<?> getAllMel(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size){

        if(page==null || size==null || page<1){
            Map<String,Object> melList = melService.getAllMel();

            return ResponseEntity.ok(melList);
        }

        List<MelDto> melList = melService.getAllMel(page-1,size);

        return ResponseEntity.ok(melList);

    }

    @GetMapping("/mel/{tailNo}")
    public ResponseEntity<List<MelDto>> getAllMel(@PathVariable String tailNo){

        List<MelDto> melByTailNo = melService.getMelByTailNo(tailNo);

        return ResponseEntity.ok(melByTailNo);

    }

    @GetMapping("/mel/sort/{tailNo}")
    public ResponseEntity<List<MelDto>> getSortedMel(@PathVariable String tailNo){

        List<MelDto> sortedMel = melService.getSortedMel(tailNo);

        return ResponseEntity.ok(sortedMel);

    }
//
//    @GetMapping("/endDate")
//    public ResponseEntity<?> getMelWithoutEnddate(){
//
//        List<MelDto> melList = melService.getMelWithoutEnddate();
//
//        Map<String,Object> response = new HashMap<>();
//
//        response.put("TotalCount",melList.size());
//        response.put("Mel",melList);
//
//        return ResponseEntity.ok(response);
//
//    }




}
