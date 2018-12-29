package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.Problem;
import server.Solution;

public class SearcherSolver implements Solver {
	private Searcher<PipeGameBoard> searcher; //change
	
	public SearcherSolver(Searcher<PipeGameBoard>/*change*/ searcher) {
		super();
		this.searcher = searcher;
	}
	
	
	@Override
	public Solution solve(Problem p) {
		State<PipeGameBoard> st=searcher.search(new SearchableBoard(new PipeGameBoard(p)));
		return this.backTrace(st);
	}
	
	private Solution backTrace(State<PipeGameBoard> state) {
		Solution solution = new Solution();
		return helpBackTrace(state, solution);
	}

	private Solution helpBackTrace(State<PipeGameBoard> state, Solution solution) {
		Map<Integer,Step> mapSteps =new HashMap<Integer,Step>();
		int hashCode;
		Step temp;

		while(state.getCameFrom()!=null)
		{
			temp = state.getStepCameFrom();
			hashCode = temp.hashCode();
			if(mapSteps.containsKey(hashCode))
			{
				temp=mapSteps.get(hashCode);
				temp.setNumToRotrate(temp.getNumToRotrate()+1);
				mapSteps.replace(hashCode, temp);
			}
			else
				mapSteps.put(hashCode, temp);
			state=state.getCameFrom();
		}
		for(Step s: mapSteps.values())
		{
			s.setNumToRotrate(s.getNumToRotrate()%4);
		}
		ArrayList<Step> array = new ArrayList<Step>(mapSteps.values());
		return new Solution(array);
	}

	
}
