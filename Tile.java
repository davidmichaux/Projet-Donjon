package Model;

import java.awt.image.BufferedImage;

public class Tile extends Model.Object{
	// Attributes
	private BufferedImage texture ; 

	// Getters
	public BufferedImage getTexture() {
		return texture;
	}

	// Setters
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	// Constructors
	public Tile(int posX, int posY, int size, BufferedImage texture, Interaction interaction) {
		super(posX, posY, size);
		this.texture=texture ; 
		this.typeInteraction=interaction ; 
	}

	public Tile(BufferedImage texture, Interaction interaction){
		this(0,0,0,texture,interaction);

	}
}
