package View;

import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import Controller.Keyboard;
import Model.Player;

public class Application extends JFrame {

	public Application() throws FileNotFoundException {
		this.setTitle("Donjon !");
		this.setPreferredSize(new Dimension(1280, 960));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new Map("Level2.txt", new Player (0, 0, 100, 100, 100, 100) ));
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			Application app = new Application();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
