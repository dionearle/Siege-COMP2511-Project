package unsw.dungeon;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */

public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    private boolean alive;
    
    private PlayerState neutralState;
    private PlayerState potionedState;
    
    private PlayerState state;
    
    private int potionDuration;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, Inventory inventory) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = inventory;
        this.alive = true;
        
        this.potionDuration = 0;

        neutralState = new NeutralPlayerState(this);
        potionedState = new PotionedPlayerState(this);   
        state = neutralState;
    }
    
    public void setState(PlayerState state) {
    	this.state = state;
    }
    
    public PlayerState getState() {
    	return state;
    }
    
    public PlayerState getNeutralState( ) {
    	return neutralState;
    }
    
    public PlayerState getPotionedState( ) {
    	return potionedState;
    }
    
    public boolean isAlive() {
    	return alive;
    }
    
    public void setAlive(boolean status) {
    	alive = status;
    }
    
    public Dungeon getDungeon() {
    	return dungeon;
    }
    
    public Inventory getInventory() {
    	return inventory;
    }
    
    public void moveUp() {
    	// if this is a valid place in the dungeon to move, we
    	// call the handleMovement function to determine what to
    	// do
        if (getY() > 0) {
        	playAudio("sounds/walk.wav");
        	handleMovement(getX(), getY() - 1);
        	
        }
    }

    public void moveDown() {
    	// if this is a valid place in the dungeon to move, we
    	// call the handleMovement function to determine what to
    	// do
        if (getY() < dungeon.getHeight() - 1) {
        	playAudio("sounds/walk.wav");
        	handleMovement(getX(), getY() + 1);
        }
    }

    public void moveLeft() {
    	// if this is a valid place in the dungeon to move, we
    	// call the handleMovement function to determine what to
    	// do
        if (getX() > 0) {
        	playAudio("sounds/walk.wav");
        	handleMovement(getX() - 1, getY());
        }
    }

    public void moveRight() {
    	// if this is a valid place in the dungeon to move, we
    	// call the handleMovement function to determine what to
    	// do
        if (getX() < dungeon.getWidth() - 1) {
        	playAudio("sounds/walk.wav");
        	handleMovement(getX() + 1, getY());
        }
    }
    
    // given x and y co-ordinates the player wants to move into,
    // we determine what should happen based on what is in this
    // position
    public void handleMovement(int x, int y) {
    	// if there is an entity in this square, then we call
    	// that entity's onPlayerCollide method
    	if (dungeon.isEntityHere(x, y)) {
        	dungeon.getEntityHere(x, y).onPlayerCollide(this);
        // if there are no entities in this position, we just
        // move into it
        } else {
        	x().set(x);
        	y().set(y);
        }
    	
    	
    	// TODO: should be better way then creating new enemy
    	// object
    	Goal testGoal = new LeafGoal("test");
    	Enemy enemy = new Enemy(dungeon, testGoal, 999, 999);
    	for (Entity e : dungeon.getAllEntitiesOfType(enemy)) {
    		enemy = (Enemy) e;
    		enemy.chasePlayer(this);
    		if (enemy.getX() == this.getX() && enemy.getY() == this.getY()) {
    			this.onEnemyCollide(enemy);
    		}
    	}
    }
    
    public void onPlayerCollide(Player player) {
    	;
    }
    
    public void onBoulderCollide(Boulder boulder) {
    	;
    }

    public void onEnemyCollide(Enemy enemy) {
    	state.onEnemyCollide(enemy);
    }
    
    public void updatePotion(Player player, int length) {
    	player.potionDuration += length;
    }
    
    public void activatePotion(Invincibility inv) {
		
		// if a non-potioned player picks up a potion, then
		// we setup a new timer and activate the potion
    	System.out.println("Duration is " + potionDuration);
    	
    	//new TimerEvent(potionDuration, getDungeon(), inv);
    	Timeline potionTimeline = new Timeline(new KeyFrame(Duration.seconds(potionDuration), ev -> {
    		potionTimer(inv);
        }));
    	potionTimeline.play();	
    	System.out.println("Start potion!");
    	
    	// since we have activated the potion, we want to change
    	// the state of the player to reflect this
    	setState(getPotionedState());
    	updateSprite();
    	
    	Enemy enemy = new Enemy(null, null, 0, 0);
    	List<Entity> list = getDungeon().getAllEntitiesOfType(enemy);
    	for (Entity e : list) {
    		e.updateSprite();
    	}
	}
    
    public void potionTimer(Invincibility inv) {
    	
    	getInventory().delEntity(inv);
  	    
    	if (getInventory().findEntity(inv) == null) {
    		System.out.println ("End potion!");
    		setState(getNeutralState());   		
    	}
    	// if not in inventory, cancel.
  	    // we also want to set the state of the player to
  	    // reflect no longer being potioned
    	inv.hide();
    	updateSprite();
    	
    	Enemy enemy = new Enemy(null, null, 0, 0);
    	List<Entity> list = getDungeon().getAllEntitiesOfType(enemy);
    	for (Entity e : list) {
    		e.updateSprite();
    	}
    }
    
    public void useBomb(Bomb bomb) {
    	
    	bomb.setX(getX());
    	bomb.setY(getY());
    	getInventory().delEntity(bomb);
    	bomb.setState(bomb.getLitState());
    	
    	//new TimerEvent(4, dungeon, bomb);
    	Timeline bombExplosionTimeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
    		bombTimer(bomb);
        }));
    	bombExplosionTimeline.play();
    	System.out.println("Lit bomb!");
    	
    	updateBombStage(bomb, 1);
    	updateBombStage(bomb, 2);
    	updateBombStage(bomb, 3);

    	bomb.updateSprite();
    }
    
    public void bombTimer(Bomb bomb) {
    	bomb.playAudio("sounds/explode.wav");
  	    System.out.println("Bomb exploded!");
  	    
  	    int x = bomb.getX();
  	    int y = bomb.getY();
  	    
  	    // any boulders, enemies or player in the squares
  	    // surrounding the exploded bomb should be destroyed
  	    for (int i = (x - 1); i <= (x + 1); i++) {
  	    	for (int j = (y - 1); j <= (y + 1); j++) {

  	    		List<Entity> entities = dungeon.getAllEntitiesHere(i, j);
  	    			
  	    		// for all entities in this square, we want to remove it
  	    		for (int k = 0; k < entities.size(); k++) {
  	    			Entity entity = entities.get(k);
  	    			
  	    			if (entity instanceof Boulder) {
  	    				
  	    				dungeon.delEntity(entity);
	  	    			entity.hide();
	  	    			
  	    			} else if (entity instanceof Switch) { 
  	    			
  	    				Switch currentSwitch = (Switch) entity;

  	    				// if the switch had a boulder on it (meaning it is triggered),
  	    				// then we want to set it as neutral
  	    				if (currentSwitch.getState() == currentSwitch.getTriggeredState()) {
  	    					
  	    					currentSwitch.setState(currentSwitch.getNeutralState());
  	    					
  	    					// in doing so we also want to notify the dungeon
  	    					// so it can update the switch goal if required
  	    					currentSwitch.notifyGoalObservers();
  	    				}
  	    			} else if (entity instanceof Enemy) {
  	    				entity.playAudio("sounds/enemy_dead.wav");
  	    				dungeon.delEntity(entity);
	  	    			entity.hide();
	  	    			
	  	    			// we also want to notify the dungeon so it can update
	  	    			// the enemy goal if required
	  	    			((Enemy) entity).notifyGoalObservers();
	  	    				
  	    			} else if (entity instanceof Player) {
  	    				
  	    				dungeon.delEntity(entity);
	  	    			entity.hide();
	  	    			((Player) entity).setAlive(false);
	  	    			notifySpriteObservers(null);
  	    			}
  	    		}
  	    	}
  	    }
  	    dungeon.delEntity(bomb);
  	    bomb.hide();
    }
    
    public void updateBombStage(Bomb bomb, int seconds) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), ev -> {
        	bomb.updateSprite();
        }));
        timeline.play();
    }
    
    public void useSword(Sword sword) {
    	
    	// first we want to subtract 1 hit from the sword
		sword.setHealth(sword.getHealth() - 1);
		System.out.println("Sword used: " + sword.getHealth() + " hits left.");
		
		// if there is an enemy in an adjacent square to the player,
		// then we need to find it and destroy it
		int x = getX();
  	    int y = getY();
		
		for (int i = (x - 1); i <= (x + 1); i++) {
  	    	for (int j = (y - 1); j <= (y + 1); j++) {

  	    		List<Entity> entities = dungeon.getAllEntitiesHere(i, j);
  	    			
  	    		// for all entities in this square, we want to remove it
  	    		for (int k = 0; k < entities.size(); k++) {
  	    			Entity entity = entities.get(k);
  	    			
  	    			// if there is an enemy nearby,
  	    			// then using the sword should destroy it
  	    			if (entity instanceof Enemy) {
  	    				playAudio("sounds/enemy_dead.wav");
  	    				dungeon.delEntity(entity);
  	    				entity.hide();
	  	    			
	  	    			// We also want to notify the dungeon so it can
	  	    			// update the enemy goal if required
	  	    			((Enemy) entity).notifyGoalObservers();	    			
  	    			}
  	    		}
  	    	}
  	    }
		
		// if the sword has no more hits remaining, then we
		// can no longer use it
    	if (sword.getHealth() == 0) {
    		System.out.println("Sword destroyed!");
    		inventory.delEntity(sword);
    		dungeon.delEntity(sword);
    		sword.hide();
    		updateSprite();
    	}  	
    }  
    
    public void useLadder(Ladder ladder) {
    	
    	// if there is an enemy in an adjacent square to the player,
    	// then we need to find it and destroy it
    	int x = getX();
    	int y = getY();
    			
    	for (int i = (x - 1); i <= (x + 1); i++) {
    		for (int j = (y - 1); j <= (y + 1); j++) {

    			List<Entity> entities = dungeon.getAllEntitiesHere(i, j);
    	  	    			
    	  	   	// for all entities in this square, we want to remove it
    	  	    for (int k = 0; k < entities.size(); k++) {
    	  	    	Entity entity = entities.get(k);
    	  	    			
    	  	    	// if there is an enemy nearby,
    	  	    	// then using the sword should destroy it
    	  	    	if (entity instanceof Acid) {
    	  	    		ladder.x().set(entity.getX());
    	  	    		ladder.y().set(entity.getY());  
    	  	    		inventory.delEntity(ladder);
    	  	    		return;
    	  	    	}
    	  	    }
    	  	}
    	}
    }
    
    @Override
	public void updateSprite() {
    	
    	Image image;
    	
    	if (getState() instanceof NeutralPlayerState) {
    		Sword testSword = new Sword(dungeon, 0, 0);
    		if (inventory.findEntity(testSword) == null) {
        		image = new Image("player_nosword.png");
        	} else {
        		image = new Image("player.png");
        	}
    	} else {
    		Sword testSword = new Sword(dungeon, 0, 0);
    		if (inventory.findEntity(testSword) == null) {
        		image = new Image("player_nosword_potioned.png");
        	} else {
        		image = new Image("player_sword_potioned.png");
        	}
    	}
    	
    	notifySpriteObservers(image);
    	
	}
}


