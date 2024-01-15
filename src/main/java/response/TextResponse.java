/**
 * Pakiet obsługujący odpowiedzi do rest api
 */
package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca odpowiedzi tekstowe backendu na requesty rest api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextResponse {
    String text;
}
