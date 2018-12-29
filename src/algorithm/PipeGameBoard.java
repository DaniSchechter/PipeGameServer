package algorithm;

import server.Problem;

public class PipeGameBoard {
	private char[][] board;
	public PipeGameBoard(Problem problem) {
		board=new char[problem.size()][problem.get(0).length()];
		for(int i=0;i<problem.size();i++)
			board[i]=problem.get(i).toCharArray();
	}
	public char[][] getBoard()
	{
		return this.board;
	}
	public void rotrateCell(Step s)
	{
		int row = s.getRow();
		int col = s.getCol();
		board[row][col]=getRotratedCell(board[row][col]);
	}
	private char getRotratedCell(char c)
	{	
		if(c=='F') return '7';
		if(c=='7') return 'J';
		if(c=='J') return 'L';
		if(c=='L') return 'F';
		if(c=='-') return '|';
		if(c=='|') return '-';
		return c;
	}
	public PipeGameBoard(PipeGameBoard other)
	{
		int numRows = other.getBoard().length;
		int numCols=other.getBoard()[0].length;
		this.board = new char[numRows][numCols];
		for(int i=0;i<numRows;i++)
			for(int j=0;j<numCols;j++)
			{
				this.board[i][j] = other.board[i][j];
			}
	}
	@Override
	public String toString()
	{
		StringBuilder s=new StringBuilder();
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++)
				s.append(board[i][j]);
			s.append('\n');
		}
		return s.toString();
	}

	@Override
	public int hashCode() {
		StringBuilder b = new StringBuilder();
		int numRows=board.length;
		int numCols=board[0].length;
		for(int i=0;i<numRows;i++)
			for(int j=0;j<numCols;j++)
				b.append(board[i][j]);
		return b.toString().hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if(other==null)return false;
		if(other==this)return true;
		if(!(other instanceof PipeGameBoard)) return false;
		PipeGameBoard otherBoard= (PipeGameBoard)other;
		
		int numRows=board.length;
		int numCols=board[0].length;
		for(int i=0;i<numRows;i++)
			for(int j=0;j<numCols;j++)
				if(board[i][j]!=otherBoard.getBoard()[i][j]) return false;
		return true;
	}
}
