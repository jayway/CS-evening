import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestSkipList {

	private SkipList skipList;

	@Before
	public void setUp() throws Exception {
		skipList = new SkipList();
	}

	@org.junit.Test
	public void testInsertTop() {

		int c = skipList.flipCoins();
		System.out.println("c: " + c);
		skipList.print();
	}

}
