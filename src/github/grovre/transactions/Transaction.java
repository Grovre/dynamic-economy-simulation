package github.grovre.transactions;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Comparable<Transaction> {
    public final double amount;
    public final UUID id;
    public final Date date;

    public Transaction(double amount, Date date, UUID id) {
        this.amount = amount;
        this.id = id;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(amount, o.amount);
    }
}
