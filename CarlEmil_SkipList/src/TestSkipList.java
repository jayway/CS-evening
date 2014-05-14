import org.junit.Before;

public class TestSkipList {

	private SkipList skipList;

	@Before
	public void setUp() throws Exception {
		skipList = new SkipList();
	}

	@org.junit.Test
	public void testInsertTop() {

		int c = skipList.flipCoins();

		skipList.insert(0);

		System.out.println("c: " + c);
		skipList.print();
	}

}
