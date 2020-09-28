package unsw.dungeon;

import javafx.scene.image.Image;

public class Door extends Entity {
	
	private DoorState closedState;
	private DoorState openState;
	
	private DoorState state;
	private int id;
	
	/**
	 * Defines door for player interaction.
	 * @param id  		id for specific door existence.
	 * @param x			Location of door.
	 * @param y			Location of door.
	 */
	public Door(int id, int x, int y) {
		super(x, y);
		this.id = id;
		
		closedState = new ClosedDoorState(this);
		openState = new OpenDoorState(this);
		state = closedState;
	}
	
	public int getID() {
		return id;
	}
	
	public DoorState getState() {
		return state;
	}
	
	public void setState(DoorState state) {
		this.state = state;
	}
	
	public DoorState getClosedState() {
		return closedState;
	}
	
	public DoorState getOpenState() {
		return openState;
	}

    public void onEnemyCollide(Enemy enemy) {
    	state.onEnemyCollide(enemy);
    }
    
	// door's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	state.onPlayerCollide(player);
    }
    
    public void onBoulderCollide(Boulder boulder) {
    	state.onBoulderCollide(boulder);
	}

	@Override
	public void updateSprite() {
		Image image = new Image("open_door.png");
		notifySpriteObservers(image);
	}

}
