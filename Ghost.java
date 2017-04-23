package Model;

public class Ghost extends Monster{

	public Ghost(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY, PV, maxXP, attack, defense);
	}
	
	public Ghost(int PV, int maxXP, int attack, int defense){
		this(0, 0, PV, maxXP, attack, defense) ; 
	}

	@Override
	public void attack(Character charac){
		Item droppedItem = charac.getInventory().dropRandomItem() ; 
		if (droppedItem != null){
			this.inventory.add(droppedItem);
		}
	}
}
