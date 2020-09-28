package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Switch extends Entity implements GoalSubject {
	
	private Goal switchGoal;
	private Dungeon dungeon;
	private ArrayList<GoalObserver> listObservers = new ArrayList<GoalObserver>();

	private SwitchState neutralState;
	private SwitchState triggeredState;
	
	private SwitchState state;

	/**
	 * Switch for gameplay.
	 * @param dungeon		Dungeon located in.
	 * @param switchGoal	Associated goals
	 * @param x				Location
	 * @param y				Location
	 */
	public Switch(Dungeon dungeon, Goal switchGoal, int x, int y) {
		super(x, y);
		neutralState = new NeutralSwitchState(this);
		triggeredState = new TriggeredSwitchState(this);
		this.switchGoal = switchGoal;
		this.dungeon = dungeon;
		
		state = neutralState;
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
		return switchGoal;
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}	

    public void onEnemyCollide(Enemy enemy) {
    	state.onEnemyCollide(enemy);
    }
    
    // switch's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	state.onPlayerCollide(player);
    }

	protected void onBoulderCollide(Boulder boulder) {	
		state.onBoulderCollide(boulder);
	}
	
	public void setState(SwitchState state) {
		this.state = state;
	}
	
	public SwitchState getState() {
		return state;
	}
	
	public SwitchState getNeutralState( ) {
		return neutralState;
	}
	
	public SwitchState getTriggeredState( ) {
		return triggeredState;
	}

	@Override
	public void handleGoalUpdate() {
		
		if (state instanceof TriggeredSwitchState) {
			
			// if there is a switch in the dungeon that isn't triggered,
    		// then the switch goal isn't complete
    		List<Entity> switches = dungeon.getAllEntitiesOfType(this);
    		for (int i = 0; i < switches.size(); i++) {
    			if (!(((Switch) switches.get(i)).getState() instanceof TriggeredSwitchState)) {
    				return;
    			}
    		}
    		
    		// if we make it here, then all the switches are triggered,
    		// so we have completed the switch goal
    		switchGoal.setStatus(true);
    		System.out.println("Switch goal completed!");
		} else {
			
			// if the switch goal was previously completed, then
			// we want to set it as false since a boulder has been
			// moved off a switch
			if (switchGoal.isCompleted()) {
				switchGoal.setStatus(false);
				System.out.println("Switch goal no longer completed!");
			}
		}
		
	}
	
	@Override
	public void updateSprite() {
		;
	}

}
