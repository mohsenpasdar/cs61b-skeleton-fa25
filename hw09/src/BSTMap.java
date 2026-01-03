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
        if (n == null) return null;

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(k, n.left);
        } else if (cmp > 0) {
            n.right = remove(k, n.right);
        } else {
            if (n.left == null && n.right == null) {
                return null;
            } else if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else {
                Node largest = largestNode(n.left);
                n.key = largest.key;
                n.val = largest.val;
                n.left = remove(largest.key, n.left);
            }
        }
        return n;
    }

    private Node largestNode(Node n) {
        Node cur = n;
        while (cur.right != null) {
            cur = cur.right;
        }

        return cur;
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
        return get(key, root);
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
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
        V val = get(key);
        if (val == null) return null;
        root = remove(key, root);
        size--;
        return val;
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
        BSTMap<Integer, String> bstMap = new BSTMap();
        bstMap.put(5, "A");
        bstMap.put(3, "B");
        bstMap.put(4, "C");
        bstMap.put(2, "D");
        bstMap.put(7, "E");
        bstMap.put(8, "F");
        bstMap.put(4, "G");
        bstMap.put(8, "H");
        System.out.println(bstMap.size());
        System.out.println(bstMap.remove(5));
        System.out.println(bstMap.containsKey(5));
        System.out.println(bstMap.size());
    }
}
