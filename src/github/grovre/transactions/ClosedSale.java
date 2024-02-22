package github.grovre.transactions;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ClosedSale extends Transaction {

    public ClosedSale(double amount, Instant instant, UUID id) {
        super(amount, instant, id);
    }

    @Override
    public String toString() {
        return "ClosedSale{" +
                "amount=" + amount +
                ", id=" + id +
                ", date=" + instant +
                '}';
    }
}
