package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca odpowiedzi backendu na requesty rest api dotyczące kodu qr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrResponse {
    String userKey;
}
