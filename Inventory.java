package Model;

import Model.Player;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	// Attributes
	private HashMap<Item,Integer> inventory = new HashMap<Item,Integer>() ;
	
	// Getters
	public HashMap<Item,Integer> getInventory() {
		return inventory;
	}
	// Setters
	public void setInventory(HashMap<Item,Integer> inventory) {
		this.inventory = inventory;
	}

	// Constructor
	public Inventory(HashMap<Item, Integer> inventory) {
		super();
		this.inventory = inventory;
	} 
	
	public Inventory(){
		this(new HashMap<>());
	}
	
	 // Methods
	public void add(Item item){
		if (inventory.get(item) == null)
			inventory.put(item, 1);
		else {
			int occ = inventory.get(item);
			inventory.replace(item, occ+1);
		}
	}
	
	public Item dropRandomItem(){						// drop random item from the inventory (when Ghost steal items)
		if(this.inventory.isEmpty()==false){
			int randomNumber = (int) (Math.random()*this.inventory.size()) ; 
			Item randomItem = (Item) this.inventory.keySet().toArray()[randomNumber];
			int occ = inventory.get(randomItem);
			inventory.replace(randomItem,occ-1);
			if (occ-1 == 0){
				inventory.remove(randomItem);
			}
			return randomItem;
		} else {
			return null  ; 			// nothing to drop
		}	
		
	}
	
	@Override
	public String toString() {
		String string = "" ; 
		for (Map.Entry<Item, Integer> entry : inventory.entrySet()){
			string += entry.getKey() + " : " + entry.getValue() + "\n"; 
		}
		return string ; 
	}
	
	public boolean exists(Item item){
		return this.inventory.get(item) != null ; 	
	}
	
	public boolean isEmpty(){
		return this.getInventory().size() == 0 ; 
	}
}
