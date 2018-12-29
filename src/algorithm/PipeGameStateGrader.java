package algorithm;

public class PipeGameStateGrader implements StateGrader<PipeGameBoard>{

	@Override
	public double grade(State<PipeGameBoard> s) {
		double grade = 0;
		PipeGameBoard board = s.getState();
		char[][] array= board.getBoard();
		int numRows = array.length;
		int numCols = array[0].length;
		
		for(int i=0;i<numRows;i++)
			for(int j=0; j<numCols;j++)
			{
				if(isLegalUp(array, i, j))
					grade++;
				if(isLegalDown(array, i, j))
					grade++;
				if(isLegalLeft(array, i, j))
					grade++;
				if(isLegalRight(array, i, j))
					grade++;
			}
		return grade+1/s.getCost();
	}
	private boolean isLegalUp(char[][]board , int row,int col)
	{
		if(row==0)return false;
		char from = board[row][col];
		char up = board[row-1][col];
		return (from=='J'||from=='L'||from=='|')&&(up=='|'||up=='7'||up=='F');
	}
	private boolean isLegalDown(char[][]board , int row,int col)
	{
		if(row==board.length-1) return false;
		char from = board[row][col];
		char down = board[row+1][col];
		return (from=='7'||from=='F'||from=='|')&&(down=='J'||down=='L'||down=='|');
	}
	private boolean isLegalLeft(char[][]board , int row,int col)
	{
		if(col==0) return false;
		char from = board[row][col];
		char left = board[row][col-1];
		return (from=='J'||from=='7'||from=='-')&&(left=='-'||left=='L'||left=='F');
	}
	private boolean isLegalRight(char[][]board , int row,int col)
	{
		if(col==board[0].length-1) return false;
		char from = board[row][col];
		char right = board[row][col+1];
		return (from=='F'||from=='L'||from=='-')&&(right=='-'||right=='7'||right=='J');
	}
	

}
