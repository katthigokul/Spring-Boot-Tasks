package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Resource(name = "TrackService")
@RequestMapping("/api/v1/")
public class TrackController {
    private TrackService trackService;


    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Method to Save Tracks

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Track Successfully Created", HttpStatus.CREATED);
        return responseEntity;
    }

    //Method to Get Track By Id

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws Exception {
        ResponseEntity responseEntity;
        Track retrievedTrack = trackService.getTrackById(id);
        return new ResponseEntity(retrievedTrack, HttpStatus.FOUND);
    }

    //Method Delete Track By Id

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws Exception {
        ResponseEntity responseEntity;
        List<Track> trackList = trackService.deleteTrackById(id);
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }

    //Method Update Track By Id

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track updatedTrack) throws Exception {
        ResponseEntity responseEntity;
        System.out.println(id);
        Track updateTrack = trackService.updateTrackById(id, updatedTrack);
        return new ResponseEntity<Track>(updateTrack, HttpStatus.OK);
    }

    //Method to Get All Tracks

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        List<Track> trackList = trackService.getAllTrack();
        return new ResponseEntity<>(trackList, HttpStatus.FOUND);
    }

    //Method to Get Tracks ByName
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) throws Exception {
        ResponseEntity responseEntity;
        System.out.println("control");
        List<Track> foundTracksList = trackService.findByName(name);
        return new ResponseEntity<>(foundTracksList, HttpStatus.FOUND);
    }

}
