package w2collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

/**
 * Implementation of a randomized queue. It is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random from items in the
 * data structure.
 *
 * @author Julia.Denisova
 * @since 2/13/2015
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * queue items.
     */
    private Item[] items;
    /**
     * number of queue items.
     */
    private int size = 0;

    /**
     * constructs empty queue.
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    /**
     * verifies if the queue is empty.
     *
     * @return true if the queue is empty and false if not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * size of the queue.
     *
     * @return number of queue items.
     */
    public int size() {
        return size;
    }

    /**
     * adds an item to the queue tail.
     *
     * @param item
     * @throws NullPointerException
     *             for null item.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null item");
        }

        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size] = item;
        size++;
    }

    /**
     * remove a random item of the queue.
     *
     * @return the removed item.
     * @throws NoSuchElementException
     *             for an empty queue.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            Item it = items[0];
            items = (Item[]) new Object[1];
            size--;
            return it;
        } else {
            int n = StdRandom.uniform(size);
            Item r = items[n];
            items[n] = items[size - 1];
            items[size - 1] = null;
            size--;
            if (size < items.length/4) {
                resize(items.length/2);
            }
            return r;
        }

    }

    /**
     * shows a random element of the queue.
     *
     * @return the chosen element.
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int n = StdRandom.uniform(size);
        return items[n];
    }

    /**
     * @return a randomized iterator over queue elements.
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Item[] arr = (Item[]) new Object[size];
            {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = items[i];
                }
                StdRandom.shuffle(arr);

            }
            private int index = 0;
            private boolean hasNext = false;

            @Override
            public boolean hasNext() {
                hasNext = (index < size);
                return hasNext;
            }

            @Override
            public Item next() {
                if (hasNext) {
                    Item it = arr[index];
                    index++;
                    hasNext = false;
                    return it;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    /**
     * resizes queue capacity.
     * 
     * @param desired
     *            capacity of the queue.
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                copy[j] = items[i];
                j++;
            }
        }
        items = copy;
    }

}
