package gym55.gym55.controller;

import gym55.gym55.tableObjects.Login;
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


    @CrossOrigin
    @PostMapping("/generate")
    @ResponseBody
    public ResponseEntity<GenerateStatsResponse> generateStats(@RequestBody GenerateStatsRequest generateStatsRequest){
        GenerateStatsResponse report = statsRepository.generate(generateStatsRequest.getFrom(), generateStatsRequest.getTo());
        return ResponseEntity.ok().body(report);
    }
}

