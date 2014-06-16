package knight.accoutrements;

public class Clothing extends Accoutrements {

    private CLOTHING type;

    public enum CLOTHING {
	TUNIC(250, 2.8), 
	UNDERSHIRT(140, 2), 
	UNDERPANTS(110, 3.4), 
	DOUBLET(270,5.6);

	private final double weight;
	private final int cost;

	public double getWeight() {
	    return weight;
	}

	public int getCost() {
	    return cost;
	}

	private CLOTHING(int cost, double weight) {
	    this.weight = weight;
	    this.cost = cost;
	}
    }

    public Clothing(CLOTHING type) {
	this.type = type;
	setWeight(type.getWeight());
	setCost(type.getCost());
    }

    @Override
    public String toString() {
	return String.format("%s \t%s", type, super.toString());
    }
}
