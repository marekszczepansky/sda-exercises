package sda.exercises.sdaexercises.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {
    public LocalDateTime getNow(){
        return LocalDateTime.now();
    }
}
