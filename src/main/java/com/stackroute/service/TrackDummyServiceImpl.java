package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class TrackDummyServiceImpl implements TrackService {
    private TrackRepository trackRepository;


    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track getTrackById(int id) throws Exception {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track Not Found");
        }
        return trackRepository.findById(id).get();
    }

    @Override
    public Track saveTrack(Track track) throws Exception {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track Already There");
        }
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTrack() throws  Exception {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> deleteTrackById(int id)  throws  Exception{
        Optional<Track> trackList= trackRepository.findById(id);
        trackRepository.deleteById(id);
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrackById(int id, Track track) throws  Exception {

        return null;
    }

    @Override
    public List<Track> findByName(String name) throws  Exception {
        return trackRepository.findByName(name);
    }

}
