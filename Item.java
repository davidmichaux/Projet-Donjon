package Model;

import static Model.Interaction.* ; 
import View.Map ; 

public class Item extends Object {
	// Attributes	
	String name ; 

	// Constructor 
	public Item(int posX, int posY, int size,String name) {
		super(posX, posY, size);
		this.typeInteraction = Movable ; 
		this.name = name ; 
	}

	public Item(String name){
		this(0,0,0,name);
	}

	// Method
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(java.lang.Object obj) {
		return this.name.equals(((Item)obj).name);
	}

}	
