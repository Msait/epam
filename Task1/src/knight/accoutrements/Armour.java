package knight.accoutrements;

public class Armour extends Accoutrements {

    private ARMOUR type;

    public enum ARMOUR {
	HELMET(250, 2.3), 
	GARDBRACE(120, 0.8), 
	STAPLE_AND_PIN_FASTENING(175, 1.3), 
	PLACKART(376, 4.5), 
	GUARD_OF_VAMBRACE(85, 0.6), 
	FAUD_OF_4_LAMES(461, 3.7), 
	GAUNTLET(240, 1.6), 
	TASSET(220, 2.2), 
	CUISSE(315,3.2), 
	POLEYN(110, 1.3), 
	GREAVE(308, 3.1), 
	CHAINMAIL(80, 5.4), 
	BREAST_PLATE(350, 6.2);

	private final double weight;
	private final int cost;

	public double getWeight() {
	    return weight;
	}

	public int getCost() {
	    return cost;
	}

	private ARMOUR(int cost, double weight) {
	    this.weight = weight;
	    this.cost = cost;
	}
    }

    public Armour(ARMOUR type) {
	this.type = type;
	setWeight(type.getWeight());
	setCost(type.getCost());
    }

    @Override
    public String toString() {
	return String.format("%s \t%s", type, super.toString());
    }
}
