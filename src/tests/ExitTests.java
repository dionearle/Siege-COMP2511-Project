package tests;

import unsw.dungeon.*;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExitTests {
	
	static Goal goal = new LeafGoal("exit");
	static Dungeon dungeon = new Dungeon(10, 10, goal);
	static Inventory inventory = new Inventory();
	static Player player = new Player(dungeon, 5, 5, inventory);
	static Exit exit = new Exit(dungeon, goal, 6, 5);
	
	@BeforeClass
	public static void setUpClass() {      
		dungeon.addEntity(player);
		dungeon.addEntity(exit);
		exit.registerGoalObserver(dungeon);
        System.out.println("ExitTests setup");
    }
	
	@Test
    public void creatingNewWallShouldSetupPropertiesCorrectly() {
        
        // assert statements
        assertEquals("getX should return x coordinate for exit", 6, exit.getX());
        assertEquals("getY should return y coordinate for wall", 5, exit.getY());
        assertNotNull("player's inventory should be empty upon start", exit.getGoal());
	}
	
	@Test
    public void walkingIntoExitCompleteDungeonIfOnlyGoal() {
		
        player.moveRight();
        assertTrue("player walking into exit should update relevant goal", exit.getGoal().isCompleted());
        assertTrue("dungeon should be complete if all goals are completed", dungeon.getGoals().isCompleted());
        
        assertTrue("walking away from exit should still keep the goal true", exit.getGoal().isCompleted());
        assertTrue("if dungeon is completed it should stay this way", dungeon.getGoals().isCompleted());
        
	}
	
	@Test
    public void walkingIntoExitShouldNotCompleteDungeonIfOtherGoals() {
		// TODO: setup a dungeon that has a composite goal, and then show that getting to exit doesn't complete level
        
	}
}
