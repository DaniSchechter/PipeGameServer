package algorithm;

public interface Searcher <T>{
	public State<T> search(Searchable<T> searchable);
}
