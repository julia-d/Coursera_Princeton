package w2collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque. Class provides implementation of a general
 * stack and a queue that supports adding and removing items from either the
 * front or the back of the data structure.
 *
 * @author Julia.Denisova
 * @since 2/13/2015
 * @param <Item>
 *            type of deque.
 */
public class Deque<Item> implements Iterable<Item> {
    /**
     * node contains a link to the first item of the deque.
     */
    private Node<Item> first;
    /**
     * node contains a link to the last item of the deque.
     */
    private Node<Item> last;
    /**
     * deque size.
     */
    private int size = 0;

    /**
     * constructs an empty deque.
     */
    public Deque() {
    }

    /**
     * verifies if the deque is empty.
     *
     * @return true if it is empty and false if the deque is not empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return number of deque elements.
     */
    public int size() {
        return size;
    }

    /**
     * adds an item to the head of the deque.
     *
     * @param item
     * @throws NullPointerException
     *             for null item.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element");
        }
        Node<Item> temp = first;
        first = new Node<Item>(item);
        if (isEmpty()) {
            last = first;
        } else {
            first.next = temp;
        }
        size++;
    }

    /**
     * adds a item to the tail of the deque.
     *
     * @param item
     * @throws NullPointerException
     *             if the item = null.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element");
        }

        Node<Item> oldLast = last;
        last = new Node<Item>(item);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;

    }

    /**
     * removes an item from the deque's head.
     *
     * @return the removed item.
     * @throws NoSuchElementException
     *             if the deque is empty.
     */
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException("Empty Deque");
        }

        Node<Item> temp = first;
        first = temp.next;
        size--;

        return temp.it;

    }

    /**
     * removes an item from the tail of the deque.
     *
     * @return the removed item.
     * @throws NoSuchElementException
     *             if the deque is empty.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Deque");
        }

        Node<Item> removed = last;

        if (size() == 1) {
            first = null;
        } else {
            Node<Item> temp = first;
            while (temp.next != null) {
                if (temp.next.next == null) {
                    last = temp;
                    last.next = null;
                    break;
                }
                temp = temp.next;
            }
        }
        size--;
        return removed.it;
    }

    /**
     * @return iterator over deque items
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> current = (Node<Item>) Deque.this.first;
            private boolean hasNext;

            public boolean hasNext() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                if (current != null) {
                    hasNext = true;
                } else {
                    hasNext = false;
                }

                return hasNext;

            }

            public Item next() {
                if (hasNext) {
                    Node<Item> returned = current;
                    current = current.next;
                    hasNext = false;
                    return returned.it;
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
     * inner class that describes deque nodes.
     *
     * @author Julia.Denisova
     * @since 2/13/2015
     * @param <Item>
     */

    private class Node<Item> {
        /**
         * current item.
         */
        private Item it;
        /**
         * reference to the node with the next item.
         */
        private Node<Item> next;

        /**
         * constructs a node with current element.
         *
         * @param i
         *            current element of the node.
         */
        public Node(Item i) {
            it = i;
        }
    }

}
