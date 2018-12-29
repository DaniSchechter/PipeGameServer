package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS<T> extends CommonSearcher<T> {
	private Queue<State<T>> openList;

	public BFS() {
		openList = new LinkedList<State<T>>();
	}

	@Override
	public State<T> search(Searchable<T> searchable) {
		this.openList.clear();

		Set<State<T>> passedStates = new HashSet<State<T>>();
		List<State<T>> neighbors = new LinkedList<State<T>>();
		State<T> state = null;

		openList.add(searchable.getInitialState());
		passedStates.add(searchable.getInitialState());

		while (!openList.isEmpty()) {
			state = openList.remove();

			if (searchable.isGoalState(state))
				return state;

			neighbors = searchable.getAllPosibleStates(state);
			for (State<T> st : neighbors) {
				if (!passedStates.contains(st)) {
					passedStates.add(st);
					openList.add(st);
				}
			}
		}
		return null;
	}

}
