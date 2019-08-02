package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.xml.crypto.KeySelectorException;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    private TrackService trackService;


    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Save Tracks

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Track Successfully Created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        //Track savedTrack = trackService.saveTrack(track);
        //return new ResponseEntity<>(savedTrack, HttpStatus.OK);
        return responseEntity;
    }

    //Get Track By Id

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Track retrievedTrack = trackService.getTrackById(id);
            return new ResponseEntity(retrievedTrack, HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

    }

    //Delete Track By Id

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            List<Track> trackList = trackService.deleteTrackById(id);
            return new ResponseEntity<>(trackList, HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //Update Track By Id

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track updatedTrack) {
        ResponseEntity responseEntity;
        System.out.println(id);
        try {
            Track updateTrack = trackService.updateTrackById(id, updatedTrack);
            return new ResponseEntity<Track>(updateTrack, HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

    }

    //Get All Tracks

    @GetMapping("track")
    public ResponseEntity<?>getAllTrack() {
        ResponseEntity responseEntity;
                try {
                    List<Track> trackList = trackService.getAllTrack();
                    return new ResponseEntity<>(trackList, HttpStatus.OK);
                }catch (Exception exception){
                    responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return responseEntity;
    }

    //Search  Track By Name

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> searchTrackByName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            System.out.println("control");
            List<Track> foundTracksList = trackService.searchTrackByName(name);
            return new ResponseEntity<>(foundTracksList, HttpStatus.FOUND);
        } catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }


    }

}
