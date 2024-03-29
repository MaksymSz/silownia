package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie użycia kuponu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseCuponRequest {
    int id;
    String coupon;
}
