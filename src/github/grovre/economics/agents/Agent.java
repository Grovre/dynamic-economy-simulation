package github.grovre.economics.agents;

import java.util.function.DoubleUnaryOperator;

public abstract class Agent {
    double balance;
    Inventory inventory;

    public Agent(double balance, Inventory inventory) {
        this.balance = balance;
        this.inventory = inventory;
    }

    public Agent(double balance) {
        this(balance, new Inventory());
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void computeBalance(DoubleUnaryOperator f) {
        balance = f.applyAsDouble(balance);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
