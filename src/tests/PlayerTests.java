package tests;

import unsw.dungeon.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTests {
	
	static Goal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 0, 0, inventory);
	
	@BeforeClass
	public static void setUpClass() {
        System.out.println("PlayerTests setup");
    }
	
	@Test
    public void creatingNewPlayerShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for player", 0, player.getX());
        assertEquals("getY should return y coordinate for player", 0, player.getY());
        assertNotNull("player's inventory should be empty upon start", player.getInventory());

	}
	
	@Test
	public void playerShouldBeAbleToNavigate() {
		        
		player.moveRight();
		assertEquals("player should be able to move right", 1, player.getX());
		        
		player.moveLeft();
		assertEquals("player should be able to move left", 0, player.getX());
		        
		player.moveDown();
		assertEquals("player should be able to move up", 1, player.getY());
		        
		player.moveUp();
		assertEquals("player should be able to move down", 0, player.getY());
	}

}
