package github.grovre.markets;

import java.io.Serializable;
import java.util.UUID;

public record MarketProduct(String name, UUID id) implements Serializable {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MarketProduct other))
            return false;

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
