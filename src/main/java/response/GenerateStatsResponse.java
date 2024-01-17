package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateStatsResponse {
    double avgUsers;
    String avgTime;
    int users;
    int newUsers;
}
