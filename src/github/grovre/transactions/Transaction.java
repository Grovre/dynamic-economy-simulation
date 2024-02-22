package github.grovre.transactions;

import java.util.UUID;

public class Transaction {
    public final double amount;
    public final UUID id;

    public Transaction(double amount, UUID id) {
        this.amount = amount;
        this.id = id;
    }
}
