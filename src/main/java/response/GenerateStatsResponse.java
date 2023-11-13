package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateStatsResponse {
    int avgUsers;
    int avgTime;
    int newUsers;
    int users;
}
