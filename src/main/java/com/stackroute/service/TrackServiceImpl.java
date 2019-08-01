package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;

    }
    @Override
    public Track saveTrack(Track track)
    {
        Track saveTrack=trackRepository.save(track);
        return saveTrack;
    }

    @Override
    public Track getTrackById(int id) {
        Track retrivedTrack=trackRepository.findById(id).get();
        return retrivedTrack;
    }

    @Override
    public Track getAllTrack() {
        return null;
    }

    @Override

    public List<Track> deleteTrackById(int id) {
        trackRepository.deleteById(id);
        return trackRepository.findAll();
    }
    @Override
    public Track updateTrackById(int id) {
        Track updateTrack= trackRepository.findById(id).get();
        return updateTrack;
    }


}
