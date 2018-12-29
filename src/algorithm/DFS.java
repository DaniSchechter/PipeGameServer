package algorithm;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;



public class DFS<T> extends CommonSearcher<T> {
	private Stack<State<T>> openStack;
	public DFS()
	{
		openStack = new Stack<State<T>>();
	}

	@Override
	public State<T> search(Searchable<T> searchable) {
		this.openStack.clear();
		
		Set<State<T>> passedStates=new HashSet<State<T>>();
		State<T> state= searchable.getInitialState();
		List<State<T>> neighbors = new LinkedList<State<T>>();
		
		openStack.add(state);
		passedStates.add(state);
		
		while (!openStack.isEmpty())
		{
			state = openStack.pop();
			
			if(searchable.isGoalState(state))
				return state;
			
			neighbors = searchable.getAllPosibleStates(state);
			for(State<T> st : neighbors)
			{
				if(!passedStates.contains(st))
				{
					openStack.push(st);
					passedStates.add(st);
				}
			}
		}
		
		
		return null;
	}
	

}

