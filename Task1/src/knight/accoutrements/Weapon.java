package knight.accoutrements;

public class Weapon extends Accoutrements {

    private WEAPONS type;

    public enum WEAPONS {
	CROSSBOW(200, 8.5), 
	DAGGERS(50, 1.3), 
	MACE(350, 15), 
	SCIMITAR(400, 7.3), 
	TWO_HANDED_SWORD(500, 22.8), 
	WAR_HAMMER(350, 18.4);

	private final double weight;
	private final int cost;

	public double getWeight() {
	    return weight;
	}

	public int getCost() {
	    return cost;
	}

	private WEAPONS(int cost, double weight) {
	    this.weight = weight;
	    this.cost = cost;
	}
    }

    public Weapon(WEAPONS type) {
	this.type = type;
	setWeight(type.getWeight());
	setCost(type.getCost());
    }

    @Override
    public String toString() {
	return String.format("%s \t%s", type, super.toString());
    }

}
