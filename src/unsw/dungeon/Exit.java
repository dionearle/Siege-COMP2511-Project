package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity implements GoalSubject {

	private Dungeon dungeon;
	
	private Goal exitGoal;
	private ArrayList<GoalObserver> listObservers = new ArrayList<GoalObserver>();
	
	/**
	 * Defines exit for player interaction.
	 *
	 * @param dungeon 		Dungeon exit bomb exists in.
	 * @param exitGoal		Specific exit related goal for gameplay.
	 * @param x				Location.
	 * @param y				Location.
	**/
	
	public Exit(Dungeon dungeon, Goal exitGoal, int x, int y) {
		super(x, y);
		this.exitGoal = exitGoal;
		this.dungeon = dungeon;
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
	
	public Goal getGoal() {
		return exitGoal;
	}
	
	// exit's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	
    	// simply lets the player move on top of the exit
    	player.x().set(getX());
    	player.y().set(getY());
    	
    	// we then notify the dungeon that a goal has been updated
    	notifyGoalObservers();   	
    }
    

    public void onEnemyCollide(Enemy enemy) {
    	// doesn't let the enemy collide with the wall
    	;
    }
    
    protected void onBoulderCollide(Boulder boulder) {
		;
	}

	@Override
	public void handleGoalUpdate() {
		
		exitGoal.setStatus(true);
		
		// we check if the dungeon is completed after reaching the exit.
		// if it isn't then we set the exit goal as false as it needs to be the last
		// achieved goal
		if (!dungeon.getGoals().isCompleted()) {
			exitGoal.setStatus(false);
			System.out.println("Exit goal must be achieved last!");
		} else {
			// the exit goal was achieved
			System.out.println("Exit goal completed!");
		}	
	}
	
	@Override
	public void updateSprite() {
		;
	}

}
