package com.stackroute.dataseeder;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
//Command Line Runner is used to execute the code after the Spring Boot application started

public class CommandLineSeedData implements CommandLineRunner {
    private TrackRepository trackRepository;

    @Autowired
    public CommandLineSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Pre-fill the database whenever application starts
        try {
            Track track = new Track(1, "Baby", "Baby Song");
            trackRepository.save(track);
        } catch (TrackAlreadyExistsException exception) {
            exception.printStackTrace();
        }

    }

}


