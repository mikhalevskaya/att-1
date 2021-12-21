//import static org.junit.jupiter.api.Assertions.*;
package lib.my;
//import junit.framework.TestCase;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyHMultiSetTest  {
    @Test
    public void testContains(){
        MyHMultiSet<Integer> hashMultiSet = new MyHMultiSet<>();
        hashMultiSet.add(20);
        hashMultiSet.add(20);
        hashMultiSet.add(50);
        assertTrue(hashMultiSet.contains(50));
    }

    @Test
    public void testAdd(){
        MyHMultiSet<Integer> hashMultiSet = new MyHMultiSet<>();
        hashMultiSet.add(20);
        assertTrue(hashMultiSet.contains(20));
    }

    @Test
    public void testRemove(){
        MyHMultiSet<Integer> hashMultiSet = new MyHMultiSet<>();
        hashMultiSet.add(20);
        hashMultiSet.add(30);
        hashMultiSet.remove(20);
        assertFalse(hashMultiSet.contains(20));
    }

}