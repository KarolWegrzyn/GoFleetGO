package Classes;

public class Subscription {
    private int id;
    private double cost;
    private double funds;

    public Subscription(int id, double cost, double funds) {
        this.id = id;
        this.cost = cost;
        this.funds = funds;
    }

    // Getters and setters

    public int getSubscriptionId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", cost=" + cost +
                ", funds=" + funds +
                '}';
    }
}