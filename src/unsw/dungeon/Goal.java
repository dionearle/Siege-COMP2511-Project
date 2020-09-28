package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface Goal {
	
	public String nameGoal();
	public boolean isCompleted();
	public Goal getGoalFromName(String name);
	
	public String getName();
	public void setName(String name);
	public BooleanProperty getStatus();
	public void setStatus(boolean status);
}
