package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Adding Query By using @Query Annotations
public interface TrackRepository extends JpaRepository<Track, Integer> {
    @Query("Select t from Track t where t.name like ?1")
    List<Track> searchTrackByName(String name);
}
