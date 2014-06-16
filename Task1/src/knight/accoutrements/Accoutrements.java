package knight.accoutrements;

public abstract class Accoutrements {

    private double weight;
    private int cost;

    public double getWeight() {
	return weight;
    }

    public void setWeight(double weight) {
	this.weight = weight;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }

    @Override
    public String toString() {
	return String.format("weight: \t%.2f \tcost: \t%d\n", getWeight(),
		getCost());
    }

}
