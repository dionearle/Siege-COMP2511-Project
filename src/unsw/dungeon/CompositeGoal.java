package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CompositeGoal implements Goal {

	private String name;
	private BooleanProperty status;
	
	private ArrayList<Goal> children = new ArrayList<Goal>();

	private LogicalOperator logicalOperator;
	
	/**
	 * Composite goal object.
	 * @param name		Name of customised specific composite goal.
	 */
	public CompositeGoal(String name) {
		super();
		this.name = name;
		this.status = new SimpleBooleanProperty();
	}
	
	public void setLogicalOperator(LogicalOperator lo) {
		logicalOperator = lo;
	}

	public String nameGoal() {
		String name = "[" + this.getName()  + " "; 
		for(Goal c : children) {
			name = name + " " + c.nameGoal();
		}	
		name = name + "]";
		return name;
	}

	public boolean isCompleted() {
		return logicalOperator.isCompleted();
	}
	
	public Goal getGoalFromName(String name) {
		
		if (this.getName().equals(name)) {
			return this;
		}
		
		for(Goal c : children) {
			Goal testChild = c.getGoalFromName(name);
			if (testChild != null) {
				return testChild;
			}
		}	
		return null;
	}
	
	/**
	 * Add child goal to composite goal.
	 * @param child		Child goal to be added.
	 * @return			Return true if operation successful.
	 */
	public boolean add(Goal child) {
		children.add(child);
		return true;
	}

	/**
	 * Remove child goal to composite goal.
	 * @param child		Child goal to be removed.
	 * @return			Return true if operation successful.
	 */
	public boolean remove(Goal child) {
		children.remove(child);
		return true;
	}
	
	public ArrayList<Goal> getChildren() {
		return children;
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

}
