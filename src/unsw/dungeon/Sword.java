package unsw.dungeon;

public class Sword extends Entity {

	private int health;
	
	/**
	 * Sword for gameplay.
	 * @param dungeon		 Dungeon exists in,
	 * @param x				Location
	 * @param y				Location
	 */
	public Sword(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.health = 5;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int value) {
		health = value;
	}
	
	public void onEnemyCollide(Enemy enemy) {
		;
    }
	
	// sword's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	
    	// simply lets the player move on top of the sword
    	player.x().set(getX());
    	player.y().set(getY());
    	
    	if (player.getInventory().findEntity(this) == null) {
    		playAudio("sounds/pickup.wav");
    		System.out.println("Sword picked up! Cannot pickup another.");
    		player.getInventory().addEntity(this);
        	displayInventory();
        	player.updateSprite();
    	}
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
		this.x().set(11);
		this.y().set(0);
    }

}
