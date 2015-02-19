package w2tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import w2collections.Deque;

public class TestDeque {
    private Deque<Integer> d;

    @Before
    public void setUp() throws Exception {
        d = new Deque<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        d = null;
    }

    @Test
    public void testAddFirst() {
        boolean correct = true;
        int n = 0;
        try {
            d.addFirst(1);
            d.addFirst(2);
            n = d.removeLast();

        } catch (Exception e) {
            correct = false;
        }
        assertTrue(
                "Method should not throw any exceptions when adding elements.",
                correct);
        assertEquals("Adding from the incorrect side", 1, n);
    }

    @Test
    public void testAddFirstNull() {
        boolean correct = false;
        try {
            d.addFirst(null);
        } catch (NullPointerException e) {
            correct = true;

        } catch (Exception e) {

        }
        assertTrue(
                "Method should throw NullPointerException when adding null.",
                correct);
    }

    @Test
    public void testAddLast() {
        boolean correct = true;
        int n = 0;
        try {
            d.addLast(1);
            d.addLast(2);
            n = d.removeLast();

        } catch (Exception e) {
            correct = false;
            e.printStackTrace();
        }
        assertTrue(
                "Method should not throw any exceptions when adding elements.",
                correct);
        assertEquals("Adding from the incorrect side", 2, n);
    }

    @Test
    public void testAddLastNull() {
        boolean correct = false;
        try {
            d.addLast(null);
        } catch (NullPointerException e) {
            correct = true;

        } catch (Exception e) {

        }
        assertTrue(
                "Method should throw NullPointerException when adding null.",
                correct);
    }

    @Test
    public void testRemoveFirst() {
        boolean correct = true;
        int n = 0;
        int m = 0;
        try {
            d.addFirst(1);
            d.addFirst(2);
            d.addFirst(3);
            n = d.removeFirst();
            m = d.removeFirst();
        } catch (Exception e) {
            correct = false;
            e.printStackTrace();
        }
        assertTrue(
                "Method should throw any exception when removing from a deque.",
                correct);
        assertEquals("Incorrect element", 3, n);
        assertEquals("Incorrect element", 2, m);
    }

    @Test
    public void testRemoveFirstEmptyDeque() {
        boolean correct = false;
        try {
            d.removeFirst();
        } catch (NoSuchElementException e) {
            correct = true;

        } catch (Exception e) {

        }
        assertTrue("Method should throw NoSuchElementException"
                + " when removing from an empty deck.", correct);
    }

    @Test
    public void testRemoveLast() {
        boolean correct = true;
        int n = 0;
        int m = 0;
        try {
            d.addFirst(1);
            d.addFirst(2);
            d.addFirst(3);
            n = d.removeLast();
            m = d.removeLast();
        } catch (Exception e) {
            correct = false;
            e.printStackTrace();
        }
        assertTrue(
                "Method should throw any exception when removing from a deque.",
                correct);
        assertEquals("Incorrect element", 1, n);
        assertEquals("Incorrect element", 2, m);

    }

    @Test
    public void testRemoveLastEmptyDeque() {
        boolean correct = false;
        try {
            d.removeLast();
        } catch (NoSuchElementException e) {
            correct = true;

        } catch (Exception e) {

        }
        assertTrue("Method should throw NoSuchElementException"
                + " when removing from an empty deck.", correct);
    }

    @Test
    public void testIterator() {
        boolean correct = true;
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        List<Integer> l = new ArrayList<Integer>();
        Iterator<Integer> iter = d.iterator();
        try {
            while (iter.hasNext()) {
                l.add(iter.next());
            }
        } catch (Exception e) {
            correct = false;
            e.printStackTrace();
        }
        assertTrue("Shouldn't throw any exceptions", correct);
        assertEquals("Wrong item", 3, (int) l.get(0));
        assertEquals("Wrong item", 2, (int) l.get(1));
        assertEquals("Wrong item", 1, (int) l.get(2));
    }

    @Test
    public void testIteratorRemove() {
        boolean correct = false;
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);

        Iterator<Integer> iter = d.iterator();
        try {
            while (iter.hasNext()) {
                iter.remove();
            }
        } catch (UnsupportedOperationException e) {
            correct = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue("Should throw UnsupportedOperationException for"
                + " remove operation", correct);
    }

    @Test
    public void testSize() {

        int n = d.size();
        assertEquals("Initial", 0, n);

        d.addFirst(1);
        n = d.size();
        assertEquals("addFirst", 1, n);

        d.removeFirst();
        n = d.size();
        assertEquals("removeFirst", 0, n);

        d.addLast(1);
        n = d.size();
        assertEquals("addLast", 1, n);

        n = d.removeLast();
        n = d.size();
        assertEquals("removeLast", 0, n);
    }

    @Test
    public void testIteratorNext() {
        boolean correct = false;
        d.addFirst(1);

        Iterator<Integer> iter = d.iterator();
        try {
            iter.next();
            iter.next();

        } catch (NoSuchElementException e) {
            correct = true;
        } catch (Exception e) {

        }
        assertTrue("Should throw NoSuchElementException if no"
                + " elements when calling next()", correct);

    }

}
