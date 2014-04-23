import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CarlArrayHashMap<K, V> implements Map<K, V> {

	private Entry<K, V>[] entrys;

	private int size = 0;

	public CarlArrayHashMap(int size) {
		entrys = new Entry[size];
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
		int index = key.hashCode() % entrys.length;
		if(entrys[index])
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
			
		} else {
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
		entrys = new Entry[size];
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
