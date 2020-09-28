package unsw.dungeon;

public class ClosedDoorState implements DoorState {
	
	private Door door;
	
	/**
	 * State of closed door.
	 * @param door 		Door which state concerns.
	 */
	public ClosedDoorState(Door door) {
		this.door = door;
	}

	public void onPlayerCollide(Player player) {
		
		// TODO: need a better way of retrieving a key from the
    	// inventory such as findEntityByName("key").
    	Key key = new Key(999, 999, 999);
    	
    	if (player.getInventory().checkMatchingKeyID(door.getID())) {
    		player.playAudio("sounds/door.wav");
    		System.out.println("correct ID");
    		
    		// removing key from player inventory
    		Key k = (Key) player.getInventory().findEntity(key);
    		player.getInventory().delEntity(k);
    		k.hide();
    		
    		// lets the player move on top of the door
        	player.x().set(door.getX());
        	player.y().set(door.getY());
    		
    		// changing state for door to be opened
        	door.setState(door.getOpenState());
        	
        	// here we update the dungeonController so it can change
      	    // the sprite of the door to appear opened
        	//door.hide(door);
        	door.updateSprite();
        	
    	} else {
    		System.out.println("don't have the right key");
    	}

	}

	public void onBoulderCollide(Boulder boulder) {
		// we don't want a boulder to be pushed through a
		// closed door so we do nothing
		;
	}
	
	public void onEnemyCollide(Enemy enemy) {
		// we don't want a boulder to be pushed through a
		// closed door so we do nothing
		;
	}
}
