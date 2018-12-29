package algorithm;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BestFS<T> extends CommonSearcher<T>{
	private Queue<State<T>> stateQueue;
	private StateGrader<T> stateGrader;
	Comparator<State<T>> comparator;
	
	public BestFS(StateGrader <T> stateGrader,Comparator<State<T>> comparator)
	{
		this.stateQueue = new PriorityQueue<State<T>>(1,comparator);
		this.stateGrader = stateGrader;
		this.comparator= comparator;
	}

	@Override
	public State<T> search(Searchable<T> searchable) {
		this.stateQueue.clear();
		
		Set<State<T>> passedStates=new HashSet<State<T>>();
		Map<Integer,State<T>> stateMap  = new HashMap<Integer,State<T>>();
		State<T> state = searchable.getInitialState();
		List<State<T>> neighbors = new LinkedList<State<T>>();
		
		stateQueue.add(state);
		boolean inPassedStates,inStepQueue;
		int hashCode;
		
		while(!stateQueue.isEmpty())
		{
			
			state = stateQueue.remove();
			passedStates.add(state);
			hashCode=state.hashCode();
			if(searchable.isGoalState(state))
				return state;          
				
			neighbors = searchable.getAllPosibleStates(state);
			for(State<T> st: neighbors)
			{
				st.setCost(stateGrader.grade(st)); //change/////////////////
				inPassedStates= passedStates.contains(st);
				inStepQueue=stateQueue.contains(st);
				if(!inPassedStates && !inStepQueue)
				{
					stateQueue.add(st);
					stateMap.put(st.hashCode(), st);
				}
				else if(!inPassedStates)
				{
					hashCode=st.hashCode();
					if(st.getCost()>stateMap.get(hashCode).getCost())
					{
						stateQueue.remove(st);
						stateQueue.add(st);
						stateMap.replace(hashCode, st);
					}
				}
			}
		}
		return null;
	}

}

