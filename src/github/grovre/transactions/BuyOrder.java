package github.grovre.transactions;

import java.time.Instant;
import java.util.UUID;

public class BuyOrder extends Order {

    public BuyOrder(double price, int initialQuantity, Instant instant, UUID id) {
        super(price, initialQuantity, instant, id);
    }

    public BuyOrder(double price, int initialQuantity, Instant instant) {
        super(price, initialQuantity, instant);
    }
}
