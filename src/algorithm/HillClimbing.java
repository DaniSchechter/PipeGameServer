package algorithm;
import java.util.List;
import java.util.Random;


public class HillClimbing<T> extends CommonSearcher<T> {
	private StateGrader<T> stateGrader;
	long timeToRun;

	public HillClimbing(StateGrader <T> stateGrader, long timeToRun)
	{
		this.timeToRun = timeToRun;
		this.stateGrader = stateGrader;
	}
	@Override
	public State<T> search(Searchable <T> searchable) {
		State <T> state = searchable.getInitialState();
		List<State<T>> neighbors;
		long t = System.currentTimeMillis();
		while((System.currentTimeMillis() - t <this.timeToRun) && (!searchable.isGoalState(state)))
		{
			neighbors = searchable.getAllPosibleStates(state);
			if(Math.random() < 0.7)
			{
				double grade = 0;
				for(State<T> st:neighbors)
				{
					double g = stateGrader.grade(st);
					if(g>grade)
					{
						grade = g;
						state = st;
					}
				}
			}
			else
			{
				state = neighbors.get(new Random().nextInt(neighbors.size()));
			}
		}
		
		return state;
	}

}
