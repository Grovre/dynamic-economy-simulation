package github.grovre.transactions;

import java.time.Instant;

public class FulfilledOrderInfo {
    final Instant fulfilledInstant;

    public FulfilledOrderInfo(Instant fulfilledInstant) {
        this.fulfilledInstant = fulfilledInstant;
    }
}
