package algorithm;

import java.util.List;

public interface Searchable<T> {
	
	public State<T> getInitialState();
	public List<State<T>> getAllPosibleStates(State<T> s);
	public boolean isGoalState(State<T> s);
}
