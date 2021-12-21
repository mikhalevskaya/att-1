package lib.my;
//import org.junit.Assert;
//import org.junit.Test;
//import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.jupiter.api.Assertions.*;

public class MyHSetTest {

    @Test
    public void testTestContains(){
        MyHSet<Integer> hashSet = new MyHSet<>();
        hashSet.add(20);
        hashSet.add(30);
        hashSet.add(50);
        assertTrue(hashSet.contains(50));
    }

    @Test
    public void testTestAdd(){
        MyHSet<Integer> hashSet = new MyHSet<>();
        hashSet.add(20);
        assertTrue(hashSet.contains(20));
    }

    @Test
    public void testTestRemove(){
        MyHSet<Integer> hashSet = new MyHSet<>();
        hashSet.add(20);
        hashSet.add(30);
        hashSet.add(50);
        hashSet.remove(20);
        assertTrue(!hashSet.contains(20));
    }
}