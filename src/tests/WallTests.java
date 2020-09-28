package tests;

import unsw.dungeon.*;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallTests {
	
	static Goal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 5, 5, inventory);
	static Wall wall = new Wall(6, 5);
	
	@BeforeClass
	public static void setUpClass() {      
		dungeon.addEntity(player);
		dungeon.addEntity(wall);
        System.out.println("WallTests setup");
    }
	
	@Test
    public void creatingNewWallShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for wall", 6, wall.getX());
        assertEquals("getY should return y coordinate for wall", 5, wall.getY());
	}
	
	@Test
    public void wallShouldHandleOnPlayerCollideCorrectly() {
        
        player.moveRight();
        assertEquals("player should not be able to walk right into a wall", 5, player.getX());
        
        wall.setX(4);
        
        player.moveLeft();
        assertEquals("player should not be able to walk left into a wall", 5, player.getX());
        
        wall.setX(5);
        wall.setY(6);
        
        player.moveDown();
        assertEquals("player should not be able to walk down into a wall", 5, player.getY());
        
        wall.setY(4);
        
        player.moveUp();
        assertEquals("player should not be able to walk up into a wall", 5, player.getY());
	}
	
	@Test
    public void wallShouldHandleOnBoulderCollideCorrectly() {
		
		// pushing right
		Boulder boulder = new Boulder(dungeon, 6, 5);
		dungeon.addEntity(boulder);
		
		wall.setX(7);
        wall.setY(5);
        
        player.moveRight();
        assertEquals("player should not be able to push a boulder right into a wall", 5, player.getX());
        
        // pushing left
        boulder.setX(4);
        wall.setX(3);
        
        player.moveLeft();
        assertEquals("player should not be able to push a boulder left into a wall", 5, player.getX());
        
        //pushing down
        boulder.setX(5);
        boulder.setY(6);
        
        wall.setX(5);
        wall.setY(7);
        
        player.moveDown();
        assertEquals("player should not be able to push a boulder down into a wall", 5, player.getY());
        
        // pushing up
        boulder.setY(4);
        wall.setY(3);
        
        player.moveUp();
        assertEquals("player should not be able to push a boulder up into a wall", 5, player.getY());
    }
	
	@Test
    public void wallShouldHandleOnEnemyCollideCorrectly() {
       // TODO: test when an enemy walks into a wall,
		// it doesn't walk onto its position
    }
}
