import java.util.*;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;
    private V removedValue;
    private boolean keyExisted;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        Node(K k, V v, Node l, Node r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }
    }

    private Node put(K k, V v, Node n) {
        if (n == null) {
            size++;
            return new Node(k, v, null, null);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(k, v, n.left);
        } else if (cmp > 0) {
            n.right = put(k, v, n.right);
        } else {
            n.val = v;
        }
        return n;
    }

    private V get(K k, Node n) {
        if (n == null) return null;

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            return get(k, n.left);
        } else if (cmp > 0) {
            return get(k, n.right);
        } else {
            return n.val;
        }
    }

    private boolean containsKey(K k, Node n) {
        if (n == null) return false;

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            return containsKey(k, n.left);
        } else if (cmp > 0) {
            return containsKey(k, n.right);
        } else {
            return true;
        }
    }

    private Node remove(K k, Node n) {
        if (n == null) {
            return null;
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(k, n.left);
        } else if (cmp > 0) {
            n.right = remove(k, n.right);
        } else {
            removedValue = n.val;
            keyExisted = true;

            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            Node largest = largestNode(n.left);
            n.key = largest.key;
            n.val = largest.val;

            n.left = remove(largest.key, n.left);
        }
        return n;
    }

    private Node largestNode(Node n) {
        if (n == null) return null;

        Node cur = n;
        while (cur.right != null) {
            cur = cur.right;
        }

        return cur;
    }

    private void inorderKeys(Node n, Set<K> keys) {
        if (n == null) return;

        inorderKeys(n.left, keys);
        keys.add(n.key);
        inorderKeys(n.right, keys);
    }

    private class BSTMapIter implements Iterator<K> {
        private Deque<Node> stack;

        public BSTMapIter() {
            stack = new ArrayDeque<>();
            pushLeftSpine(root);
        }

        private void pushLeftSpine(Node n) {
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }


        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node current = stack.pop();
            K key = current.key;
            if (current.right != null) {
                pushLeftSpine(current.right);
            }
            return key;
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException();

        root = put(key, value, root);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException();

        return get(key, root);
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException();

        return containsKey(key, root);
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
        removedValue = null;
        keyExisted = false;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        inorderKeys(root, keys);
        return keys;
    }


    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException();

        removedValue = null;
        keyExisted = false;
        root = remove(key, root);
        if (keyExisted) {
            size--;
        }
        return removedValue;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() { return new BSTMapIter(); }

    public static void main(String[] args) {
        BSTMap<Integer, String> bstMap = new BSTMap();
        bstMap.put(5, "A");
        bstMap.put(3, "B");
        bstMap.put(4, "C");
        bstMap.put(2, "D");
        bstMap.put(7, "E");
        bstMap.put(8, "F");
        bstMap.put(4, "G");
        bstMap.put(6, "j");
        bstMap.put(1, "n");
        bstMap.put(-4, "H");
        bstMap.put(18, "H");
        bstMap.put(15, "H");
        bstMap.put(0, "H");
        bstMap.put(-4, "H");
        bstMap.put(-2, "H");
        bstMap.put(12, "H");

        for (Integer key : bstMap) {
            System.out.println(key);
        }
    }
}
