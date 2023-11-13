package response;

import gym55.gym55.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard_IdResponse {
    List<UnitStat> stats = new ArrayList<>();
    public void addStat(Training training){
        stats.add(new UnitStat(training.getTrainingDate(), training.getDuration()));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UnitStat{
    String date;
    String time;
}