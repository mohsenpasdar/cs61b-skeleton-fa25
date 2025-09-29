import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B {
    private int size;
    private int nextFirst;
    private T[] items;

    public ArrayDeque61B() {
        size = 0;
        nextFirst = 0;
        items = (T[]) new Object[8];
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            a[i] = (T) get(i);
        }
        nextFirst = 2 * size - 1;
        items = a;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(Object x) {
        if (size == items.length) {
            resize(2 * size);
        }
        size++;
        items[nextFirst] = (T) x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(Object x) {
        if (size == items.length) {
            resize(2 * size);
        }
        size++;
        items[(nextFirst + size) % items.length] = (T) x;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.addLast((T) get(i));
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public Object removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        nextFirst = (nextFirst + 1) % items.length;
        T output = items[nextFirst];
        items[nextFirst] = null;
        return output;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public Object removeLast() {
        if (isEmpty()) {
            return null;
        }

        T output = items[(nextFirst + size) % items.length];
        items[(nextFirst + size) % items.length] = null;
        size--;
        return output;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public Object get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public Object getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }
}
