package tests;

import unsw.dungeon.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EnemySwordPotionsTests {
	
	static CompositeGoal goals = new CompositeGoal("test");
	static Dungeon dungeon = new Dungeon(10, 10, goals);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 0, 0, inventory);
	static Enemy enemy1 = new Enemy(dungeon, goals, 4, 4);
	static Sword sword1 = new Sword(dungeon, 1, 1);
	static Enemy enemy2 = new Enemy(dungeon, goals, 5, 5);
	static Sword sword2 = new Sword(dungeon, 2, 2);
	static Invincibility potion = new Invincibility(1,0);
	
	@BeforeClass
	public static void setUpClass() {
		goals.setLogicalOperator(new AndOperator(goals));
		dungeon.addEntity(player);
		dungeon.addEntity(sword1);
		dungeon.addEntity(enemy1);
		dungeon.addEntity(sword2);
		dungeon.addEntity(enemy2);
		dungeon.addEntity(potion);
		enemy1.registerGoalObserver(dungeon);
		enemy2.registerGoalObserver(dungeon);
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
    public void B_creatingNewEnemiesShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for treasure", 4, enemy1.getX());
        assertEquals("getX should return x coordinate for treasure", 4, enemy1.getY());
        
        assertEquals("getX should return x coordinate for treasure", 5, enemy2.getX());
        assertEquals("getX should return x coordinate for treasure", 5, enemy2.getY());
	}
	
	@Test
    public void C_creatingNewSwordsShouldSetupPropertiesCorrectly() {
        
        // assert statements        
        assertEquals("getY should return y coordinate for treasure", 1, sword1.getY());
        assertEquals("getY should return y coordinate for treasure", 1, sword1.getY());
        
        assertEquals("getY should return y coordinate for treasure", 2, sword2.getY());
        assertEquals("getY should return y coordinate for treasure", 2, sword2.getY());
	}
	
	@Test
	public void D_pickingUpSingleSwordShouldAddToInventoryCorrectly() {
		
		assertEquals("dungeon should have 2 swords", 2, dungeon.getAllEntitiesOfType(sword1).size());
		assertEquals("player shouldn't have a sword", null, player.getInventory().findEntity(sword1));

		player.moveDown();
		player.moveRight();
		
		assertEquals("dungeon should have 2 swords, as not yet used", 2, dungeon.getAllEntitiesOfType(sword1).size());
		assertNotEquals("player should have sword 1", null, player.getInventory().findEntity(sword1));
	}
	
	@Test
	public void E_usingASwordOnceShouldKeepItInTheInventory() {
		
		assertNotEquals("player should have a sword", null, player.getInventory().findEntity(sword1));
		
		player.useSword(sword1);
		
		assertEquals("dungeon should have 2 swords, as only used once", 2, dungeon.getAllEntitiesOfType(sword1).size());
		assertNotEquals("player should still have sword 1 as still got uses left", null, player.getInventory().findEntity(sword2));
	}
	
	@Test
	public void F_usingASwordMultipleTimesShouldRemoveItFromInventory() {
		
		assertNotEquals("player should have a sword", null, player.getInventory().findEntity(sword1));
		
		player.useSword(sword1);
		player.useSword(sword1);
		player.useSword(sword1);
		player.useSword(sword1);
		
		assertEquals("dungeon should have 1 sword, as one has been destroyed", 1, dungeon.getAllEntitiesOfType(sword1).size());
		assertEquals("player should not have sword as 5 hits used", null, player.getInventory().findEntity(sword2));
	}
	
	@Test
	public void G_pickingUpANewSwordShouldAddToInventoryCorrectly() {
		
		assertEquals("player should not have a sword", null, player.getInventory().findEntity(sword1));
		
		player.moveDown();
		player.moveRight();
		
		assertEquals("dungeon should have 1 sword, as not yet used", 1, dungeon.getAllEntitiesOfType(sword1).size());
		assertNotEquals("player should have sword 1", null, player.getInventory().findEntity(sword2));
	}
	
	@Test
	public void H_hittingEnemyWithSwordShouldKillEnemy() {
		
		player.setX(0);
		player.setY(0);
		
		enemy1.setX(0);
		enemy1.setY(1);
		
		assertNotEquals("player should have a sword", null, player.getInventory().findEntity(sword1));
		
		player.useSword(sword1);
		
		assertEquals("dungeon should have 1 enemy, as 1 has been killed", 1, dungeon.getAllEntitiesOfType(enemy1).size());
		assertNotEquals("player should still have sword 1", null, player.getInventory().findEntity(sword2));
	}
	
	@Test
	public void I_collidingWithEnemyWhenPotionedShouldKillEnemy() {
		
		player.setX(0);
		player.setY(0);
		
		enemy1.setX(2);
		enemy1.setY(0);
		
		assertEquals("player should not have a potion", null, player.getInventory().findEntity(potion));
		
		player.moveRight();
		
		assertNotEquals("player should have a potion", null, player.getInventory().findEntity(potion));
		assertEquals("dungeon should have 1 potion, as not yet used", 1, dungeon.getAllEntitiesOfType(potion).size());

		player.moveRight();
		assertEquals("dungeon should have no enemies, as now both are killed", 1, dungeon.getAllEntitiesOfType(enemy1).size());
		assertNotEquals("player should still have potion", null, player.getInventory().findEntity(potion));
	}
	
	@Test
	public void J_collidingWithEnemyShouldKillPlayer() {
			
		player.setX(3);
		player.setY(4);
		
		player.moveRight();
		
		assertEquals("Player should be dead if colliding with enemy", 0, dungeon.getAllEntitiesOfType(player).size());
	}

	
}
