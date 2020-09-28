package unsw.dungeon;

public class LitBombState implements BombState {
	
	@SuppressWarnings("unused")
	private Bomb bomb;

	public LitBombState(Bomb bomb) {
		this.bomb = bomb;
	}

	public void onPlayerCollide(Player player) {
		// cannot pick up the bomb if it is lit, so we do nothing
		;
	}

}
