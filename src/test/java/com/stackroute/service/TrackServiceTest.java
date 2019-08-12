package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackServiceTest {
    private Track track;
    //Create a mock for TrackRepository
    @Mock
    private TrackRepository trackRepository;
    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("Gelaya");
        track.setComments("NTR Song");
        list = new ArrayList<>();
        list.add(track);
    }

    @After
    public void tearDown() {
        list = null;
        track = null;
    }

    @Test
    public void givenTrackShouldsaveTrackSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);
        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackShouldsaveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        Track savedTrack = trackService.saveTrack(track);
//        Assert.assertEquals(track,savedTrack);

    }

    @Test
    public void givenTrackShouldRetunrgetAllTrack() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTrack();
        Assert.assertEquals(list, tracklist);
        verify(trackRepository, times(1)).getAllTrack(track);
    }

    @Test
    public void givenIdShouldReturnDeletedTrack() throws TrackNotFoundException {
        when(trackRepository.existsById(track.deleteTrackById())).thenReturn(true);
        when(trackRepository.findById(track.getTrackById())).thenReturn(Optional.of(track));
        Track savedTrack = trackService.deleteTrackById(track.getTrackId());
        assertEquals(track, savedTrack);
    }

    @Test(expected = TrackNotFoundException.class)
    public void givenIdToUpdateShouldReturnTrackNotFoundException() throws TrackNotFoundException {
        Track resultTrack = trackService.getTrackById(track.getTrackById());
        trackService.updateTrackById(track.getTrackById(), track);
    }

}