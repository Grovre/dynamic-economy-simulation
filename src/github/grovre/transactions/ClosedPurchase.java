package github.grovre.transactions;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ClosedPurchase extends Transaction {

    public ClosedPurchase(double amount, Instant instant, UUID id) {
        super(amount, instant, id);
    }

    @Override
    public String toString() {
        return "ClosedPurchase{" +
                "amount=" + amount +
                ", id=" + id +
                ", date=" + instant +
                '}';
    }
}
