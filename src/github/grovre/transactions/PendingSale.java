package github.grovre.transactions;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class PendingSale extends Transaction {

    public PendingSale(double amount, Instant instant, UUID id) {
        super(amount, instant, id);
    }

    @Override
    public String toString() {
        return "PendingSale{" +
                "amount=" + amount +
                ", id=" + id +
                ", date=" + instant +
                '}';
    }
}
