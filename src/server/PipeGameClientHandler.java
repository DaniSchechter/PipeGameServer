package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import algorithm.Solver;
import algorithm.Step;

public class PipeGameClientHandler implements ClientHandler {
	private CacheManager<Problem, Solution> cm;
	private Solver s;

	public PipeGameClientHandler(CacheManager<Problem, Solution> cm,Solver s) {
		this.cm = cm;
		this.s=s;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		boolean existSolution = false;
		Problem problem=new Problem();
		Solution solution=null;
		long currTime;
		
		//Get information from client
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
			String line=null;
			while (!(line = in.readLine()).equals("done")) {
				problem.add(line);
			}
		} catch (IOException e) {}
		// check solution existence in files
		solution = new Solution();
		
		System.out.print("Solution ");
		currTime=System.currentTimeMillis();
		
		if(existSolution=cm.ifExistsSolution(problem))
		{
			solution.addAll(cm.getSolution(problem));
			System.out.print(" was found in file");
		}
		else
		{
			solution = s.solve(problem);
			System.out.print(" was generated");
		}
		
		System.out.print(" in "+(System.currentTimeMillis()-currTime) +"[ms]");
		
		//Send Solution to client
		PrintWriter out = new PrintWriter(outToClient);
		for(Step step: solution)
		{
			out.println(step.stepToStr());
		}
		out.println("done");
		System.out.println(" and sent after "+ (System.currentTimeMillis()-currTime) +"[ms]");
		out.flush();
		//save solution in file
		if(!existSolution)
		{
			cm.saveSolutionToFile(problem, solution);
		}
	}
}
