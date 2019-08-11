package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    private TrackService trackService;


    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Method to Save Tracks in database

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

    //Method to Get Track By Id from database

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        Track retrivedTrack = trackService.getTrackById(id);
        return new ResponseEntity<Track>(retrivedTrack, HttpStatus.OK);

    }

    //Method to Delete Track By Id from database

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) {
        List<Track> trackList = trackService.deleteTrackById(id);
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }

    //Method Update Track By Id to database

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track updatedTrack) {
        Track updateTrack = trackService.updateTrackById(id, updatedTrack);
        return new ResponseEntity<Track>(updateTrack, HttpStatus.OK);

    }

    //Method to Get All Tracks from database

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTrack() {
        List<Track> trackList = trackService.getAllTracks();
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }
}
