package response;

import gym55.gym55.tableObjects.Training;
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
        stats.add(new UnitStat(training.getDate(), training.getTime()));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UnitStat{
    String date;
    String time;
}