import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    int N;
    boolean[] openness;
    WeightedQuickUnionUF weightedUF;
    //create a new disjoint set to address the problem with backwash
    WeightedQuickUnionUF weightedUF2;
    int openSites;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        openness = new boolean[N * N];
        weightedUF = new WeightedQuickUnionUF(N * N + 2);
        weightedUF2 = new WeightedQuickUnionUF(N * N + 1);
        openSites = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        if (isOpen(row, col)) return;
        int id = encode(row, col);
        openness[id] = true;
        openSites++;

        // connect the current cell to its neighbors:
        if (col > 0 && isOpen(row, col - 1)) {
            int leftNeighbor = encode(row, col - 1);
            weightedUF.union(id, leftNeighbor);
            weightedUF2.union(id, leftNeighbor);
        }

        if (col < N - 1 && isOpen(row, col + 1)) {
            int rightNeighbor = encode(row, col + 1);
            weightedUF.union(id, rightNeighbor);
            weightedUF2.union(id, rightNeighbor);
        }

        if (row > 0 && isOpen(row - 1, col)) {
            int topNeighbor = encode(row - 1, col);
            weightedUF.union(id, topNeighbor);
            weightedUF2.union(id, topNeighbor);
        }

        if (row < N - 1 && isOpen(row + 1, col)) {
            int bottomNeighbor = encode(row + 1, col);
            weightedUF.union(id, bottomNeighbor);
            weightedUF2.union(id, bottomNeighbor);
        }

        // connect the top row with the virtual top site
        if (row == 0) {
            weightedUF.union(id, N * N);
            weightedUF2.union(id, N * N);
        }

        // connect the bottom row with the virtual bottom site
        if (row == N - 1) {
            weightedUF.union(id, N * N + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        int id = encode(row, col);
        return openness[id];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        if (!isOpen(row, col)) return false;
        int id = encode(row, col);

        return weightedUF2.connected(id, N * N);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return weightedUF.connected(N * N, N * N + 1);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row/col out of bounds");
        }
    }

    int encode(int x, int y) {
        return x * N + y;
    }

    int[] decode(int id) {
        int row = id / N;
        int col = id % N;
        return new int[] {row, col};
    }
}
