package server;

public interface CacheManager<P, S> {
	public S getSolution(P problem);
	public Boolean ifExistsSolution(P problem);
	public void saveSolutionToFile(P problem, S solution);
}
