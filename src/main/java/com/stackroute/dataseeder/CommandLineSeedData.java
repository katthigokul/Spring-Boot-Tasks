package com.stackroute.dataseeder;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class CommandLineSeedData implements CommandLineRunner {
    private TrackRepository trackRepository;

    @Autowired
    public CommandLineSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLine Event Received");
        Track track = new Track(1, "Baby", "Baby Song");
        trackRepository.save(track);
        Track track2 = new Track(2, "Love me", "Love Song");
        trackRepository.save(track2);
        Track track3 = new Track(3, "Let me Love You", "Love Song");
        trackRepository.save(track3);
        Track track4 = new Track(4, "Sorry", "Sorry song");
        trackRepository.save(track4);
        Track track5 = new Track(5, "Love me Again", "Sad Song");
        trackRepository.save(track5);

    }

}


