package unsw.dungeon;

public class AndOperator implements LogicalOperator {
	
	private CompositeGoal goal;

	public AndOperator(CompositeGoal goal) {
		this.goal = goal;
	}

	public boolean isCompleted() {
		
		// if its AND we need all children to be true
		for(Goal c : goal.getChildren()) {
			if (!c.isCompleted()) {
				return false;
			}
		}
		return true;

	}

}
