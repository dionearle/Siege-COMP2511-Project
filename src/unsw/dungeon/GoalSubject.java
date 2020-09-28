package unsw.dungeon;

public interface GoalSubject {

	public void registerGoalObserver(GoalObserver observer);
	public void removeGoalObserver(GoalObserver observer);
	public void notifyGoalObservers();
	public void handleGoalUpdate();
	
}
