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
        System.out.println("c: " + c);
        System.out.println("height " + skipList.height);
        skipList.insert(0);
        skipList.print();
    }

}
