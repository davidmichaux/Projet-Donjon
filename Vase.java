package Model;

import java.awt.image.BufferedImage;
import static Model.Stat.* ; 

public class Vase extends Tile {
	// Attributs
	private static final String[] POTIONS = new String[] {"Super potion","Hyper potion"} ; 
	private static final Stat[] STATS = new Stat[] {PV, XP, Attack, Defense} ; 
		// contents : 
	private int nbAmmo = 0; 
	private Item item ; 		// only one item

	// Constructors
	public Vase(BufferedImage texture, Interaction interaction) {
		super(texture, interaction) ;
		this.generateRandomItem();
	}

	public Vase(){
		this(null,null);
		this.generateRandomItem();
	}

	// Methods
	private void generateRandomItem(){
		int randomNumberType = (int) (Math.random() * POTIONS.length) ; 		// number between 0 and 1 (Super or Hyper)
		int randomNumberStat = (int) (Math.random() * STATS.length) ; 
		Double randomNumberAmmo = Math.random(); 							// generate a number between 0 and 1 
		if (randomNumberAmmo > 0.5){
			item = new Potion(POTIONS[randomNumberType],STATS[randomNumberStat]);
		} else if (randomNumberAmmo <= 0.5) {
			nbAmmo = (int) (Math.random()*15 + 1); 			// for not generating 0

		}
	}
	
	@Override
	public String toString() {
		String str = "" ; 
		str += "Dans le vase" + "\n" ;
		str +=  "---"+ "\n" ; 
		str +=  this.item.toString() + " et " + String.valueOf(this.nbAmmo) + " munition(s)" ; 
		return str ; 
	}
}
