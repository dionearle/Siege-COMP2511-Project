package unsw.dungeon;

public class OpenDoorState implements DoorState {
	
	private Door door;
	
	public OpenDoorState(Door door) {
		this.door = door;
	}

	public void onPlayerCollide(Player player) {
		
    	// simply lets the player move on top of the door since it is opened
        player.x().set(door.getX());
        player.y().set(door.getY());

	}

	public void onBoulderCollide(Boulder boulder) {
		
		// since the door is open we can push the boulder through the door
		boulder.x().set(door.getX());
    	boulder.y().set(door.getY());

	}
	
	public void onEnemyCollide(Enemy enemy) {
		
		// since the door is open the enemy should be able to walk through the door
		enemy.x().set(door.getX());
    	enemy.y().set(door.getY());

	}
}
