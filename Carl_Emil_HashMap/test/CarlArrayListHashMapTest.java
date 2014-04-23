import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CarlArrayListHashMapTest {

	private CarlArrayListHashMap<String, String> hashMap;

	@Before
	public void setUp() throws Exception {
		hashMap = new CarlArrayListHashMap<String, String>(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZeroSize() {
		assertEquals(0, hashMap.size());
	}

	@Test
	public void testGetEmpty() {
		assertEquals(null, hashMap.get("a"));
	}

	@Test
	public void testPutOne() {
		hashMap.put("a", "a");
		assertEquals(1, hashMap.size());
	}

	@Test
	public void testPutTwo() {
		hashMap.put("a", "a");
		assertEquals(1, hashMap.size());
		hashMap.put("b", "b");
		assertEquals(2, hashMap.size());
	}

	@Test
	public void testPutTwoSame() {
		hashMap.put("a", "a");
		assertEquals(1, hashMap.size());
		hashMap.put("a", "a");
		assertEquals(2, hashMap.size());
	}

	@Test
	public void testGetOne() {
		hashMap.put("a", "a");
		assertEquals("a", hashMap.get("a"));
	}

	@Test
	public void testPutMany() {
		hashMap.put("a1", "a1");
		hashMap.put("a2", "a2");
		hashMap.put("a3", "a3");
		hashMap.put("a4", "a4");
		hashMap.put("a5", "a5");
		assertEquals(5, hashMap.size());
	}

	@Test
	public void testGetMany() {
		hashMap.put("a1", "a1");
		hashMap.put("a2", "a2");
		hashMap.put("a3", "a3");
		hashMap.put("a4", "a4");
		hashMap.put("a5", "a5");
		assertEquals("a2", hashMap.get("a2"));
	}

	@Test
	public void testClear() {
		hashMap.put("a", "a");
		hashMap.clear();
		assertEquals(0, hashMap.size());
		assertEquals(null, hashMap.get("a"));
	}

	@Test
	public void testContainsKey() {
		hashMap.put("a", "a");
		assertEquals(true, hashMap.containsKey("a"));
		assertEquals(false, hashMap.containsKey("b"));

	}

}
