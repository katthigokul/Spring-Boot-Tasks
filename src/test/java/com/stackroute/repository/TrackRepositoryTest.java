package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest

public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setId(10);
        track.setName("Love");
        track.setComments("Sad");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testsaveTrack(){
        trackRepository.saveTrack(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(101,fetchTrack.getId());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track("Harry","john",34,"Harry123",201);
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllUser(){
        Track u = new Track("Johny","Jenny",10,"Johny12",102);
        Track u1 = new Track("Harry","Jenny",11,"Harry12",103);
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Baby,list.get(0).getName());
    }


}