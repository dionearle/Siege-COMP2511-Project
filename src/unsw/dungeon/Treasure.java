package unsw.dungeon;

import java.util.ArrayList;

public class Treasure extends Entity implements GoalSubject {
	
	private Goal treasureGoal;
	private Dungeon dungeon;
	private ArrayList<GoalObserver> listObservers = new ArrayList<GoalObserver>();

	/**
	 * Treasure for collection in dungeon gameplay.
	 * @param dungeon 		Dungeon treasure is in.
	 * @param treasureGoal	Associated goals with the treasure.
	 * @param x				Location of treasure.
	 * @param y				Location of treasure.
	 */
	public Treasure(Dungeon dungeon, Goal treasureGoal, int x, int y) { //unsure if needs dungeon
		super(x, y);
		this.treasureGoal = treasureGoal;
		this.dungeon = dungeon;
	}
	
	public Goal getGoal() {
		return treasureGoal;
	}
	
	public void registerGoalObserver(GoalObserver observer) {
		if(!listObservers.contains(observer)) { 
			listObservers.add(observer); 
		}
	}

	public void removeGoalObserver(GoalObserver observer) {
		listObservers.remove(observer);
		
	}

	public void notifyGoalObservers() {
		for(GoalObserver observer : listObservers) {
			observer.update(this);
		}
		
	}

    public void onEnemyCollide(Enemy enemy) {
    	;
    }
	
	// treasure's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	playAudio("sounds/pickup.wav");
    	// simply lets the player move on top of the treasure
    	player.x().set(getX());
    	player.y().set(getY());
    	player.getInventory().addEntity(this);
    	displayInventory();
    	dungeon.delEntity(this);
    	//System.out.println(player.getInventory());
    	// we also notify the dungeon so it can update the
    	// treasure goal if needed
    	notifyGoalObservers();
    }

	public void onBoulderCollide(Boulder boulder) {
		;
	}

	public void handleGoalUpdate() {
		// if there is no more treasure in the dungeon,
		// then the goal is completed
    	if (!dungeon.doesEntityExist(this)) {
    		treasureGoal.setStatus(true);
    		System.out.println("Treasure goal completed!");
    	}
	}
	
	@Override
	public void updateSprite() {
		;
	}
	
	@Override
	public void displayInventory() {

		// if the player hasn't picked up any treasure yet, then
		// we simply add it to the inventory screen
		if (dungeon.getPlayer().getInventory().getWealth(this) == 1) {
			this.x().set(5);
			this.y().set(0);
		} else {
			// TODO; display how much treasure they have
			this.x().set(999);
			this.y().set(999);
		}

	}
	

}
