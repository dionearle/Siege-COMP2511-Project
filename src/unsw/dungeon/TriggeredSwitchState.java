package unsw.dungeon;

public class TriggeredSwitchState implements SwitchState {
	
	private Switch floorSwitch;

	public TriggeredSwitchState(Switch floorSwitch) {
		this.floorSwitch = floorSwitch;
	}

	public void onPlayerCollide(Player player) {	
		
		// TODO: should be better method of doing this
		
		// since the switch is activated, this means there is a boulder
		// on it. To figure out whether we can push the boulder, we first
		// need to get its properties
		int x = floorSwitch.getX();
		int y = floorSwitch.getY();
		
		Dungeon dungeon = player.getDungeon();
		Boulder tmpBoulder = new Boulder(player.getDungeon(), 999,999);
		
		Boulder boulder = (Boulder) dungeon.findEntity(x, y, tmpBoulder);
		
		// once we have the boulder object that is on top of the switch,
		// we can call onPlayerCollide to determine the behaviour
		boulder.onPlayerCollide(player);
		
		// if we were able to move the boulder off the switch,
		// then the switch is no longer triggered, so we set its
		// state as neutral
		if (dungeon.findEntity(x, y, tmpBoulder) == null) {
			
			floorSwitch.setState(floorSwitch.getNeutralState());

			// we finally notify the dungeon that one of the
			// switches is no longer triggered
			floorSwitch.notifyGoalObservers();
		}
	}

	public void onBoulderCollide(Boulder boulder) {
		
		// we don't want to be able to push a boulder onto a
		// switch that is already triggered, as this means
		// there is a boulder on it. hence we do nothing
		;
	}
	
	public void onEnemyCollide(Enemy enemy) {
		// enemy should not be able to walk onto a triggered switch
    	;
    }

}
