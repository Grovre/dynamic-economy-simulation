package github.grovre.economics.markets;

import java.io.Serializable;
import java.util.Objects;

public class MarketProduct implements Serializable {
    public final String name;

    public MarketProduct(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketProduct that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MarketProduct{" +
                "name='" + name + '\'' +
                '}';
    }
}