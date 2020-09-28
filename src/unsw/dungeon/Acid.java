package unsw.dungeon;

public class Acid extends Entity {
	
	Dungeon dungeon;
	
	/**
	 * Defines pool of acid for player interaction.
	 * @param dungeon 	Dungeon for acid existence.
	 * @param x			Location of acid.
	 * @param y			Location of acid.
	 */
	public Acid(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
    public void onPlayerCollide(Player player) {
    	
    	// Lets the player move on top of the acid
    	player.x().set(getX());
    	player.y().set(getY());
    	
    	Ladder testLadder = new Ladder(999, 999);
    	if (dungeon.findEntity(getX(), getY(), testLadder) == null) {
    		// Player will die when walking on acid
        	player.hide();
        	player.setAlive(false);
        	player.getDungeon().delEntity(player);
    	}
    }
    
    
    public void onEnemyCollide(Enemy enemy) {
    	
    	enemy.x().set(getX());
    	enemy.y().set(getY());
    	
    	Ladder testLadder = new Ladder(999, 999);
    	if (dungeon.findEntity(getX(), getY(), testLadder) == null) {
    		enemy.hide();
    	}
    }

	public void onBoulderCollide(Boulder boulder) {
    	
    	Ladder testLadder = new Ladder(999, 999);
    	if (dungeon.findEntity(getX(), getY(), testLadder) == null) {
    		boulder.x().set(getX());
        	boulder.y().set(getY());   		
    		boulder.hide();
    	}
	}

	@Override
	public void updateSprite() {
		;
	}

}
