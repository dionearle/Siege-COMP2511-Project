package unsw.dungeon;

public class OrOperator implements LogicalOperator {

	private CompositeGoal goal;

	public OrOperator(CompositeGoal goal) {
		this.goal = goal;
	}
	
	public boolean isCompleted() {
		
		// if its OR then we only need one child to be true
		for(Goal c : goal.getChildren()) {
			if (c.isCompleted()) {
				return true;
			}
		}
		return false;
	}

}
