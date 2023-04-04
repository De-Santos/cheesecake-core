package com.product.service.dao;


import com.product.service.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
//    DEPRECATED
//    @Query(value = "{}", fields = "{'id': 1}")
//    List<String> findAllIds();

//    DEPRECATED
//    @Query("{ 'photoTagName' : ?0 }")
//    boolean existsByPhotoTagName(String tagName);

    @Query("{ 'realPhotoName' : ?0 }")
    boolean existsByRealPhotoName(String realPhotoName);

    boolean existsPhotoByIdAndInUseIsTrue(String id);
}
