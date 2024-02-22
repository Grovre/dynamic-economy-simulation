package github.grovre.transactions;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class PendingPurchase extends Transaction {

    public PendingPurchase(double amount, Instant instant, UUID id) {
        super(amount, instant, id);
    }

    @Override
    public String toString() {
        return "PendingPurchase{" +
                "amount=" + amount +
                ", id=" + id +
                ", date=" + instant +
                '}';
    }
}
