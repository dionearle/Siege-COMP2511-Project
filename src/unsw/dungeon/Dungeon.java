/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements GoalObserver {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goal goals;

    /**
     * Dungeon object, which contains all gameplay elements.
     * @param width		Window dimensions for gameplay.
     * @param height	Window dimensions for gameplay.
     * @param goals		Goals for gameplay.
     */
    public Dungeon(int width, int height, Goal goals) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = goals;
    }
    
    @Override
	public void update(GoalSubject subject) {
    	
    	// here we update the goal relevant to the subject entity
    	subject.handleGoalUpdate();
    	
    	// Simply checks whether the all the goals for a dungeon are completed.
    	if (this.getGoals().isCompleted()) {
    		this.getGoals().setStatus(true);
    	}
	}
    
    public Goal getGoals() {
		return goals;
	}
    
    public String nameGoal() {
    	return goals.nameGoal();
    }
    
    public Goal getGoalFromName(String name) {
    	return goals.getGoalFromName(name);
    }

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void delEntity(Entity entity) {
        entities.remove(entity);
    }
    
    public List<Entity> getEntities() {
    	return entities;
    }
        
    public boolean isEntityHere(int x, int y) {
    	for (int i = 0; i < entities.size(); i++) {
    		if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
    			return true;
    		}
		}
    	return false;
    }
    
    public Entity getEntityHere(int x, int y) {
    	for (int i = 0; i < entities.size(); i++) {
    		if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
    			return entities.get(i);
    		}
    	}
    	return null;
    	
    }
    
    public Entity findEntity(int x, int y, Entity ent) {
    	
    	for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getX() == x && entities.get(i).getY() == y
			&& entities.get(i).getClass() == ent.getClass()) {
				return entities.get(i);
			}			
    	}
		return null;
	}
    
    public List<Entity> getAllEntitiesOfType(Entity ent) {
    	
    	List<Entity> result = new ArrayList<>();
    			
    	for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getClass() == ent.getClass()) {
				result.add(entities.get(i));
			}			
    	}
    	
		return result;
	}
    
    public List<Entity> getAllEntitiesHere(int x, int y) {
    	
    	List<Entity> result = new ArrayList<>();
    			
    	for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
				result.add(entities.get(i));
			}			
    	}
    	
		return result;
	}
    
    public boolean doesEntityExist(Entity ent) {
    	
    	for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getClass() == ent.getClass()) {
				return true;
			}			
    	}
		return false;
	}
    
}
