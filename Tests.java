import Model.* ;
import Model.Character;
import View.Map;

import static Model.Direction.* ; 
import static Model.Interaction.* ; 
import static Model.Stat.* ; 
import static Model.State.* ;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test; 

public class Tests {

	// Character class methods tests
	@Test
	public void moveTest(){
		Player player = new Player(10,10,100,0,0,0);
		player.move(HAUT);
		assertEquals(player.getPosY(),9);
	}

	@Test
	public void attackTest(){
		Player player = new Player(0,0,100,0,50,60);
		Player ennemi = new Player(0,0,40,0,50,20);
		player.attack(ennemi); 
		assertEquals(10,ennemi.getPV());
		ennemi.attack(player);
		assertEquals(player.getPV(),90);
	}

	@Test
	public void isDeadTest(){
		Player player = new Player(0,0,0,0,50,60);
		assertEquals(player.isDead(),true);
	}

	@Test
	public void isDeadTest1(){
		Player player = new Player(0,0,10,0,50,60);
		assertEquals(player.isDead(),false);
	}

	// Player class methods tests
	@Test
	public void useAmmoTest(){
		Player player = new Player(0,0,10,0,50,60);
		player.useAmmo();
		assertEquals(player.getAmmo(),9);
	}

	@Test 
	public void canUseAmmoTest(){
		Player player = new Player(0,0,10,0,50,60);
		assertEquals(player.canUseAmmo(),true);
	}

	@Test 
	public void ammoTest(){
		Player player = new Player(10,0,20,30);
		player.setAmmo(0);
		assertEquals(player.canUseAmmo(),false);
	}

	@Test(expected=IllegalArgumentException.class) 
	public void setAmmoTest(){
		Player player = new Player(10,0,20,30);
		player.setAmmo(-5);
	}

	@Test
	public void addAtkTest(){
		Player player = new Player(10,0,20,30);
		player.addAtk(10);
		assertEquals(player.getAttack(),30);
	}

	@Test
	public void addDefTest(){
		Player player = new Player(10,0,20,30);
		player.addDef(10);
		assertEquals(player.getDefense(),40);
	}

	@Test
	public void addXPTest(){
		Player player = new Player(10,30,20,30);
		player.addXP(10);
		assertEquals(player.getXP(),10);
	}

	@Test 							// erreur casts
	public void useItemTest(){
		Player player = new Player(100,100,20,30);
		player.setPV(10);
		Potion potion = new Potion("Super potion",PV);
		player.goToInventory(potion);
		player.useItem(potion);
		assertEquals(player.getPV(),15);
	} 

	@Test							
	public void goToInventoryTest(){
		Player player = new Player(0,0,0,0); 
		assertEquals(player.getInventory().isEmpty(),true); 
		Potion potion = new Potion("Super potion",PV);
		player.goToInventory(potion);
		assertEquals(player.getInventory().isEmpty(),false); 
	} 

	@Test(expected=IllegalArgumentException.class) 
	public void attackSpecialTest(){
		Player player = new Player(50,100,0,0);
		player.setXP(50);
		ArrayList<Character> ennemis = new ArrayList<Character>() ; 
		player.attackSpecial(ennemis);
		int count = 0 ; 
		for (int i=0;i<ennemis.size();i++){
			count += ennemis.get(i).getPV() ; 
		}
		assertEquals(count,0) ; 

	}

	@Test 
	public void attackSpecialTest1(){
		Player player = new Player(50,100,0,0);
		player.setXP(100);
		ArrayList<Character> ennemis = new ArrayList<Character>() ; 
		player.attackSpecial(ennemis);
		int count = 0 ; 
		for (int i=0;i<ennemis.size();i++){
			count += ennemis.get(i).getPV() ; 
		}
		assertEquals(count,0) ; 
	}
	
	/**
	@Test
	 public void longDistanceAttackTest(){
		Map map = new Map() ; 
		Player player = new Player(5, 10, 100, 200, 40, 40) ;
		Mouse mouse = new Mouse(15, 10, 50, 0, 5, 10) ; 
		player.longDistanceAttack(map);
		assertEquals(mouse.getPV(),0) ; 
	}
	**/
	
	// Mouse class methods tests
	@Test
	 public void attackMouseTest(){
		Player player = new Player(100,100,50,60);
		Mouse mouse = new Mouse(30,200,10,20) ;
		mouse.attack(player);
		assertEquals(player.getState(),Confused); 
	}
	
	// Skeleton class methods tests
	/** void **/ 
	
	// Ghost class methods tests
	@Test
	public void attackGhostTest(){
		
	}
	
	// Inventory class methods tests
	@Test
	public void isEmptyTest(){
		Inventory inv = new Inventory();
		assertEquals(true,inv.isEmpty());
		inv.add(new Item("Dave"));
		assertEquals(false,inv.isEmpty());
	}
	
	@Test
	public void existsTest(){
		Player player = new Player(50,100,30,40);
		assertEquals("",player.getInventory().toString());
		player.goToInventory(new Item("Dave"));
		assertEquals(false,player.getInventory().isEmpty()) ; 
	}
	
	@Test
	public void dropRandomObject(){
		Player player = new Player(10,30,20,35); 
		player.getInventory().add(new Item("David"));
		assertEquals(false,player.getInventory().isEmpty());
		player.setInventory(new Inventory());			// inventaire vide
		Item item = player.getInventory().dropRandomItem() ; 
		assertEquals(null,item); 
	}
	
	// Item class methods tests
	@Test
	public void equalsTest(){
		Item item1 = new Item("David"); 
		Item item2 = new Item("David") ; 
		assertEquals(item1.equals(item2), true);
	}
	
	@Test
	public void toStringItemTest(){
		Item item1 = new Item("David"); 
		assertEquals("David",item1.toString()) ; 
	}
	
	// Potion class methods tests
	@Test 
	public void useToTest(){
		Potion potion = new Potion("Hyper potion", Attack); 
		Player player = new Player(200,150,30,40);
		potion.useTo(player);
		assertEquals(40,player.getAttack());
	}
	
	@Test 
	public void useToTest1(){
		Potion potion = new Potion("Hyper potion", PV); 
		Player player = new Player(200,150,30,40);
		potion.useTo(player);
		assertEquals(200,player.getPV());
	}
	
	@Test 
	public void useToTest2(){
		Potion potion = new Potion("Hyper potion", PV); 
		Player player = new Player(200,150,30,40);
		player.setPV(120);
		potion.useTo(player);
		assertEquals(130,player.getPV());
	}
	
	@Test 
	public void useToTest3(){
		Potion potion = new Potion("Hyper potion", PV); 
		Player player = new Player(200,150,30,40);
		player.setPV(10);
		potion.useTo(player);
		assertEquals(20,player.getPV());
	}
	
	public void useToTest4(){
		Potion potion = new Potion("Super potion", XP); 
		Player player = new Player(200,150,30,40);
		potion.useTo(player);
		assertEquals(5,player.getXP());
		potion.useTo(player);
		assertEquals(10,player.getXP());
	}
	
	// Vase class methods tests
	/** void **/
	
	
}

