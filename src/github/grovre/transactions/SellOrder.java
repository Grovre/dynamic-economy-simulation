package github.grovre.transactions;

import java.time.Instant;
import java.util.UUID;

public class SellOrder extends Order {

    public SellOrder(int initialQuantity, Instant instant, UUID id) {
        super(initialQuantity, instant, id);
    }

    public SellOrder(int initialQuantity, Instant instant) {
        super(initialQuantity, instant);
    }
}
