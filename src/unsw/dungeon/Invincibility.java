package unsw.dungeon;

public class Invincibility extends Entity {
	
	public Invincibility(int x, int y) {
		super(x, y);
	}
	
	// invincibility's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
	
    public void onPlayerCollide(Player player) {
    	
    	playAudio("sounds/pickup.wav");
    	// lets the player move on top of the invincibility
    	player.x().set(getX());
    	player.y().set(getY());

    	player.getInventory().addEntity(this);
    	displayInventory();
    	
    	// we then call the activate potion method to setup
    	// the potion's effects on the player
    	player.playAudio("sounds/potion.wav");
    	player.updatePotion(player, 5);
    	player.activatePotion(this);
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
		this.x().set(13);
		this.y().set(0);
    }

}
