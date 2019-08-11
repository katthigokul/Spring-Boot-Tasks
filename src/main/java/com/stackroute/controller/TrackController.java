package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
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
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Track Successfully Created", HttpStatus.CREATED);
        return responseEntity;
    }

    //Get Track By Id

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Track retrievedTrack = trackService.getTrackById(id);
        return new ResponseEntity(retrievedTrack, HttpStatus.OK);
    }

    //Method to Delete Track By Id

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        List<Track> trackList = trackService.deleteTrackById(id);
        return new ResponseEntity<>(trackList, HttpStatus.OK);

    }

    //Method to Update Track By Id

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track updatedTrack) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Track updateTrack = trackService.updateTrackById(id, updatedTrack);
        return new ResponseEntity<Track>(updateTrack, HttpStatus.OK);
    }

    //Method to Get All Tracks

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        List<Track> trackList = trackService.getAllTrack();
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }

    //Method to Search  Track By Name

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> searchTrackByName(@PathVariable String name) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        System.out.println("control");
        List<Track> foundTracksList = trackService.searchTrackByName(name);
        return new ResponseEntity<>(foundTracksList, HttpStatus.FOUND);
    }

}
