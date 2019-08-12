package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.graalvm.compiler.nodeinfo.Verbosity.Id;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setId(10);
        track.setName("Baby");
        track.setComments("Baby Song");
        list = new ArrayList();
        list.add(track);
    }

    @After
    public void tearDown() {
        list = null;
        track = null;
    }

    @Test
    public void givenTrackShouldReturnsaveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenTrackShouldReturnsaveTrackFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenTrackShouldReturngetAllTrack() throws Exception {
        when(trackService.getAllTrack()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //    Test for updateTrack()
    @Test
    public void givenTrackShouldReturnUpdatedTrack() throws Exception {
        when(trackService.updateTrackById(anyInt(id))).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    //    Test for updateTrackFailure
    @Test
    public void givenTrackShouldReturnTrackNotFoundException() throws Exception {
        when(trackService.updateTrackById(anyInt(id))).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //    Test for deleteTrack()
    @Test
    public void givenTrackIdShouldDeleteTrackAndReturnDeletedTrack() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenReturn(Optional.of(track));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track?id=20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMovedPermanently())
                .andDo(MockMvcResultHandlers.print());
    }

    //    Test for deleteTrackFailure
    @Test
    public void givenTrackIdShouldReturnTrackNotFoundException() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/116")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}