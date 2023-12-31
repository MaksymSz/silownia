package gym55.gym55.controller;

import gym55.gym55.tableObjects.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import request.ReportProblemRequest;
import gym55.gym55.repository.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {
    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/report")
    @ResponseBody
    public Map<String, String> reportProblem(ReportProblemRequest reportProblemRequest){
        Map<String, String> response = new HashMap<>();

        Report report = reportRepository.addReport(
                reportProblemRequest.getTitle(),
                reportProblemRequest.getDescription(),
                reportProblemRequest.getDate());

        if(report.getReportId() < 0){
            response.put("request", "Dodano");
        } else{
            response.put("request", "Nie udalo sie dodac");
        }
        return response;
    }
}
