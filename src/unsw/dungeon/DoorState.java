package unsw.dungeon;

public interface DoorState {
	
	public void onPlayerCollide(Player player);
	public void onBoulderCollide(Boulder boulder);
	public void onEnemyCollide(Enemy enemy);	
}
