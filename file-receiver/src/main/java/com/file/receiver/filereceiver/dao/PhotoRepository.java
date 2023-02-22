package com.file.receiver.filereceiver.dao;

import com.file.receiver.filereceiver.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
}
