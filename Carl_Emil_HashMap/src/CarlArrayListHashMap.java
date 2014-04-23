import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CarlArrayListHashMap<K, V> implements Map<K, V> {

	private ArrayList<Entry<K, V>>[] entrys;

	private int size = 0;

	public CarlArrayListHashMap(int size) {
		entrys = (ArrayList<Entry<K, V>>[]) new ArrayList[size];
	}

	@Override
	public int size() {
		new ArrayList<String>();
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		if (get(key) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		int index = getIndex(key);
		ArrayList<Entry<K, V>> array = entrys[index];
		if (array != null) {
			for (Entry e : array) {
				if (e.key.equals(key)) {
					return (V) e.value;
				}
			}
		}
		return null;
	}

	private int getIndex(Object key) {
		return key.hashCode() % entrys.length;
	}

	@Override
	public V put(K key, V value) {

		int index = getIndex(key);

		Entry entry = new Entry(key, value);

		if (entrys[index] == null) {
			ArrayList<Entry<K, V>> array = new ArrayList<Entry<K, V>>();
			array.add(entry);
			entrys[index] = array;
		} else {
			entrys[index].add(entry);
		}

		size++;
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		entrys = (ArrayList<Entry<K, V>>[]) new ArrayList[entrys.length];
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	class Entry<K, V> {
		public Entry(K k, V v) {
			key = k;
			value = v;
		}

		K key;
		V value;
	}
}
