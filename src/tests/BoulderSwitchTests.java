package tests;

import unsw.dungeon.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoulderSwitchTests {
	
	static Goal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 5, 5, inventory);
	static Boulder boulder = new Boulder(dungeon, 6, 5);
	
	@BeforeClass
	public static void setUpClass() {      
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		
        System.out.println("BoulderTests setup");
    }
	
	@Before
	public void setUp() {
		player.setX(5);
		player.setY(5);
		
		boulder.setX(6);
		boulder.setY(5);
	}
	
	@Test
	public void A_CreatingNewBoulderShouldSetupPropertiesCorrectly() {

        
        // assert statements
        assertEquals("getX should return x coordinate for boulder", 6, boulder.getX());
        assertEquals("getY should return y coordinate for boulder", 5, boulder.getY());
	}
	
	@Test
	public void B_PlayerShouldBeAbleToPushBoulderIfNothingBehindIt() {

        
		player.moveRight();
        assertEquals("player should be able to push boulder right if nothing behind it", 6, player.getX());
        assertEquals("player should be able to push boulder right if nothing behind it", 5, player.getY());
        assertEquals("player should be able to push boulder right if nothing behind it", 7, boulder.getX());
        assertEquals("player should be able to push boulder right if nothing behind it", 5, boulder.getY());
        
        player.moveUp();
        player.moveRight();
        
        player.moveDown();
        assertEquals("player should be able to push boulder down if nothing behind it", 7, player.getX());
        assertEquals("player should be able to push boulder down if nothing behind it", 5, player.getY());
        assertEquals("player should be able to push boulder down if nothing behind it", 7, boulder.getX());
        assertEquals("player should be able to push boulder down if nothing behind it", 6, boulder.getY());
        
        player.moveRight();
        player.moveDown();
        
        player.moveLeft();
        assertEquals("player should be able to push boulder left if nothing behind it", 7, player.getX());
        assertEquals("player should be able to push boulder left if nothing behind it", 6, player.getY());
        assertEquals("player should be able to push boulder left if nothing behind it", 6, boulder.getX());
        assertEquals("player should be able to push boulder left if nothing behind it", 6, boulder.getY());
        
        player.moveDown();
        player.moveLeft();
        
        player.moveUp();
        assertEquals("player should be able to push boulder up if nothing behind it", 6, player.getX());
        assertEquals("player should be able to push boulder up if nothing behind it", 6, player.getY());
        assertEquals("player should be able to push boulder up if nothing behind it", 6, boulder.getX());
        assertEquals("player should be able to push boulder up if nothing behind it", 5, boulder.getY());
	}
	
	@Test
	public void C_PlayerShouldBeAbleToPushBoulderIfSwitchBehindIt() {

		
		Switch mySwitch = new Switch(dungeon, goals, 7, 5);
		dungeon.addEntity(mySwitch);
		
		player.moveRight();
        assertEquals("player should be able to push boulder right if switch behind it", 6, player.getX());
        assertEquals("player should be able to push boulder right if switch behind it", 5, player.getY());
        assertEquals("player should be able to push boulder right if switch behind it", 7, boulder.getX());
        assertEquals("player should be able to push boulder right if switch behind it", 5, boulder.getY());
        
        player.moveUp();
        player.moveRight();
        
        mySwitch.setY(6);
        
        player.moveDown();
        assertEquals("player should be able to push boulder down if switch behind it", 7, player.getX());
        assertEquals("player should be able to push boulder down if switch behind it", 4, player.getY());
        assertEquals("player should be able to push boulder down if switch behind it", 7, boulder.getX());
        assertEquals("player should be able to push boulder down if switch behind it", 5, boulder.getY());
        
        player.moveRight();
        player.moveDown();
        
        mySwitch.setX(6);
        
        player.moveLeft();
        assertEquals("player should be able to push boulder left if switch behind it", 7, player.getX());
        assertEquals("player should be able to push boulder left if switch behind it", 5, player.getY());
        assertEquals("player should be able to push boulder left if switch behind it", 6, boulder.getX());
        assertEquals("player should be able to push boulder left if switch behind it", 5, boulder.getY());
        
        player.moveDown();
        //player.moveLeft();
        
        mySwitch.setY(5);
        
        player.moveUp();
        assertEquals("player should be able to push boulder up if switch behind it", 7, player.getX());
        assertEquals("player should be able to push boulder up if switch behind it", 5, player.getY());
        assertEquals("player should be able to push boulder up if switch behind it", 6, boulder.getX());
        assertEquals("player should be able to push boulder up if switch behind it", 5, boulder.getY());
    }
	
	@Test
	public void D_PlayerShouldNotBeAbleToPushBoulderIfEntityBehindIt() {
		
		
		Bomb bomb = new Bomb(7, 5);
		dungeon.addEntity(bomb);
		
		player.moveRight();
		
        assertEquals("player shouldn't be able to push boulder right if entity behind it", 5, player.getX());
        assertEquals("player shouldn't be able to push boulder right if entity behind it", 5, player.getY());
        assertEquals("player shouldn't be able to push boulder right if entity behind it", 6, boulder.getX());
        assertEquals("player shouldn't be able to push boulder right if entity behind it", 5, boulder.getY());
        
        player.moveUp();
        player.moveRight();
        
        System.out.println(boulder.getX());
        bomb.setX(7);
        bomb.setY(6);
        
        player.moveDown();
        assertEquals("player shouldn't be able to push boulder down if entity behind it", 6, player.getX());
        assertEquals("player shouldn't be able to push boulder down if entity behind it", 5, player.getY());
        assertEquals("player shouldn't be able to push boulder down if entity behind it", 6, boulder.getX());
        assertEquals("player shouldn't be able to push boulder down if entity behind it", 6, boulder.getY());
        
        player.moveRight();
        player.moveDown();
        
        bomb.setX(6);
        
        player.moveLeft();
        assertEquals("player shouldn't be able to push boulder left if entity behind it", 6, player.getX());
        assertEquals("player shouldn't be able to push boulder left if entity behind it", 6, player.getY());
        assertEquals("player shouldn't be able to push boulder left if entity behind it", 5, boulder.getX());
        assertEquals("player shouldn't be able to push boulder left if entity behind it", 6, boulder.getY());
        
        player.moveDown();
        player.moveLeft();
        
        bomb.setY(5);
        
        player.moveUp();
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, player.getX());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 6, player.getY());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, boulder.getX());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, boulder.getY());
    }
	
	@Test 
	public void E_CreatingMultipleBombsShouldSetUpPropertiesCorrectly() {
		Bomb bomb2 = new Bomb(7, 6);
		dungeon.addEntity(bomb2);
		assertEquals("dungeon should have 2 bomb", 2, dungeon.getAllEntitiesOfType(bomb2).size());
		
		Bomb bomb3 = new Bomb(6, 6);
		dungeon.addEntity(bomb3);
		assertEquals("dungeon should have 3 bombs", 3, dungeon.getAllEntitiesOfType(bomb3).size());
				
	}
	
	@Test
	public void F_PlayerShouldBeAbleToDropAndUseBomb() throws InterruptedException {

		
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, player.getX());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, player.getY());
       // assertEquals("player shouldn't be able to push boulder up if entity behind it", 6, player.getY());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 6, boulder.getX());
       // assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, boulder.getY());
        
        Bomb bomb4 = new Bomb(8,8);
		dungeon.addEntity(bomb4);
		//assertEquals("player shouldn't have bomb 4", null, player.getInventory().findEntity(bomb4).getClass());
        assertEquals("dungeon should have 4 bomb", 4, dungeon.getAllEntitiesOfType(bomb4).size());
		player.moveRight();
		player.moveRight();
		player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        
        
        assertEquals("player should have bomb 4", bomb4.getClass(), player.getInventory().findEntity(bomb4).getClass());
      //  bomb3.
        player.moveUp();
        player.moveUp();
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 8, player.getX());
        assertEquals("player shouldn't be able to push boulder up if entity behind it", 6, player.getY());
        
        player.useBomb(bomb4);
       // assertEquals("player shouldn't have bomb 4", null, player.getInventory().findEntity(bomb4).getClass());

        Thread.sleep(5500);
       // assertEquals("player shouldn't be able to push boulder up if entity behind it", 6, player.getY());
        assertEquals("boulder has exploded and should be gone", 999, boulder.getX());
        assertEquals("boulder has exploded and should be gone", 999, boulder.getY());
        assertEquals("player has exploded and should be gone", 999, player.getX());
        assertEquals("player has exploded and should be gone", 999, player.getY());
       // assertEquals("player shouldn't be able to push boulder up if entity behind it", 5, boulder.getY());
	}
}
