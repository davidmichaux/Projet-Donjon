package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.Keyboard;
import Model.GameObject;
import Model.Interaction;
import Model.Player;
import Model.Tile;

public class Map extends JPanel implements Observer {
	// Attributes
	private ArrayList<GameObject> objects ; 

	// la map
	private Tile[][][] map;
	private String currentLevel;
	private static final BufferedImage[] tileset = Map.createTileset();
	private static final int SIZE_TILE = 32;
	private Player player ;

	// Setters
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}

	// Constructor
	public Map() throws FileNotFoundException {
		this("level2.txt", null);
	}

	public Map(String level, Player p) throws FileNotFoundException {
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.currentLevel = level;
		this.player = p ;
		initialiseLevel();
		this.addKeyListener(new Keyboard(this.player));
		player.addObserver(this);
	}

	private void initialiseLevel() {
		try {
			int x, y, z, indexTile;
			String[] tiles;
			InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream("/levels/" + currentLevel));
			BufferedReader br    = new BufferedReader(in);
			br.readLine(); // Le premier readLine() permet de sauter la première ligne "# Nombre de couches"
			z = Integer.parseInt(br.readLine()); 		// renvoie la hauteur en z de la carte (nbre de couches)
			br.readLine(); // Ce readLine() permet de sauter la troisième ligne "# Hauteur"
			y = Integer.parseInt(br.readLine()); 		// on met le nombre la hauteur en y
			br.readLine(); // Ce readLine() permet de sauter la cinquième ligne "# Largeur"
			x = Integer.parseInt(br.readLine());		// on met la largeur en x
			this.map = new Tile[z][y][x]; // On définit nos trois dimensions

			// Ensuite, on crée notre level pour chaque couche :
			for(int k = 0; k < z; k++)
			{
				br.readLine(); // On saute la ligne "# Couche N"
				for(int i = 0; i < y; i++) 
				{
					tiles = br.readLine().split(" ");
					for(int j = 0; j < x; j++) 
					{
						if(Integer.parseInt(tiles[j])-1 >= 0)
							indexTile = Integer.parseInt(tiles[j])-1;
						else
							indexTile = 0;

						this.map[k][i][j] = new Tile(
								i * SIZE_TILE, 
								j * SIZE_TILE,
								SIZE_TILE,
								Map.tileset[indexTile], 
								Interaction.Decor);
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	// Methods
	public GameObject collision(GameObject object){
		for (GameObject obj : objects) {
			if(obj.isObstacle() && obj.getDistance(object) <= ((object.getSize()/2)+(obj.getSize()/2))){
				return obj ; 
			}
		}
		return null ; /** si pas de collision avec un objet, renvoie nul **/
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On affiche la map
		for(int k = 0; k < map.length; k++){
			for(int i = 0; i < map[0].length; i++) 
				for(int j = 0; j < map[0][0].length; j++) 
					g.drawImage(map[k][i][j].getTexture(), 
							map[k][i][j].getPosY(), 
							map[k][i][j].getPosX(),
							null);
		}
		g.setColor(new Color(0, 0, 0, 50));
		g.fillOval(player.getPosX() + 8, player.getPosY() + 52, 48, 16);
		g.drawImage(player.getTexture(), player.getPosX(),  player.getPosY(), null);
	}


	private static BufferedImage[] createTileset() {
		BufferedImage[] set = null;
		try {
			BufferedImage sheet = ImageIO.read(Map.class.getResourceAsStream("/resources/tileset32x32.png"));
			// Il y a autant de tiles que (longueurTileset / SIZE_TILE) x (largeurTileset / SIZE_TILE) 
			int nbTiles = (sheet.getHeight() / SIZE_TILE) * (sheet.getWidth() / SIZE_TILE);
			set = new BufferedImage[nbTiles];
			int idx = 0;
			for(int i = 0; i < sheet.getHeight(); i+=SIZE_TILE) { // height-1 car l'image fait 705 px au lieu de 704
				for(int j = 0; j < sheet.getWidth(); j+=SIZE_TILE) {
					set[idx] = sheet.getSubimage(j, i, SIZE_TILE, SIZE_TILE);
					idx++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("abonné notifié !");
		repaint();
	}
}





