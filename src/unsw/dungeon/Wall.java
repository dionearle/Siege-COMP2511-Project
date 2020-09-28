package unsw.dungeon;

public class Wall extends Entity {

	/**
	 * Wall for interaction in gameplay.
	 * @param x		Location of wall instance.
	 * @param y		Location of wall instance.
	 */
    public Wall(int x, int y) {
        super(x, y);
    }
    
    // wall's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	// doesn't let the player collide with the wall
    	;
    }
    
    public void onEnemyCollide(Enemy enemy) {
    	// doesn't let the enemy collide with the wall
    	;
    }

	public void onBoulderCollide(Boulder boulder) {
		// since we don't want to push a boulder into the
		// grid containing a wall, we do nothing
		;	
	}
	
	@Override
	public void updateSprite() {
		;
	}

}
