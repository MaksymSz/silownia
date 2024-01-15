package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie generowanie statystyk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateStatsRequest {
    String from;
    String to;
}
