package algorithm;

import java.util.List;
import java.util.LinkedList;

public class SearchableBoard implements Searchable<PipeGameBoard> {
	private PipeGameBoard pipeboard;
	
	public SearchableBoard(PipeGameBoard pipeboard) {
		super();
		this.pipeboard = pipeboard;
	}

	// Functions

	@Override
	public State<PipeGameBoard> getInitialState() {
		// Initial state so came from is null
		return new State<PipeGameBoard>(pipeboard, null, null,0);
	}

	@Override
	public List<State<PipeGameBoard>> getAllPosibleStates(State<PipeGameBoard> cameFrom) {
		List<State<PipeGameBoard>> list = new LinkedList<State<PipeGameBoard>>();
		PipeGameBoard originalBoard = cameFrom.getState();
		char[][] array = originalBoard.getBoard();
		int numRows = array.length;
		int numCol = array[0].length;
	
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numCol; j++) {
				if((array[i][j]=='s') || (array[i][j]=='g')||(array[i][j]==' ')) continue;
				PipeGameBoard newBoard = new PipeGameBoard(originalBoard);
				Step step = new Step(i, j, 1);
				newBoard.rotrateCell(step);
				list.add(new State<PipeGameBoard>(newBoard, cameFrom, step, cameFrom.getCost()+1));
			}
		return list;
	}

	@Override
	public boolean isGoalState(State<PipeGameBoard> state) {
		PipeGameBoard pipeBoard = state.getState();
		Pair<Integer,Integer> coordinate = getCellCoordinates(pipeBoard.getBoard(),'s');
		return recursiveIsGoalState(pipeBoard.getBoard(),coordinate.getX(),coordinate.getY() );
	}

	private Pair<Integer, Integer> getCellCoordinates(char[][] board,char id) {
		int numRows = board.length;
		int numCols = board[0].length;
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numCols; j++)
				if (board[i][j] == id)
					return new Pair<Integer, Integer>(i, j);
		return new Pair<Integer, Integer>(-1, -1);
	}

	private boolean recursiveIsGoalState(char[][] board,int row,int col)
	{	
		char c;
		boolean up=false,down=false,left=false,right=false;
		/* 'n' represents visited cell; we cannot go back to 'n' but we can
		come out from 'n'*/
		if(board[row][col]=='g') return true;
		if(isLegalUp(board,row,col))
		{
			c= board[row][col];
			board[row][col]='n';
			up = recursiveIsGoalState(board,row-1,col);
			board[row][col]=c;
		}
		if(isLegalDown(board,row,col))
		{
			c=board[row][col];
			board[row][col]='n';
			down = recursiveIsGoalState(board,row+1,col);
			board[row][col]=c;
		}
		if(isLegalLeft(board,row,col))
		{
			c=board[row][col];
			board[row][col]='n';
			left = recursiveIsGoalState(board,row,col-1);
			board[row][col]=c;
		}
		if(isLegalRight(board,row,col))
		{
			c=board[row][col];
			board[row][col]='n';
			right = recursiveIsGoalState(board,row,col+1);
			board[row][col]=c;
		}

		return (up||down||left||right);
	}
	
	private boolean isLegalUp(char[][]board , int row,int col)
	{
		if(row==0)return false;
		char from = board[row][col];
		char up = board[row-1][col];
		return (from=='n'||from=='s'||from=='J'||from=='L'||from=='|')&&(up=='|'||up=='7'||up=='F'||up=='g');
	}
	private boolean isLegalDown(char[][]board , int row,int col)
	{
		if(row==board.length-1) return false;
		char from = board[row][col];
		char down = board[row+1][col];
		return (from=='n'||from=='s'||from=='7'||from=='F'||from=='|')&&(down=='J'||down=='L'||down=='|'||down=='g');
	}
	private boolean isLegalLeft(char[][]board , int row,int col)
	{
		if(col==0) return false;
		char from = board[row][col];
		char left = board[row][col-1];
		return (from=='n'||from=='s'||from=='J'||from=='7'||from=='-')&&(left=='-'||left=='L'||left=='F'||left=='g');
	}
	private boolean isLegalRight(char[][]board , int row,int col)
	{
		if(col==board[0].length-1) return false;
		char from = board[row][col];
		char right = board[row][col+1];
		return (from=='n'||from=='s'||from=='F'||from=='L'||from=='-')&&(right=='-'||right=='7'||right=='J'||right=='g');
	}
	

}
