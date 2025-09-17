import edu.princeton.cs.algs4.In;
import javassist.runtime.Inner;

public class Stack2 {
    private static class SLList {
        private static class IntNode {
            private final int item;
            private IntNode next;
            public IntNode(int item, IntNode next) {
                this.item = item;
                this.next = next;
            }
        }

        private int size;
        private final IntNode sentinel = new IntNode(63, null);;

        public SLList() {
            size = 0;
        }

        public SLList(int x) {
            size = 1;
            sentinel.next = new IntNode(x, null);
        }

        public void addFirst(int x) {
            size++;
            sentinel.next = new IntNode(x, sentinel.next);
        }

        public int removeFirst() {
            size--;
            int item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            return item;
        }

        public int sum() {
            IntNode current = sentinel.next;
            return helper(sentinel.next);
        }

        private static int helper(IntNode current) {
            if (current == null) return 0;
            return current.item + helper(current.next);
        }
    }

    private SLList linkedList;

    public Stack2() {
        linkedList = new SLList();
    }

    public void push(int x) {
        linkedList.addFirst(x);
    }

    public int pop() {
        return linkedList.removeFirst();
    }

    public int size() {
        return linkedList.size;
    }

    public int sum() {
        return linkedList.sum();
    }


}

