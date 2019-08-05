package com.stackroute.dataseeder;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("track2.properties")
public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {
    private TrackRepository trackRepository;

    @Autowired
    public ApplicationListenerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /* Using Environment to get property  values*/

    @Autowired
    private Environment environment;

    /* To Get  the Property Values*/
    @Value("${track2.id}")
    int id;
    @Value(("${track2.name"))
    String name;
    @Value(("${track2.comments"))
    String comments;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Context Event Received");
        /*Track track = new Track(1, "Baby", "Baby Song");
        trackRepository.save(track);
        Track track2 = new Track(2, "Love me", "Love Song");
        trackRepository.save(track2);
        Track track3 = new Track(3, "Let me Love You", "Love Song");
        trackRepository.save(track3);
        Track track4 = new Track(4, "Sorry", "Sorry song");
        trackRepository.save(track4);
        Track track5 = new Track(5, "Love me Again", "Sad Song");
        trackRepository.save(track5);

    }*/
        try {
            trackRepository.save(new Track(id, environment.getProperty("name"), environment.getProperty("comments")));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
