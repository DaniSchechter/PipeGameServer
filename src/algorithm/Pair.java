package algorithm;

public class Pair<K,V> {
	private final K x;
	private final V y;
	public Pair(K key, V value) {
		super();
		this.x = key;
		this.y = value;
	}
	
	public K getX() {
		return x;
	}

	public V getY() {
		return y;
	}

}
