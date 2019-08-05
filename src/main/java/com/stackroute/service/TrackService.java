package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id) throws TrackNotFoundException;

    public List<Track> getAllTrack();

    public List<Track> deleteTrackById(int id) throws TrackNotFoundException;

    public Track updateTrackById(int id, Track updatedTrack) throws  TrackNotFoundException;

    //Searching Tracks By Name

    public List<Track> findByName(String name) throws TrackNotFoundException;
}
