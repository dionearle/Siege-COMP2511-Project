package unsw.dungeon;

public class Key extends Entity {

	private int id;
	
	/**
	 * Key for opening doors in dungeon gameplay.
	 * @param id		Unique key for unique door.
	 * @param x			Location.
	 * @param y			Location.
	 */
	public Key(int id, int x, int y) {
		super(x, y);
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	// key's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	
    	// simply lets the player move on top of the key
    	player.x().set(getX());
    	player.y().set(getY());

    	// One key at a time
    	if (player.getInventory().findEntity(this) == null) {
    		playAudio("sounds/pickup.wav");
    		player.getInventory().addEntity(this);
        	displayInventory();
    	}
    }
    

    public void onEnemyCollide(Enemy enemy) {
    	;
    }

	public void onBoulderCollide(Boulder boulder) {
		;
	}
	
	@Override
	public void updateSprite() {
		;
	}
	
	@Override
	public void displayInventory() {
		this.x().set(7);
		this.y().set(0);
    }

}
