package Model;

public class Object extends GameObject {
	// Attributes : heritent de ceux de GO (posX,posY,typeInteraction,size)

	// Getters
	public Interaction getTypeInteraction() {
		return typeInteraction;
	}

	// Constructor
	public Object(int posX, int posY, int size) {
		super(posX, posY);
		this.size=size ; 
	}

	@Override 
	public void move(Direction dir){

	}
	
}
