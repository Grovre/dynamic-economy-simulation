package testing.agents;

import java.util.UUID;

public class Agent {

    final UUID id;
    final double balance;
    final Inventory inventory;
    final double confidenceFactor;
    final double bullFactor;

    public Agent(double balance, double confidenceFactor, double bullFactor) {
        this.balance = balance;
        this.confidenceFactor = confidenceFactor;
        this.bullFactor = bullFactor;
        this.inventory = new Inventory();
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getConfidenceFactor() {
        return confidenceFactor;
    }

    public double getBullFactor() {
        return bullFactor;
    }


}
