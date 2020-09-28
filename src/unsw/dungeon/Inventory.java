package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private List<Entity> inventory;
	
	public Inventory() {
		this.inventory = new ArrayList<>();
	}
	
	public void addEntity(Entity ent) {
		inventory.add(ent);
	}
	
	// To use, make a new entity ("Ent ent = new ent()"), then pass it in. Just to get the type.
	/**
	 * Delete entity from inventory.
	 * @param ent		Entity for deletion.
	 */
	public void delEntity(Entity ent) {
		//System.out.println(inventory);
		for (Entity e : inventory) {
			//System.out.println(ent.getClass());
			if (e.getClass() == ent.getClass()) {
				
				// TODO: although we cannot access dungeon from this class,
				// the entity should also be removed from the dungeon's
				// list of entities through its delEntity method
				inventory.remove(e);
				//System.out.println(inventory);
				break;
			}
		}
		
		
	}
	
	/**
	 * Find entity from inventory.
	 * @param ent		Entity type for search.
	 */
	public Entity findEntity(Entity ent) {
		for (Entity e : inventory) {
			//System.out.println(ent.getClass());
			if (e.getClass() == ent.getClass()) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Get wealth reading from inventory.
	 * @param ent		Type of treasure to count.
	 */
	public int getWealth(Treasure t) {
		int wealth = 0;
		for (Entity e : inventory) {
			//System.out.println(ent.getClass());
			if (e.getClass() == t.getClass()) {
				wealth++;
			}
		}
		return wealth;
	}

	@Override
	public String toString() {
		return "Inventory [inventory=" + inventory + "]";
	}

	public boolean checkMatchingKeyID(int id) {
		
		// TODO: there should be a better way of extracting the
		// key from our inventory, but at least this works
		// for now
		for (Entity e : inventory) {
			if (e instanceof Key) {
				if (((Key) e).getID() == id) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}
}
