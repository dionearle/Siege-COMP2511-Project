package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class LeafGoal implements Goal {
	
	private String name;
	private BooleanProperty status;

	public LeafGoal(String name) {
		super();
		this.name = name;
		this.status = new SimpleBooleanProperty();
	}

	public String nameGoal() {
		return this.name;
	}

	public boolean isCompleted() {
		return status.get();
	}
	
	// Getter and Setter methods
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BooleanProperty getStatus() {
		return BooleanProperty.booleanProperty(status);
	}

	public void setStatus(boolean status) {
		this.status.set(status);
	}

	public Goal getGoalFromName(String name) {
		if (this.name.equals(name)) {
			return this;
		}	
		return null;
		
	}

}
