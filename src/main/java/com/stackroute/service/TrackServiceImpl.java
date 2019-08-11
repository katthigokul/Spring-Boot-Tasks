package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod")
@Service


public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;

    }

    //Method to Save Tracks in database

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track already Exists");
        }
        Track saveTrack = trackRepository.save(track);
        if (saveTrack == null) {
            throw new TrackAlreadyExistsException("Track already Exists");
        }
        return saveTrack;
    }

    //method to Get Track By id from database

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id).get();
            return retrivedTrack;

        } else {
            throw new TrackNotFoundException("Track not found");
        }

    }

// Method to Get All Tracks from database

    @Override
    public List<Track> getAllTracks() {

        List<Track> listTrack = trackRepository.findAll();
        return listTrack;
    }


    //Method to Delete Track By Id from database

    @Override

    public List<Track> deleteTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            return trackRepository.findAll();
        } else {
            throw new TrackNotFoundException("Track not found");

        }
    }

    //Method to Update Track By Id in Database

    @Override
    public Track updateTrackById(int id, Track updatedTrack) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track track = trackRepository.findById(id).get();
            track.setName(updatedTrack.getName());
            track.setComments(updatedTrack.getComments());
            trackRepository.save(track);
            return track;
        } else {
            throw new TrackNotFoundException("Track Not Found");

        }
    }

    //Method to Search Tracks By Name in Database

    @Override
    public List<Track> findByName(String name) throws TrackNotFoundException {
        if (!(trackRepository.findByName(name).isEmpty() || trackRepository.findByName(name) == null)) {
            List<Track> foundTracksList = trackRepository.findByName(name);
            System.out.println(foundTracksList.size());
            System.out.println(1);
            return foundTracksList;
        } else {


            throw new TrackNotFoundException(("Track Not Found"));
        }
    }
}
