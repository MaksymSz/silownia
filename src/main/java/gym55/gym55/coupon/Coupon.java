package gym55.gym55.coupon;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    int couponid;
    String name;
    float discount;
}
