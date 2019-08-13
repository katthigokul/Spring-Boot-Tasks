package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


public interface TrackService {
    public Track saveTrack(Track track) throws Exception;

    public Track getTrackById(int id) throws Exception;

    public List<Track> getAllTrack() throws  Exception;

    public List<Track> deleteTrackById(int id) throws Exception;

    public Track updateTrackById(int id, Track updatedTrack) throws  Exception;

    //Searching Tracks By Name

    public List<Track> findByName(String name) throws Exception;
}
