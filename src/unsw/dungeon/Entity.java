package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements SpriteSubject {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    
    private ImageView image;
    
    ArrayList<SpriteObserver> listObservers = new ArrayList<SpriteObserver>();

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.image = null;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setX(int new_x) {
        this.x().set(new_x);
    }

    public void setY(int new_y) {
        this.y().set(new_y);
    }
    
    public void hide() {
    	image.setVisible(false);
    }
    
    public void displayInventory() {
    	setX(999);
    	setY(999);
    }
    
    public void setImage(ImageView image) {
    	this.image = image;
    }
    
    public ImageView getImage() {
    	return image;
    }

	@Override
	public String toString() {
		return "Entity [x=" + x + ", y=" + y + "]";
	}
	
	public void playAudio(String strfile) {
		AudioClip walk = new AudioClip(this.getClass().getResource(strfile).toString());
		walk.setVolume(0.5);
		walk.play();
	}
	
	public void playMusic(String strfile, int vol) {
		AudioClip walk = new AudioClip(this.getClass().getResource(strfile).toString());
		walk.setVolume(vol);
		walk.setCycleCount(50);
		walk.play();
		
	}

	// Abstract method to determine what should happen when a player
	// collides with an entity type (implemented by each entity)
	public abstract void onPlayerCollide(Player player);
	
	public abstract void onEnemyCollide(Enemy enemy);

	protected abstract void onBoulderCollide(Boulder boulder);
    
	@Override
	public void registerSpriteObserver(SpriteObserver o) {
		if (! listObservers.contains(o)) {
			listObservers.add(o);
		}
	}

	@Override
	public void removeSpriteObserver(SpriteObserver o) {
		listObservers.remove(o);
	}

	@Override
	public void notifySpriteObservers(Image image) {
		for(SpriteObserver obs : listObservers) {
			obs.update(this, image);
		}
	}
	
	public abstract void updateSprite();
}
