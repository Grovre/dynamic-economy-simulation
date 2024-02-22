package github.grovre.transactions;

import java.util.UUID;

public class Sale extends Transaction {

    public Sale(double amount, UUID id) {
        super(amount, id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "amount=" + amount +
                ", id=" + id +
                '}';
    }
}
