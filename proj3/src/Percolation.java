import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    int N;
    int[][] grid;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.N = N;
    }

    int encode(int x, int y) {
        return x * N + y;
    }

    int[] decode(int id) {
        int row = id / N;
        int col = id % N;
        return new int[] {row, col};
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return 0;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
