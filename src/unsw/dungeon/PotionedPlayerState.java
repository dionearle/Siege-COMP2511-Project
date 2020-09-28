package unsw.dungeon;

public class PotionedPlayerState implements PlayerState {

	Player player;
	
	public PotionedPlayerState(Player player) {
		this.player = player;
	}
	
	public void onEnemyCollide(Enemy enemy) {
		
		// if the player is potioned, the enemy will die
		enemy.hide();
		enemy.playAudio("sounds/enemy_dead.wav");
		player.getDungeon().delEntity(enemy);
		
		
		// we also notify the dungeon so that it can
		// update the enemy goal if needed
		enemy.notifyGoalObservers();
	}
}
