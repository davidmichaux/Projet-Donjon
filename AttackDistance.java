package Model;

public class AttackDistance extends GameObject {
	private int damageValue ; 
	
	public AttackDistance(int posX, int posY,int damageValue) {
		super(posX, posY);
		this.damageValue = damageValue ; 
	}

	@Override
	public void move(Direction dir) {
		switch(dir){
			case HAUT : super.posY -- ;  break ; 
			case BAS : super.posY ++ ; break ; 
			case GAUCHE : super.posX -- ; break ; 
			case DROITE : super.posX ++ ; break ; 
			}
		}

	
}
