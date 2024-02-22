package github.grovre.transactions;

import java.util.UUID;

public class Buy extends Transaction {

    public Buy(double amount, UUID id) {
        super(amount, id);
    }

    @Override
    public String toString() {
        return "Buy{" +
                "amount=" + amount +
                ", id=" + id +
                '}';
    }
}
