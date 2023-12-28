package gym55.gym55.controller;

import gym55.gym55.tableObjects.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.GenerateStatsRequest;
import response.Dashboard_IdResponse;
import response.GenerateStatsResponse;
import gym55.gym55.repository.*;


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
        response.put("inGym", statsRepository.getPeopleInGym());
        return response;
    }

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<HashMap<Object, Object>> getDashboard(@PathVariable("id") int id){
        List<Training> data = statsRepository.getTrainings(id);
        return ResponseEntity.ok().body(new HashMap<>(){{put("data", data);}});}

    //TODO.txt endpoint /generate - brakuje w DB
    @ResponseBody
    public GenerateStatsResponse generateStats(GenerateStatsRequest generateStatsRequest){
        //statsRepository
        return new GenerateStatsResponse();
    }
}

