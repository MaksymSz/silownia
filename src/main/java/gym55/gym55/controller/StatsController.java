package gym55.gym55.controller;

import gym55.gym55.StatsRepository;
import gym55.gym55.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.GenerateStatsRequest;
import response.Dashboard_IdResponse;
import response.GenerateStatsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatsController {
    @Autowired
    StatsRepository statsRepository;

    @GetMapping("/ingym")
    public Map<String, Integer> inGym(){
        Map<String, Integer> response = new HashMap<>();
        //response.put("inGym", statsRepository.getPeopleInGym());

        //testowo
        response.put("inGym", 8);
        return response;
    }

    // tablica krotek. pierwszy element to trening który był najdawniej.
    @GetMapping("dashboard/{id}")
    @ResponseBody
    public Dashboard_IdResponse getTrainingsStat(@PathVariable("id") int id){
        Dashboard_IdResponse dashboardIdResponse = new Dashboard_IdResponse();
        List<Training> trainings = statsRepository.getTrainings(id);

        for (Training training : trainings) {
            dashboardIdResponse.addStat(training);
        }

        return dashboardIdResponse;
    }

    @ResponseBody
    public GenerateStatsResponse generateStats(GenerateStatsRequest generateStatsRequest){
        //statsRepository
        return new GenerateStatsResponse();
    }
}

