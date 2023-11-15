package gym55.gym55.tableObjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa emulująca jeden wiersz tabeli coupon z bazy danych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Coupon {
    int couponId;
    String name;
    int value;
    //TODO dostosować do nowej wersji tabeli coupon
}
