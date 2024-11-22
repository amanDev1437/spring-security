package com.aman.springsecdemo.repository;


import com.aman.springsecdemo.model.IngestData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngestDataRepo extends MongoRepository<IngestData,String> {
}
