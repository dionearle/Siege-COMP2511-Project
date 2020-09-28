package unsw.dungeon;

public class NeutralPlayerState implements PlayerState {

	Player player;
	
	public NeutralPlayerState(Player player) {
		this.player = player;
	}
	
	public void onEnemyCollide(Enemy enemy) {
		// if neutral player collides with enemy they die
		enemy.playAudio("sounds/ded.wav");
    	player.hide();
    	player.setAlive(false);
    	player.getDungeon().delEntity(player);
	}
}
