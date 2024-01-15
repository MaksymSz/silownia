package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty odnośnie raportowania problemu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportProblemRequest {
    String title;
    String description;
    String date;
}
