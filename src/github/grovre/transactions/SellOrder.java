package github.grovre.transactions;

import java.time.Instant;
import java.util.UUID;

public class SellOrder extends Order {

    public SellOrder(double price, int initialQuantity, Instant instant, UUID id) {
        super(price, initialQuantity, instant, id);
    }

    public SellOrder(double price, int initialQuantity, Instant instant) {
        super(price, initialQuantity, instant);
    }
}
