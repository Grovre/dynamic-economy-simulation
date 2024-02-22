package github.grovre.transactions;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Comparable<Transaction> {
    public final double amount;
    public final UUID id;
    public final Instant instant;

    public Transaction(double amount, Instant instant, UUID id) {
        this.amount = amount;
        this.id = id;
        this.instant = instant;
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
        return instant.compareTo(o.instant);
    }
}
