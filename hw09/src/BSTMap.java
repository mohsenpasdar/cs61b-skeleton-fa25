import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

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

        public Node put(K k, V v, Node n) {
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

        public V get(K k, Node n) {
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
        if (root != null) {
            root.put(key, value, root);
        } else {
            root = new Node(key, value, null, null);
            size++;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (root != null) {
            return root.get(key, root);
        } else {
            return null;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return false;
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

    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Map61B bstMap = new BSTMap();
        bstMap.put(5, "A");
        bstMap.put(3, "B");
        bstMap.put(4, "C");
        bstMap.put(2, "D");
        bstMap.put(7, "E");
        bstMap.put(8, "F");
        bstMap.put(4, "G");
        bstMap.put(8, "H");
        System.out.println(bstMap.size());

        System.out.println(bstMap.get(0));
    }
}
