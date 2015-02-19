package w2tests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import w2collections.RandomizedQueue;

public class TestRandomizedQueue {
    private RandomizedQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test
    public void testSize() {
        int n = queue.size();
        assertEquals("Initial", 0, n);
        queue.enqueue(1);
        n = queue.size();
        assertEquals("Enqueue", 1, n);
        queue.dequeue();
        assertEquals("Dequeue", 0, queue.size());
    }

    @Test
    public void testEnqueue() {
        boolean correct = true;
        try {
            queue.enqueue(1);
        } catch (Exception e) {
            correct = false;
        }

        assertTrue("method should not throw any exceptions", correct);
    }

    @Test
    public void testEnqueueNull() {
        boolean correct = false;
        try {
            queue.enqueue(null);
        } catch (NullPointerException e) {
            correct = true;
        } catch (Exception e) {

        }

        assertTrue("method should throw NullPointerException", correct);
    }

    @Test
    public void testDequeue() {
        boolean correct = true;
        int n = 0;
        try {
            queue.enqueue(1);
            n = queue.dequeue();

        } catch (Exception e) {
            correct = false;
        }
        assertTrue("method shouldn't throw any exceptions", correct);
        assertEquals("incorrect size after removal", 0, queue.size());
        assertEquals("Incorrect removal", 1, n);

    }

    @Test
    public void testDequeueEmptyQueue() {
        boolean correct = false;
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            correct = true;
        } catch (Exception e) {

        }

        assertTrue(
                "method should throw NoSuchElementException if the queue is empty",
                correct);

    }

    @Test
    public void testSample() {
        boolean correct = true;
        int n = 0;
        try {
            queue.enqueue(1);
            n = queue.sample();

        } catch (Exception e) {
            correct = false;
        }
        assertTrue("method shouldn't throw any exceptions", correct);
        assertEquals("incorrect size after sampling", 1, queue.size());
        assertEquals("Incorrect sample", 1, n);
    }

    @Test
    public void testIterator() {
        boolean correct = true;
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        int count = 0;
        try {
            for (Object i : queue) {
                System.out.println(i);
                count++;
            }
        } catch (Exception e) {
            correct = false;
        }
        assertTrue("method shouldn't throw any exceptions", correct);
        assertEquals("incorrect number of elements revewed", 3, count);
    }

    @Test
    public void testIteratorRemove() {
        boolean correct = false;
        try {
            Iterator<Integer> iter = queue.iterator();
            iter.remove();
        } catch (UnsupportedOperationException e) {
            correct = true;
        } catch (Exception e) {

        }
        assertTrue("method should throw UnsupportedOperationException", correct);
    }

    @Test
    public void testIteratorNext() {
        boolean correct = false;
        queue.enqueue(1);
        queue.enqueue(2);
        try {

            Iterator<Integer> iter = queue.iterator();
            iter.hasNext();
            iter.next();
            iter.hasNext();
            iter.next();
            iter.next();
        } catch (NoSuchElementException e) {
            correct = true;
        } catch (Exception e) {

        }

        assertTrue("method should throw NoSuchElementException", correct);
    }

}
