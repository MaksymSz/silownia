/**
 * Pakiet obsługujący requesty rest api
 */
package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie zapisu na kurs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollRequest {
    int userId;
    int courseId;
}
