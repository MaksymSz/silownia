package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie kodu qr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrRequest {
    int id;
}
