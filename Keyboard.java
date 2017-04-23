package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Direction;
import Model.Player;

public class Keyboard implements KeyListener {
	private Player player;

	public Keyboard(Player player) {
		this.player = player ;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode()) {
			case KeyEvent.VK_UP : player.move(Direction.HAUT) ; break ;
		 	case KeyEvent.VK_DOWN : player.move(Direction.BAS); ; break ;
			case KeyEvent.VK_LEFT : player.move(Direction.GAUCHE) ; break ;
			case KeyEvent.VK_RIGHT : player.move(Direction.DROITE) ; break ;
			default: break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
