package algorithm;

import java.util.Comparator;

public class PipeGameComparator<T> implements Comparator<State<T>>{

	@Override
	public int compare(State<T> board, State<T> other) {
		return (int) (other.getCost() - board.getCost());
	}
	

}
