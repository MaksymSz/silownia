package gym55.gym55;


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
    int couponid;
    String name;
    float discount;
    //TODO.txt dodać ewentualnie ilość użyć dla Kuponu? / Kupony jednorazowe?
}
