package knight;

import java.util.ArrayList;

import knight.accoutrements.Accoutrements;
import knight.accoutrements.Armour.ARMOUR;
import knight.accoutrements.Clothing.CLOTHING;
import knight.accoutrements.Shield.SHIELD;
import knight.accoutrements.Weapon.WEAPONS;

public class EquipTheKnight {

    public static void main(String[] args) {

	Knight knight = new Knight();

	knight.addWeapon(WEAPONS.MACE);
	knight.addWeapon(WEAPONS.TWO_HANDED_SWORD);

	knight.addArmour(ARMOUR.HELMET);
	knight.addArmour(ARMOUR.CUISSE);
	knight.addArmour(ARMOUR.PLACKART);

	knight.addShield(SHIELD.PARMA);

	knight.addClothing(CLOTHING.TUNIC);
	knight.addClothing(CLOTHING.UNDERPANTS);

	System.out.printf("Total weight: \t%.2f kg%nTotal cost: \t%d $%n",
		knight.totalWeight(), knight.totalCost());

	ArrayList<Accoutrements> knightItems = knight.find(110, 400);
	System.out.printf("\nFound items with price[110, 400]:%n%s",
		knightItems.toString());

    }

}
