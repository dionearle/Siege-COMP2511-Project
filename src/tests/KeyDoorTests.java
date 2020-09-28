package tests;

import unsw.dungeon.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KeyDoorTests {
	
	static Goal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 0, 0, inventory);
	static Key key1 = new Key(0, 3, 3);
	static Door door1 = new Door(0, 4, 4);
	static Key key2 = new Key(0, 5, 5);
	static Door door2 = new Door(0, 6, 6);

	
	@BeforeClass
	public static void setUpClass() {
		dungeon.addEntity(player);
		dungeon.addEntity(key1);
		dungeon.addEntity(door1);
		dungeon.addEntity(key2);
		dungeon.addEntity(door2);
		//treasure1.registerGoalObserver(dungeon);
        System.out.println("PlayerTests setup");
    }
	
	@Test
    public void A_creatingNewPlayerShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for player", 0, player.getX());
        assertEquals("getY should return y coordinate for player", 0, player.getY());
        assertNotNull("player's inventory should be empty upon start", player.getInventory());
        //assertEquals("player should not be potioned upon start", false, player.isPotioned());

	}
	
	
	@Test
    public void B_creatingNewKeysShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for treasure", 3, key1.getX());
        assertEquals("getY should return y coordinate for treasure", 3, key1.getY());
        assertEquals("getX should return x coordinate for treasure", 5, key2.getX());
        assertEquals("getY should return y coordinate for treasure", 5, key2.getY());
        //assertNotNull("player's inventory should be empty upon start", player.getInventory());
        //assertEquals("player should not be potioned upon start", false, player.isPotioned());

	}
	
	@Test
    public void C_creatingNewDoorsShouldSetupPropertiesCorrectly() {
        
        // assert statements
		assertEquals("getX should return x coordinate for treasure", 4, door1.getX());
        assertEquals("getY should return y coordinate for treasure", 4, door1.getY());
        assertEquals("getX should return x coordinate for treasure", 6, door2.getX());
        assertEquals("getY should return y coordinate for treasure", 6, door2.getY());
        //assertNotNull("player's inventory should be empty upon start", player.getInventory());
        //assertEquals("player should not be potioned upon start", false, player.isPotioned());

	}
	
	
	@Test
	public void D_pickingUpSingleKeyShouldAddToInventoryCorrectly() {
		assertEquals("dungeon should have 2 doors", 2, dungeon.getAllEntitiesOfType(door1).size());
		assertEquals("dungeon should have 2 keys", 2, dungeon.getAllEntitiesOfType(key1).size());
		assertEquals("player shouldn't have key 0", false, player.getInventory().checkMatchingKeyID(0));
		assertEquals("player shouldn't have key 1", false, player.getInventory().checkMatchingKeyID(1));
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 2 doors", 2, dungeon.getAllEntitiesOfType(door1).size());
		assertEquals("dungeon should have 2 keys", 2, dungeon.getAllEntitiesOfType(key1).size());
		assertEquals("player shouldn't have key 0", false, player.getInventory().checkMatchingKeyID(0));
		assertEquals("player shouldn't have key 1", false, player.getInventory().checkMatchingKeyID(1));
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 2 doors", 2, dungeon.getAllEntitiesOfType(door1).size());
		assertEquals("dungeon should have 2 keys, as not yet used", 2, dungeon.getAllEntitiesOfType(key1).size());
		assertEquals("player should have key 0", true, player.getInventory().checkMatchingKeyID(0));
		assertEquals("player shouldn't have key 1", false, player.getInventory().checkMatchingKeyID(1));
	}
	
	@Test
	public void E_pickingUpSingleKeyShouldUnlockSingleDoor() {
		assertEquals("door 1 should be locked", door1.getClosedState(), door1.getState());
		player.moveDown();
		player.moveRight();
		assertEquals("door 1 should be opened", door1.getOpenState(), door1.getState());

	}
	
	@Test
	public void F_pickingUpMultipleKeysShouldAddToInventoryCorrectly() {
		assertEquals("player shouldn't have key 0", false, player.getInventory().checkMatchingKeyID(0));
		assertEquals("player shouldn't have key 1", false, player.getInventory().checkMatchingKeyID(1));
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 2 doors", 2, dungeon.getAllEntitiesOfType(door1).size());
		assertEquals("dungeon should have 2 keys", 2, dungeon.getAllEntitiesOfType(key1).size());
		assertEquals("player shouldn't have key 0", true, player.getInventory().checkMatchingKeyID(0));
		assertEquals("player should have key 1", false, player.getInventory().checkMatchingKeyID(1));
	}
	
	
	@Test
	public void G_pickingUpMultipleKeysShouldUnlockMultipleDoors() {
		assertEquals("door 2 should be locked", door2.getClosedState(), door2.getState());
		player.moveDown();
		player.moveRight();
		assertEquals("door 2 should be opened", door2.getOpenState(), door2.getState());

	}

}
