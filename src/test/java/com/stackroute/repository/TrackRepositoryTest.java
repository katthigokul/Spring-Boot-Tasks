package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest

public class TrackRepositoryTest {


    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setId(1);
        track.setName("Baby");
        track.setComments("Baby Song");

    }

    @After
    public void tearDown() {

        track = null;
        trackRepository.deleteAll();
    }

    @Test
    public void givenTrackShouldReturnsaveTrack() {
        trackRepository.save(track);
        Track fetchtrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(1, fetchtrack.getId());

    }

    @Test
    public void givenTrackShouldReturnSavetrackFailure() {
        Track testtrack = new Track(1, "Love me", "Love Song");
        trackRepository.save(track);
        Track fetchtrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testtrack, track);
    }

    @Test
    public void givenTrackShouldReturnGetAlltrack() {
        Track u = new Track(1, "Baby", "Baby Song");
        Track u1 = new Track(3, "Sorry", "Sorry Song");
        trackRepository.save(u);
        trackRepository.save(u1);
        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Baby", list.get(0).getName());
    }

    //Test for delete Track
    @Test
    public void GivenTrackIdShouldDeletetrackAndReturnNull() {
        trackRepository.save(track);
        trackRepository.delete(track);
        Optional<Track> expected = Optional.empty();
        Assert.assertEquals(expected, trackRepository.findById(track.getTrackById()));
    }

    //Test for update Track

    @Test
    public void GivenTrackWithSameIdShouldUpdatedTrackOfThatId() {
        trackRepository.save(track);
        Track updatedTrack = trackRepository.findById(trackToUpdate.getTrackId()).get();
        updatedTrack.setName(trackToUpdate.getTrackName());
        updatedTrack.setComments(trackToUpdate.getComments());
        Track expected = trackToUpdate;
        Track foundTrack = trackRepository.save(trackToUpdate);

        Assert.assertEquals(expected, foundTrack);
    }

}