package unsw.dungeon;

import javafx.scene.image.Image;

public class Bomb extends Entity {

	private BombState unlitState;
	private BombState litState;
	
	private BombState state;
	
	private int bombStage;
	
	/**
	 * Defines bomb for player interaction.
	 * @param x			Location of acid.
	 * @param y			Location of acid.
	 */
	public Bomb( int x, int y) {
		super(x, y);
		
		unlitState = new UnlitBombState(this);
		litState = new LitBombState(this);
		state = unlitState;
		bombStage = 1;
	}
	
	public BombState getState() {
		return state;
	}
	
	public void setState(BombState state) {
		this.state = state;
	}
	
	public BombState getUnlitState() {
		return unlitState;
	}
	
	public BombState getLitState() {
		return litState;
	}
	

    public void onEnemyCollide(Enemy enemy) {
    	;
    }
	
	// bomb's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	state.onPlayerCollide(player);
    }
    
    public void onBoulderCollide(Boulder boulder) {
    	;
	}

	@Override
	public void updateSprite() {
		
		Image image;
			
		if (bombStage == 1) {
			image = new Image("bomb_lit_1.png");
		} else if (bombStage == 2) {
			image = new Image("bomb_lit_2.png");
		} else if (bombStage == 3) {
			image = new Image("bomb_lit_3.png");
		} else if (bombStage == 4) {
			image = new Image("bomb_lit_4.png");
		} else {
			image = null;
		}
			
		bombStage++;
		
		notifySpriteObservers(image);
	}
	
	@Override
	public void displayInventory() {
		
		this.x().set(9);
		this.y().set(0);
    }

}
