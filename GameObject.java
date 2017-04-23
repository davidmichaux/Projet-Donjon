package Model;

import static Model.Interaction.* ;

import java.awt.image.BufferedImage;
import java.util.Observable; 

public abstract class GameObject extends Observable {
	// Attributes
	protected int posX ; 
	protected int posY ; 
	protected Interaction typeInteraction;
	protected int size ; 						/** taille de l'objet **/
	protected BufferedImage texture ; 

	
	// Getters 
	public int getPosX(){
		return posX ; 
	}
	public BufferedImage getTexture() {
		return texture;
	}
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	public int getPosY(){
		return posY ; 
	}
	public Interaction getTypeInteraction(){
		return typeInteraction ; 
	}
	public int getSize() {
		return size ;
	}
	
	// Setters 
	public void setPosX(int posX){
		this.posX = posX ;
	}
	public void setPosY(int posY){
		this.posY = posY ;
	}
	public void setInteraction(Interaction interaction){
		typeInteraction = interaction ;
	}
	public void setSize(int size) {
		this.size = size ;
	}

	// Constructor
	public GameObject(int posX, int posY){
		this.posX = posX ; 
		this.posY = posY ; 
		typeInteraction = Decor ; 
	}
	
	// Methods
	public int[] getCenter(){												/** Renvoie le centre de l'objet (carré) **/
		return new int[]{posX + (size/2), posY + (size/2)} ; 				/** indice 0 : position en x et indice 1 : position en y **/
	}
	public double getDistance(GameObject object){							/** Renvoie la distance entre le GameObject et un autre **/
		int[] myCenter = getCenter(); 
		int[] otherCenter = object.getCenter();
		return Math.sqrt( Math.pow(myCenter[0]-otherCenter[0],2) + Math.pow(myCenter[1]-otherCenter[1],2) ) ;
	}
	public boolean isObstacle(){
		return this.typeInteraction == Breakable || this.typeInteraction == Unbreakable ; 
	}
	public boolean isAtPosition(int x,int y){
		return this.posX == x && this.posY == y;
	}
	public abstract void move(Direction dir);
}
