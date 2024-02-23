package testing.simulated;

import github.grovre.economics.Economy;
import testing.simulated.agents.Agent;

import java.util.*;

public class Main {

    final static Economy economy = new Economy();
    final static List<Agent> agents = new ArrayList<>();

    public static void main(String[] args) {
        for (var i = 0; i < 100; i++) {
            var agent = new Agent(1000.0, 1, 1);
            agents.add(agent);
        }
    }
}
