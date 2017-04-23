package Model;
 
public class Monster extends Character {

	public Monster(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY, PV, maxXP, attack, defense);
	}
	
	public double distToPlayer(Player player){
		int[] myCenter = this.getCenter() ; 
		int[] playerCenter = player.getCenter() ; 
		return Math.sqrt( Math.pow(myCenter[0]-playerCenter[0],2) + Math.pow(myCenter[1]-playerCenter[1],2) ) ;
	}
	
	
}
