package unsw.dungeon;

import javafx.scene.image.Image;

public interface SpriteSubject {
	
	public void registerSpriteObserver(SpriteObserver o);
	public void removeSpriteObserver(SpriteObserver o);
	public void notifySpriteObservers(Image image); 

}
