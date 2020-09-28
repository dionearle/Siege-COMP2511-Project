package unsw.dungeon;

public class NeutralSwitchState implements SwitchState {
	
	private Switch floorSwitch;

	public NeutralSwitchState(Switch floorSwitch) {
		this.floorSwitch = floorSwitch;
	}

	public void onPlayerCollide(Player player) {
		
		// simply lets the player move on top of the switch
    	player.x().set(floorSwitch.getX());
    	player.y().set(floorSwitch.getY());

	}

	public void onBoulderCollide(Boulder boulder) {
		
		// lets the player push a boulder onto the switch
		boulder.x().set(floorSwitch.getX());
		boulder.y().set(floorSwitch.getY());
		
		boulder.playAudio("sounds/door.wav");
		
		floorSwitch.setState(floorSwitch.getTriggeredState());
		
		
		// we also want to notify the dungeon so it can update
		// the goal for floor switches if needed
		floorSwitch.notifyGoalObservers();
	}
	
	public void onEnemyCollide(Enemy enemy) {
		// simply lets the enemy move on top of the switch
    	enemy.x().set(floorSwitch.getX());
    	enemy.y().set(floorSwitch.getY());
    }

}
