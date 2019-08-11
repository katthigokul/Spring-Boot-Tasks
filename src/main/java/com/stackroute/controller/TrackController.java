package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    //method to Save Tracks in Database

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;

        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Track Successfully Created", HttpStatus.CREATED);

        return responseEntity;
    }

    //method to Get Track By Id from Database

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        // try {
        Track retrievedTrack = trackService.getTrackById(id);
        return new ResponseEntity(retrievedTrack, HttpStatus.OK);
        /*} catch (TrackNotFoundException ex) {
            return new ResponseEntity<Track>(ex.getMessage(), HttpStatus.CONFLICT);
        }*/

    }

    //Method to Delete Track By Id from Database

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        // try {
        List<Track> trackList = trackService.deleteTrackById(id);
        return new ResponseEntity<>(trackList, HttpStatus.OK);
        /*} catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }*/
    }

    //Method to Update Track By Id in Database

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track updatedTrack) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        System.out.println(id);
        // try {
        Track updateTrack = trackService.updateTrackById(id, updatedTrack);
        return new ResponseEntity<Track>(updateTrack, HttpStatus.OK);
        /*} catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }*/

    }

    //Method to Get All Tracks from Database

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        //try {
        List<Track> trackList = trackService.getAllTrack();
        return new ResponseEntity<>(trackList, HttpStatus.OK);
        /*} catch (Exception exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;*/
    }

    //Method  to Search  Track By Name in Database

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> searchTrackByName(@PathVariable String name) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        // try {
        System.out.println("control");
        List<Track> foundTracksList = trackService.searchTrackByName(name);
        return new ResponseEntity<>(foundTracksList, HttpStatus.FOUND);
        /*} catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }*/


    }

}
