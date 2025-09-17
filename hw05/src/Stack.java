import edu.princeton.cs.algs4.In;
import javassist.runtime.Inner;

public class Stack {
    private class IntNode {
        private final int item;
        private IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private final IntNode sentinel;
    private int size;

    public Stack() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public void push(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }

    public int pop() {
        int topItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return topItem;
    }

    public int size() {
        return size;
    }

    public static int helper(IntNode current) {
        if (current == null) return 0;
        return current.item + helper(current.next);
    }

    public int sum() {
        return helper(sentinel.next);
    }
}

