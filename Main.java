import static Model.Stat.*;
import static org.junit.Assert.assertEquals;

import Model.Inventory;
import Model.Item;
import Model.Mouse;
import Model.Player;
import Model.Potion;
import Model.Stat;
import View.Map;
import Model.* ; 
public class Main {

	public static void main(String[] args) {
		/** Inventory inv = new Inventory();
		System.out.println(inv);

		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Potion"));
		inv.add(new Item("Epée"));
		System.out.println(inv);
		Item i = inv.dropRandomItem();
		System.out.println(i);
		System.out.println("---");
		System.out.println(inv);
		 **/
		/** Player player = new Player(20,30,40,50);
		System.out.println(player);
		Item item = new Potion("Super potion",Stat.Attack);
		item.useTo(player);
		System.out.println(player); **/
		/** Player player = new Player(20,30,40,50);
		System.out.println(player);
		/**System.out.println(player.canUseAmmo()); **/
		/** player.setAmmo(-5); **/
		/** System.out.println("---");
		Inventory inv = new Inventory();
		Player player = new Player(100, 40, 30, 40);
		System.out.println(inv);
		System.out.println("---");
		System.out.println(player.getInventory());
		inv.add(new Item("Dave"));
		inv.add(new Item("Dave"));
		inv.add(new Item("Erika"));
		inv.add(new Item("Ali"));
		System.out.println("---");
		System.out.println(inv);
		System.out.println("---"); **/
		/** Player player = new Player(10,30,20,35); 
		player.getInventory().add(new Item("David"));
		Item item = player.getInventory().dropRandomItem(); 
		System.out.println(item); **/

		/** Item item1 = new Item("David"); 
		Item item2 = new Item("David") ; 
		System.out.println(item1 == item2);
		System.out.println(item1.equals(item2)); **/

		
		/** Vase vase = new Vase();
		  System.out.println(vase);
		 **/

		/** 
		 * Player player = new Player(150,300,30,30); 
		 * System.out.println(player);

		for(int i=0;i<30;i++){
			int count ;
			Double x = Math.random()*10;
			if (x<=5){
				count = 0 ;
			} else {
				count = 1;
			}
			System.out.println(count);
		}
		 **/
		
		
		
	}

}  
