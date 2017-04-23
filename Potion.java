package Model;

public class Potion extends Item {
	// Attributs
	private int value ; 
	private Stat attributModif ; 		// sur quelle stat la potion s'applique (PV,XP,...)

	// Constructors
	public Potion(int posX, int posY, int size, String name,Stat stat) {
		super(posX, posY, size, name);
		switch(name){
			case "Super potion" : value = 5 ; break ; 
			case "Hyper potion" : value = 10 ; break ; 
			default : value = 20 ; break ; 
		}
		attributModif = stat ; 
	}

	public Potion(String name, Stat stat){
		this(0,0,0,name,stat);
	}

	// Methods
	public void useTo(Player player){
		switch(attributModif){
		case PV : player.addPV(value); break ; 
		case XP : player.addXP(value); break ; 
		case Defense : player.addDef(value); break ; 
		case Attack : player.addAtk(value); break ; 
		}

	}

	@Override
	public String toString() {
		return name + " : " + String.valueOf(attributModif);
	}
}
