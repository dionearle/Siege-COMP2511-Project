package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Enemy extends Entity implements GoalSubject {
	
	private Goal enemyGoal;
	private Dungeon dungeon;
	private ArrayList<GoalObserver> listObservers = new ArrayList<GoalObserver>();
	
	/**
	 *
	 * Defines bomb for player interaction.
	 *
	 * @param dungeon 		Dungeon bomb exists in.
	 * @param enemyGoal		Specific enemy related goal for gameplay.
	 * @param x				Location.
	 * @param y				Location.
	 */
	public Enemy(Dungeon dungeon, Goal enemyGoal, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.enemyGoal = enemyGoal;
	}
	
	public void registerGoalObserver(GoalObserver observer) {
		if(!listObservers.contains(observer)) { 
			listObservers.add(observer); 
		}
	}

	public void removeGoalObserver(GoalObserver observer) {
		listObservers.remove(observer);
		
	}

	public void notifyGoalObservers() {
		for(GoalObserver observer : listObservers) {
			observer.update(this);
		}
		
	}
	
	public Goal getGoal() {
		return enemyGoal;
	}
	
    public void handleEnemyMovement(int x, int y, Enemy enemy, Player player) {
	    
    	// if there is an entity in this square, then we call
	    // that entity's onPlayerCollide method
	    if (dungeon.isEntityHere(x, y) && dungeon.findEntity(x, y, player) == null) {
	    	
	    // if there are no entities in this position, we just
	    // move into it
	    } else {
	        enemy.x().set(x);
	        enemy.y().set(y);
	    }
    }
	
	public void chasePlayer(Player player) {
		
		if (player.getState() instanceof NeutralPlayerState) {
			if (player.getX() == this.getX() && player.getY() != this.getY()) { // N & S
				if (player.getY() > this.getY()) { // Player is S
					handleEnemyMovement(this.getX(), this.getY() + 1, this, player);
				} else { // Player is N
					handleEnemyMovement(this.getX(), this.getY() - 1, this, player);
				}
			} else if (player.getY() == this.getY() && player.getX() != this.getX() ) { // E & W
				if (player.getX() > this.getX()) { // Player is E
					handleEnemyMovement(this.getX() + 1, this.getY(), this, player);
				} else { // Player is W
					handleEnemyMovement(this.getX() - 1, this.getY(), this, player);
				}
				
			} else if (player.getY() != this.getY() && player.getX() != this.getX() ) { // NE, NW, SE, SW
				if (player.getY() > this.getY() && player.getX() > this.getX()) { // Player is S
					handleEnemyMovement(this.getX() + 1, this.getY() + 1, this, player);
				} else if (player.getY() > this.getY() && player.getX() < this.getX()) { // Player is N
					handleEnemyMovement(this.getX() - 1, this.getY() + 1, this, player);
				} else if (player.getY() < this.getY() && player.getX() > this.getX()) { // Player is E
					handleEnemyMovement(this.getX() + 1, this.getY() - 1, this, player);
				} else if (player.getY() < this.getY() && player.getX() < this.getX()) { // Player is W
					handleEnemyMovement(this.getX() - 1, this.getY() - 1, this, player);
				}
				
			} else { // On top of player
				player.onEnemyCollide(this);
			}
		} else { // player is potioned so we move away from them
			if (player.getX() == this.getX() && player.getY() != this.getY()) { // N & S
				if (player.getY() > this.getY()) { // Player is S
					handleEnemyMovement(this.getX(), this.getY() - 1, this, player);
				} else { // Player is N
					handleEnemyMovement(this.getX(), this.getY() + 1, this, player);
				}
			} else if (player.getY() == this.getY() && player.getX() != this.getX() ) { // E & W
				if (player.getX() > this.getX()) { // Player is E
					handleEnemyMovement(this.getX() - 1, this.getY(), this, player);
				} else { // Player is W
					handleEnemyMovement(this.getX() + 1, this.getY(), this, player);
				}
			} else if (player.getY() != this.getY() && player.getX() != this.getX() ) { // NE, NW, SE, SW
				if (player.getY() > this.getY() && player.getX() > this.getX()) { // Player is S
					handleEnemyMovement(this.getX() - 1, this.getY() - 1, this, player);
				} else if (player.getY() > this.getY() && player.getX() < this.getX()) { // Player is N
					handleEnemyMovement(this.getX() + 1, this.getY() - 1, this, player);
				} else if (player.getY() < this.getY() && player.getX() > this.getX()) { // Player is E
					handleEnemyMovement(this.getX() - 1, this.getY() + 1, this, player);
				} else if (player.getY() < this.getY() && player.getX() < this.getX()) { // Player is W
					handleEnemyMovement(this.getX() + 1, this.getY() + 1, this, player);
				}
				
			} else { // On top of player
				player.onEnemyCollide(this);
			}
		}
	}
	
	
	// enemy's implementation of onPlayerCollide(), which
    // determines what the entity does when the player collides
    // with this entity
    public void onPlayerCollide(Player player) {
    	player.onEnemyCollide(this);
    } 
    

    public void onEnemyCollide(Enemy enemy) {
    	// doesn't let an enemy collide with another enemy
    	;
    }
    
    protected void onBoulderCollide(Boulder boulder) {
		;
	}

	@Override
	public void handleGoalUpdate() {
		// if there are no more enemies in the dungeon,
		// then the goal is completed
		if (!dungeon.doesEntityExist(this)) {
    		enemyGoal.setStatus(true);
    		System.out.println("Enemy goal completed!");
    	}
	}
	
	@Override
	public void updateSprite() {
		
		Image image;
		
    	if (dungeon.getPlayer().getState() instanceof NeutralPlayerState) {
    		image = new Image("_orc_red.png");
    	} else {
    		image = new Image("_orc_green.png");
    	}
    	
    	notifySpriteObservers(image);
	}

}
