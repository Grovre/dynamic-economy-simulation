package github.grovre.transactions;

import java.time.Instant;
import java.util.UUID;

public class BuyOrder extends Order {

    public BuyOrder(int initialQuantity, Instant instant, UUID id) {
        super(initialQuantity, instant, id);
    }

    public BuyOrder(int initialQuantity, Instant instant) {
        super(initialQuantity, instant);
    }
}
