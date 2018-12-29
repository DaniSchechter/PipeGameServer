package algorithm;

public class Step {
	private int row;
	private int col;
	private int numToRotrate;
	
	public Step()
	{
		this.row = -1;
		this.col = -1;
		this.numToRotrate = -1;
	}
	public Step(int row, int col, int numToRotrate)
	{
		this.row = row;
		this.col = col;
		this.numToRotrate = numToRotrate;
	}
	public Step(Step other)
	{
		this.row=other.row;
		this.col=other.col;
		this.numToRotrate=other.numToRotrate;
	}
	
	//Convert from step to string
	public String stepToStr()
	{
		return ("" + this.row + "," + this.col + "," + this.numToRotrate);
	}
	//Convert from string to step
	public void strToStep(String str)
	{
		this.row = str.charAt(0)-'0';
		this.col = str.charAt(2)-'0';
		this.numToRotrate =str.charAt(4)-'0';
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getNumToRotrate() {
		return numToRotrate;
	}
	public void setNumToRotrate(int numToRotrate) {
		this.numToRotrate = numToRotrate;
	}
	public void setAllValues(int row,int col,int numToRotrate)
	{
		this.row = row;
		this.col = col;
		this.numToRotrate = numToRotrate;
	}
	@Override
	public int hashCode() {
		return (""+row+col).hashCode();
	}
	@Override
	public boolean equals(Object other) {
		Step otherStep = (Step)other;
		return (this.row==otherStep.getRow() && this.col == otherStep.getCol());
	}
}
