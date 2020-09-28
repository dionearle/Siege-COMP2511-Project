package unsw.dungeon;

public class Ladder extends Entity {
	
	
	/**
	 * Ladder for bridging acid throughout gameplay.
	 * @param x		 Location
	 * @param y		 Location.
	 */
	public Ladder(int x, int y) {
		super(x, y);
	}
	
    public void onPlayerCollide(Player player) {
    	
    	// simply lets the player move on top of the ladder
    	player.x().set(getX());
    	player.y().set(getY());
    	
    	// One ladder at a time
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
		this.x().set(15);
		this.y().set(0);
    }

}
