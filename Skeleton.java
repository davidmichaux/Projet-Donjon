package Model;

import java.util.ArrayList;

import View.Map;

public class Skeleton extends Monster {

	// Attributes
	private ArrayList<AttackDistance> attackDistance ; 
	
	// Constructors 
	public Skeleton(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY, PV, maxXP, attack, defense);
	}
	
	public Skeleton(int PV, int maxXP, int attack, int defense) {
		super(0,0,PV, maxXP, attack, defense);
	}

	@Override
	public void attack(Character ennemi, Map map){			// long dist attack
		new Thread(() -> {
			Direction direction = this.orientation ; 
			AttackDistance atkD = new AttackDistance(this.posX,this.posY,this.attack) ; 
			attackDistance.add(atkD);		// ajouter attaques dans liste 
			GameObject collisionObject = map.collision(atkD) ; 
			while (collisionObject == null){
				atkD.move(direction);
				collisionObject = map.collision(atkD) ; 
			}
			if (collisionObject instanceof Player){			
				this.attack((Character)collisionObject);
			}
			});
	}
}
