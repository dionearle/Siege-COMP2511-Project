package unsw.dungeon;

public class UnlitBombState implements BombState {
	
	private Bomb bomb;

	public UnlitBombState(Bomb bomb) {
		this.bomb = bomb;
	}

	public void onPlayerCollide(Player player) {
		
		// lets the player move on top of the bomb and pick it up
		player.playAudio("sounds/pickup.wav");
    	player.x().set(bomb.getX());
    	player.y().set(bomb.getY());
    	player.getInventory().addEntity(bomb);	
    	bomb.displayInventory();
	}
}
