package github.grovre;

import github.grovre.markets.Market;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Economy {
    final Set<Market> markets;

    public Economy() {
        markets = new HashSet<>();
    }

    public Economy(Market... markets) {
        this.markets = Arrays.stream(markets).collect(Collectors.toSet());
        // If Collectors.toSet impl ever changes for any reason
        assert this.markets instanceof HashSet<Market>
                : "this.markets not instanceof HashSet<>";
    }
}
