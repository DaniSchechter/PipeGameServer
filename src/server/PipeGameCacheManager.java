package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import algorithm.Step;

public class PipeGameCacheManager implements CacheManager<Problem,Solution> {

	@Override
	public Solution getSolution(Problem problem) {
		int hashCode = problem.hashCode();
		Solution solutionList = new Solution();
		BufferedReader inFromFile= null;
		File file = new File(""+hashCode+".txt");
		
		try {
			inFromFile = new BufferedReader(new FileReader(file));
		    Step step = new Step();
		    String line;

		    while ((line = inFromFile.readLine()) != null) {
		    	step.strToStep(line);
		    	solutionList.add(new Step(step));
		    }
		} catch (FileNotFoundException e) {
		    return null;
		} catch (IOException e) {
		} finally {
		    try {
		        if (inFromFile != null) {
		        	inFromFile.close();
		        }
		    } catch (IOException e) {
		    }
		}
		return solutionList;
		
	}
	
	public Boolean ifExistsSolution(Problem problem) {
		int hashCode = problem.hashCode();
		System.out.print("[to problem "+hashCode+"]");
		if(new File(""+hashCode+".txt").exists()) return true;
		return false;
	}
	public void saveSolutionToFile(Problem problem, Solution solution)
	{
		int hashCode = problem.hashCode();
		PrintWriter outToFile= null;
		File file = new File(""+hashCode+".txt");
		
		try {
			outToFile = new PrintWriter(file);
		    for(Step step:solution)
		    {
		    	outToFile.println(step.stepToStr());
		    }
		    outToFile.flush();
		} catch (FileNotFoundException e) {
		} finally {
		    if (outToFile != null) {
				outToFile.close();
			}
		}
	}
}

