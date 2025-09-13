public class IntList {
    int first;
    IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. Must use recursion.
     *
     * This method is non-destructive, i.e. it must not modify the original list.
     */
    public static IntList incrRecursiveNondestructive(IntList L, int x) {
        // TODO: Fill in this code
        IntList output = new IntList(L.first + x, null);
        if (L.rest == null) return output;
        output.rest = incrRecursiveNondestructive(L.rest, x);
        return output;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrRecursiveDestructive(IntList L, int x) {
        // TODO: Fill in this code
        L.first = L.first + x;
        if (L.rest == null) return L;
        incrRecursiveDestructive(L.rest, x);
        return L;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. May not modify the original list.
     */
    public static IntList incrIterativeNondestructive(IntList L, int x) {
        // TODO: Fill in this code
        IntList output = new IntList(L.first + x, null);

        IntList currentOriginal = L.rest;
        IntList currentNew = output;
        while (currentOriginal != null) {
            currentNew.rest = new IntList(currentOriginal.first + x, null);
            currentOriginal = currentOriginal.rest;
            currentNew = currentNew.rest;
        }
        return output;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrIterativeDestructive(IntList L, int x) {
        // TODO: Fill in this code
        L.first += x;
        IntList current = L.rest;
        while (current != null) {
            current.first += x;
            current = current.rest;
        }
        return L;
    }

    /**
     * Returns an IntList consisting of the elements of L1 followed by the
     * elements of L2.
     */
    public static IntList concatenate(IntList L1, IntList L2) {
        // TODO: Fill in this code
        IntList current = L1;
        while (current.rest != null) {
            current = current.rest;
        }
        current.rest = L2;
        return L1;
    }

    /*
     * =================================================================
     * OPTIONAL METHODS
     * =================================================================
     */

    /**
     * Returns the sum of all elements in the IntList.
     */
    public int sum() {
        // Optional: Fill in this code
        return 0;
    }

    /**
     * Destructively adds x to the end of the list.
     */
    public void addLast(int x) {
        // Optional: Fill in this code
    }

    /**
     * Destructively adds x to the front of this IntList.
     * This is a bit tricky to implement. The standard way to do this would be
     * to return a new IntList, but for practice, this implementation should
     * be destructive.
     */
    public void addFirst(int x) {
        // Optional: Fill in this code
    }
}
