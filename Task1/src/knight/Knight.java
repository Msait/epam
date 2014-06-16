package knight;

import java.util.ArrayList;

import knight.accoutrements.Accoutrements;
import knight.accoutrements.Armour;
import knight.accoutrements.Armour.ARMOUR;
import knight.accoutrements.Clothing;
import knight.accoutrements.Clothing.CLOTHING;
import knight.accoutrements.Shield;
import knight.accoutrements.Shield.SHIELD;
import knight.accoutrements.Weapon;
import knight.accoutrements.Weapon.WEAPONS;

public class Knight {
    private ArrayList<Accoutrements> accoutrements;
    private double totalWeight;
    private int totalCost;

    public Knight() {
	accoutrements = new ArrayList<Accoutrements>();
	totalCost = 0;
	totalWeight = 0.0;
    }
    
    private void addItem(Accoutrements item) {
	totalCost += item.getCost();
	totalWeight += item.getWeight();
	accoutrements.add(item);
    }

    public void addWeapon(WEAPONS weapon) {
	Accoutrements item = new Weapon(weapon);
	addItem(item);
    }

    public void addArmour(ARMOUR armour) {
	Accoutrements item = new Armour(armour);
	addItem(item);
    }

    public void addShield(SHIELD shield) {
	Accoutrements item = new Shield(shield);
	addItem(item);
    }

    public void addClothing(CLOTHING clothing) {
	Accoutrements item = new Clothing(clothing);
	addItem(item);
    }

    public int totalCost() {
	return totalCost;
    }

    public double totalWeight() {
	return totalWeight;
    }

    public ArrayList<Accoutrements> find(int min, int max) {
	ArrayList<Accoutrements> foundItems = new ArrayList<Accoutrements>();

	for (Accoutrements item : accoutrements) {
	    if (item.getCost() >= min && item.getCost() <= max)
		foundItems.add(item);
	}

	return foundItems;
    }

}
