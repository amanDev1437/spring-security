package com.aman.springsecdemo.repository;


import com.aman.springsecdemo.model.Mel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MelRepo extends MongoRepository<Mel,String> {

    Page<Mel> findAll(Pageable pageable);

    List<Mel> findByTailNo(String tailNo);

    @Query("{ 'endDate' : { $exists: false } }")
    List<Mel> findMelWithoutEnddate();


}
