package algorithm;

public class State<T>{
	private T state;
	private State<T> cameFrom;
	private double cost;
	private Step stepCameFrom;
	
	public State (T state, State<T> cameFrom, Step stepCameFrom, double cost)
	{
		this.state = state;
		this.cameFrom = cameFrom;
		this.stepCameFrom= stepCameFrom;
		this.cost = cost;
	}
	@Override
	public int hashCode() {
		return state.hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if(other==null)return false;
		if(other==this)return true;
		if(!(other instanceof State<?>)) return false;
		State<?> s= (State<?>)other;
		return this.state.equals(s.getState());
	}

	public Step getStepCameFrom() {
		return stepCameFrom;
	}
	public void setStepCameFrom(Step stepCameFrom) {
		this.stepCameFrom = stepCameFrom;
	}
	public T getState() {
		return state;
	}
	public void setState(T state) {
		this.state = state;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	//test////////////////////////////////////////////
	public State(T state)
	{
		this.state=state;
		this.cameFrom=null;
		this.cost=0;
	}
	public State(T state,State<T> cameFrom,double cost)
	{
		this.state=state;
		this.cameFrom=cameFrom;
		this.cost=cost;
	}
	
}
