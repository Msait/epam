package knight.accoutrements;

public class Shield extends Accoutrements {

    private SHIELD type;

    public enum SHIELD {
	KITE(380, 8), 
	HEATER(660, 12.7), 
	SCUTUM(210, 16.4), 
	PARMA(440, 9.8), 
	BUCKLER(280, 6.1);

	private final double weight;
	private final int cost;

	public double getWeight() {
	    return weight;
	}

	public int getCost() {
	    return cost;
	}

	private SHIELD(int cost, double weight) {
	    this.weight = weight;
	    this.cost = cost;
	}
    }

    public Shield(SHIELD type) {
	this.type = type;
	setWeight(type.getWeight());
	setCost(type.getCost());
    }

    @Override
    public String toString() {
	return String.format("%s \t%s", type, super.toString());
    }
}
