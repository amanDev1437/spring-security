package com.aman.springsecdemo.repository;


import com.aman.springsecdemo.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepo extends MongoRepository<Flight,String> {

    Page<Flight> findAll(Pageable pageable);


}

