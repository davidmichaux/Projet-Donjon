package Model;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import javax.imageio.ImageIO;

import View.Map;

import static Model.Direction.BAS;
import static Model.Direction.DROITE;
import static Model.Direction.GAUCHE;
import static Model.Direction.HAUT;
import static Model.State.* ; 

public class Player extends Character {
	// Attributes
	private ArrayList<AttackDistance> attackDistance ; 
	private State state ;
	private int ammo ;  		// munitions atq a distance
	private BufferedImage spritesheet; 

	// Getters & Setters
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getAmmo(){
		return ammo ; 
	}
	public void setAmmo(int ammo){
		if(ammo >=0){
			this.ammo = ammo ; 
		} else {
			throw new IllegalArgumentException("Le nbre de munitions ne peut être négatif.");
		}
	}
	public BufferedImage getSpritesheet(){
		return spritesheet ; 
	}

	// Constructor
	public Player(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY, PV, maxXP, attack, defense);
		attackDistance = new ArrayList<>() ; 		// initialiser liste sinon nulle 
		ammo = 10 ; 
		this.setState(Normal) ; 
		this.inventory = new Inventory() ; 
		try {
			this.spritesheet = ImageIO.read(Player.class.getResourceAsStream("/resources/BODY_male.png"));
			super.texture = spritesheet.getSubimage(0, 128, 64, 64);
			super.size = 64 ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player(int PV, int maxXP, int attack, int defense){
		this(0,0,PV, maxXP, attack, defense);
	}

	// Methods
	public void attackSpecial(ArrayList<Character> ennemis){
		if (this.XP == this.maxXP){
			for (int i=0;i<ennemis.size();i++)
				ennemis.get(i).setPV(0);
		} else {
			throw new IllegalArgumentException("L'XP n'est pas au max.");
		}
	}

	public void longDistanceAttack(Map map){
		if (canUseAmmo()){
			new Thread(() -> {
				Direction direction = this.orientation ; 
				AttackDistance atkD = new AttackDistance(this.posX,this.posY,this.attack) ; 
				attackDistance.add(atkD);		// ajouter attaques dans liste 
				GameObject collisionObject = map.collision(atkD) ; 
				while (collisionObject == null){
					atkD.move(direction);
					collisionObject = map.collision(atkD) ; 
				}
				if (collisionObject instanceof Character){				// cas 1
					this.attack((Character)collisionObject);
				}
			});
		}
	}

	public void goToInventory(Item item){
		this.inventory.add(item);
	}

	public void useItem(Item item){
		if (this.inventory.exists(item) == false){
			throw new IllegalArgumentException("L'objet n'est pas dans l'inventaire");
		}
		else {
			if (item instanceof Potion){
				((Potion)item).useTo(this);
			}
		}
	}

	public void addPV(int value){
		if (value >= 0){
			this.PV += value ;
			if (PV> maxPV){
				PV = maxPV ; 
			}
		} else {
			throw new IllegalArgumentException("Valeur négative");
		}
	}

	public void addXP(int value){
		if (value >=0){
			this.XP += value ;
			if (XP > maxXP){
				XP = maxXP ; 
			}
		} else {
			throw new IllegalArgumentException("Valeur négative");
		}
	}

	public void addAtk(int value){
		if (value >=0){
			this.attack += value ;
		} else {
			throw new IllegalArgumentException("Valeur négative");
		}
	}

	public void addDef(int value){
		if (value >=0){
			this.defense += value ;
		} else {
			throw new IllegalArgumentException("Valeur négative");
		}
	}

	@Override
	public String toString() {
		String string = "" ; 
		string += "Player : \n";
		string += "PV : "+ this.PV + "/" + this.maxPV + '\n';
		string += "XP : "+ this.XP + "/" + this.maxXP + '\n';
		string += "Attack : "+ this.attack + "\n" ;
		string += "Defense : "+ this.defense + "\n" ;
		string += "Munitions : " + this.ammo + "\n" ; 
		return string ; 

	}

	public void addAmmo(int value){
		this.ammo+= value ; 
	}

	public void useAmmo(){
		if (this.ammo <= 0){
			this.ammo = 0 ; 
		}
		else {
			this.ammo -- ; 
		}
	}

	public boolean canUseAmmo(){
		return this.ammo > 0 ; 
	}

	public void moveDown() throws IOException{
		orientation = BAS ; 
		this.spritesheet = ImageIO.read(Player.class.getResourceAsStream("/resources/BODY_male.png"));
		super.texture = spritesheet.getSubimage(64, 128, 64, 64);
		super.size = 64 ;
		this.posY -- ; 
	}
}
