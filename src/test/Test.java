package test;

/*
 1. בכל מחלקה שלו לבדוק אם יש לו:
 	hashcode,( default!!!! equals)
 	public boolean equals(Object arg0) {
		return this.equals((Grid)arg0);
	}
 2.במימוש של
   getallpossiblestates 
   לא לשכול להשתמש בקונסטרקטור שמקבל 3 פרמטרים (בשביל האבא)
   State<Grid> state = new State<Grid>(g,s,0);
3. in backtrace dont forget to go back (state = state.getCameFrom();)   
  
 */
import algorithm.DFS;
import algorithm.HillClimbing;
import algorithm.PipeGameBoard;
import algorithm.PipeGameStateGrader;
import algorithm.SearcherSolver;
import algorithm.Solver;
import algorithm.StateGrader;
import server.CacheManager;
import server.ClientHandler;
import server.PipeGameCacheManager;
import server.PipeGameClientHandler;
import server.PipeGameServer;
import server.Problem;
import server.Server;
import server.Solution;

public class Test {

	public static void main(String[] args) {
		StateGrader<PipeGameBoard> sg= new PipeGameStateGrader();
		Solver solver= new SearcherSolver(new HillClimbing<>(sg, 30000));
		CacheManager<Problem, Solution> cm = new PipeGameCacheManager(); 
		ClientHandler ch = new PipeGameClientHandler(cm, solver);
		Server server = new PipeGameServer(6400,ch);
		try {
			server.start();
		} catch (Exception e) {}
	}

}
/*
public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(PipeGameServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(PipeGameClientHandler.class);
		// cache manager interface
		dt.setCacheManagerInterface(CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(PipeGameCacheManager.class);
		// solver interface
		dt.setSolverInterface(Solver.class);
		// solver class
		dt.setSolverClass(SearcherSolver.class);
		// searchable interface
		dt.setSearchableInterface(Searchable.class);
		// searcher interface
		dt.setSearcherInterface(Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(SearchableBoard.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFS.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		Solver solver= new SearcherSolver(new BestFS<>(new PipeGameStateGrader(), new PipeGameComparator<>()));
		CacheManager<Problem, Solution> cm = new PipeGameCacheManager(); 
		ClientHandler ch = new PipeGameClientHandler(cm, solver);
		s=new PipeGameServer(port, ch);
		s.start();
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}
	
	 ------------- Best First Search Test --------------
	 * You are given a Maze
	 * Create a new Searchable from the Maze
	 * Solve the Maze
	 * and return a list of moves from {UP,DOWN,RIGHT,LEFT}
	 *  
	 
	
	public static List<String> solveMaze(Maze m){
		SearchableMaze sM = new SearchableMaze(m);
		Searcher<Grid> se= new BestFS<>(new MazestateGrader(m), new PipeGameComparator<>());
		State<Grid> sol = se.search(sM);
		return backTrace(sol);
	}
	private static List<String> backTrace(State<Grid> state) {
		List<String> sol = new LinkedList<String>();
		while(state.getCameFrom()!= null)
		{
			if(state.getState().row+1 == state.getCameFrom().getState().row)
			{
				sol.add("UP");
			}
			if(state.getState().row-1 == state.getCameFrom().getState().row)
			{
				sol.add("DOWN");
			}
			if(state.getState().col+1 == state.getCameFrom().getState().col)
			{
				sol.add("LEFT");
			}
			if(state.getState().col-1 == state.getCameFrom().getState().col)
			{
				sol.add("RIGHT");
			}
			state = state.getCameFrom();
		}
		return sol;
	}
}
class MazestateGrader implements StateGrader<Grid>
{
	private Maze m;
	public MazestateGrader(Maze m)
	{
		this.m = m;
	}
	@Override
	public double grade(State<Grid> s) {
		List<Grid> l =  m.getPossibleMoves(s.getState());
		return l.size();
}
}
class SearchableMaze implements Searchable<Grid> {
	private Maze maze;
	public SearchableMaze(Maze maze) {
		this.maze = maze;
	}
	@Override
	public State<Grid> getInitialState() {
		return new State<Grid>(maze.getEntrance());
	}
	@Override
	public List<State<Grid>> getAllPosibleStates(State<Grid> s) {
		List<State<Grid>> list = new LinkedList<State<Grid>>();
		List<Grid> l = new LinkedList<Grid>();
		l.addAll(maze.getPossibleMoves(s.getState()));
		for(Grid g : l)
		{
			State<Grid> state = new State<Grid>(g,s,0);
			list.add(state);
		}
		return list;
	}
	@Override
	public boolean isGoalState(State<Grid> s) {
		Grid g=maze.getExit();
		return g.equals(s.getState());
	}	
}
 */
