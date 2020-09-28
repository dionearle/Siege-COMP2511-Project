package unsw.dungeon;

public interface SwitchState {

	public void onPlayerCollide(Player player);
	public void onBoulderCollide(Boulder boulder);
	public void onEnemyCollide(Enemy enemy);
	
}
