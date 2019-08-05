package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//Adding Query By using @Query Annotations
public interface TrackRepository extends MongoRepository<Track,Integer> {
   // @Query("Select t from Track t where t.name like ?1")
    List<Track> findByName(String name);


}
