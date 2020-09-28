package tests;

import unsw.dungeon.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TreasureTests {
	
	static CompositeGoal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 0, 0, inventory);
	static Treasure treasure1 = new Treasure(dungeon, goals, 3, 2);
	static Treasure treasure2 = new Treasure(dungeon, goals, 3, 3);
	static Treasure treasure3 = new Treasure(dungeon, goals, 4, 4);
	
	@BeforeClass
	public static void setUpClass() {
		goals.setLogicalOperator(new AndOperator(goals));
		dungeon.addEntity(player);
		dungeon.addEntity(treasure1);
		dungeon.addEntity(treasure2);
		dungeon.addEntity(treasure3);
		treasure1.registerGoalObserver(dungeon);
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
    public void B_creatingNewTreasureShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for treasure", 3, treasure1.getX());
        assertEquals("getY should return y coordinate for treasure", 2, treasure1.getY());
        //assertNotNull("player's inventory should be empty upon start", player.getInventory());
        //assertEquals("player should not be potioned upon start", false, player.isPotioned());

	}
	
	@Test
	public void C_pickingUpSingleTreasureShouldAddToInventoryCorrectly() {
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 1 treasure", 3, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 0 treasures", 0, player.getInventory().getWealth(treasure1));
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 1 treasure", 3, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 0 treasures", 0, player.getInventory().getWealth(treasure1));
		//player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 0 treasures (collected)", 2, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 1 treasure (collected)", 1, player.getInventory().getWealth(treasure1));
	}
	
	@Test
	public void D_pickingUpMultipleTreasuresShouldAddToInventoryCorrectly() {
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 1 treasure", 1, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 0 treasures", 2, player.getInventory().getWealth(treasure1));
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 3 treasure", 0, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 0 treasures", 3, player.getInventory().getWealth(treasure1));
		//
		player.moveRight();
		assertEquals("dungeon should have 2 treasures (collected)", 0, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 1 treasure (collected)", 3, player.getInventory().getWealth(treasure1));
		player.moveDown();
		assertEquals("dungeon should have 1 treasures (collected)", 0, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 2 treasure (collected)", 3, player.getInventory().getWealth(treasure1));
		player.moveDown();
		player.moveRight();
		assertEquals("dungeon should have 0 treasures (collected)", 0, dungeon.getAllEntitiesOfType(treasure1).size());
		assertEquals("player should have 3 treasure (collected)", 3, player.getInventory().getWealth(treasure1));
	}
	
}
