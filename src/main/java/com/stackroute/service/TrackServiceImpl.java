package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Profile("prod")
@Service("TrackService")


public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;

    }

    //Save Tracks

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Track saveTrack=null;
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track already Exists");
        }else {
            saveTrack = trackRepository.save(track);
            if (saveTrack == null) {
                throw new TrackAlreadyExistsException("Track is null");
            }
            return saveTrack;
        }
    }

    //Get Track By id

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id).get();
            return retrivedTrack;

        } else {
            throw new TrackNotFoundException("Track not found");
        }

    }

// Get All Tracks

    @Override
    public List<Track> getAllTrack() {

        List<Track> listTrack = trackRepository.findAll();
        return listTrack;
    }


    //Delete Track By Id

    @Override

    public List<Track> deleteTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            return trackRepository.findAll();
        } else {
            throw new TrackNotFoundException("Track not found");

        }
    }

    //Update Track By Id

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

    //Search Tracks By Name

    @Override
    public List<Track>findByName(String name) throws TrackNotFoundException {
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
