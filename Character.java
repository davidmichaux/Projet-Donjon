package Model;
import static Model.Direction.* ;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import View.Map; 

public class Character extends GameObject {
	// Attributes
	protected int PV ; 
	protected int XP ;
	protected int maxPV ; 
	protected int maxXP ; 
	protected int attack ; 
	protected int defense ;
	protected Inventory inventory ; 
	protected Direction orientation ; 

	// Getters
	public int getPV() {
		return PV;
	}

	public int getXP() {
		return XP;
	}

	public int getMaxPV() {
		return maxPV;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Direction getDirection(){
		return orientation ; 
	}

	// Setters
	public void setPV(int PV) {
		this.PV = PV;
	}

	public void setXP(int XP) {
		this.XP = XP;
	}

	public void setMaxPV(int maxPV) {
		this.maxPV = maxPV;
	}

	public void setMaxXP(int maxXP) {
		this.maxXP = maxXP;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setDirection(Direction orientation){
		this.orientation = orientation ; 
	}

	// Constructor
	public Character(int posX, int posY, int PV, int maxXP, int attack, int defense) {
		super(posX, posY);
		this.PV = PV ;
		this.XP = 0 ;
		this.setMaxPV(PV) ; 
		this.maxXP = maxXP ; 
		this.attack = attack ;
		this.defense = defense ;
		this.typeInteraction = Interaction.Unbreakable ; 

	}

	// Methods
	public void attack(Character ennemi){	
		int damage = Math.abs(this.attack - ennemi.defense) ; 
		if (damage >= ennemi.PV){
			ennemi.PV = 0 ; 
		} else {
			ennemi.PV -= damage ; 
		}
	}

	public void attack(Character ennemi,Map map){
		int damage = Math.abs(this.attack - ennemi.defense) ; 
		if (damage >= ennemi.PV){
			ennemi.PV = 0 ; 
		} else {
			ennemi.PV -= damage ; 
		}
	}

	public boolean isDead(){
		return this.PV == 0 ;
	}

	public void move(Direction dir){
		System.out.println("je bouge !");
		if (isDead()==false){
			switch(dir){
			case HAUT : super.posY -- ; orientation = HAUT ; break ; 
			case BAS : super.posY ++  ; orientation = BAS ; break ; 
			case GAUCHE : super.posX -- ; orientation = GAUCHE ; break ; 
			case DROITE : super.posX ++ ; orientation = DROITE ; break ; 
			}
		}
		// Notifie les observeurs qu'on a bougé
		setChanged();
		notifyObservers();
	}
	
	

}
