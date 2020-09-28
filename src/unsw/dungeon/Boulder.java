package unsw.dungeon;

public class Boulder extends Entity {

	private Dungeon dungeon;
	
	/**
	 * Defines boulder for player interaction.
	 * @param dungeon 	Dungeon for boulder existence.
	 * @param x			Location of boulder.
	 * @param y			Location of boulder.
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	// boulder's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	
    	// logic for pushing the boulder
    	if (player.getY() < this.getY()) {
    		handlePush(getX(), getY() + 1);
    		player.y().set(this.getY() - 1);
    	} else if (player.getY() > this.getY()) {
    		handlePush(getX(), getY() - 1);
    		player.y().set(this.getY() + 1);
    	} else if (player.getX() > this.getX() ) {
    		handlePush(getX() - 1, getY());
    		player.x().set(this.getX() + 1);
    	} else if (player.getX() < this.getX()) {
    		handlePush(getX() + 1, getY());
    		player.x().set(this.getX() - 1);
    	}
    }
    
    /**
     * Defines push behaviours for a boulder by player.
     * @param x		Current location of boulder.
     * @param y		Current location of boulder.
     */
    public void handlePush(int x, int y) {
    	// if there is an entity in this square, then we call
    	// that entity's onBoulderCollide method
    	if (dungeon.isEntityHere(x, y)) {
        	dungeon.getEntityHere(x, y).onBoulderCollide(this);
        // if there are no entities in this position, we just
        // move into it
        } else {
        	this.x().set(x);
        	this.y().set(y);
        }
    }
    

    public void onEnemyCollide(Enemy enemy) {
    	// doesn't let the enemy collide with the wall
    	;
    }
    
	public void onBoulderCollide(Boulder boulder) {
		// if there is another boulder behind it, we shouldn't do
	    // anything as the player is only strong enough to push one
	    // boulder at a time
		;
	}

	@Override
	public void updateSprite() {
		;
	}

}
