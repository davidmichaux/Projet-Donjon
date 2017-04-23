package Model;

import static Model.State.* ; 

public class Mouse extends Character {

	// Constructor
	public Mouse(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY, PV, maxXP, attack, defense);

	}

	public Mouse(int PV, int maxXP, int attack, int defense){
		this(0,0,PV,maxXP,attack,defense);
	}

	@Override
	public void attack(Character ennemi){			
		int damage = Math.abs(this.attack - ennemi.defense) ; 
		if (damage >= ennemi.PV){
			ennemi.PV = 0 ; 
		} else {
			ennemi.PV -= damage ; 
		}
		if(ennemi instanceof Player){
			((Player) ennemi).setState(Confused);
		}
	}

}
