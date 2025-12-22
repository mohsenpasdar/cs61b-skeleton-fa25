import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    int N;
    boolean[] openness;
    WeightedQuickUnionUF weightedUF;
    int openSites;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.N = N;
        openness = new boolean[N * N];
        weightedUF = new WeightedQuickUnionUF(N * N);
        openSites = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (isOpen(row, col) || numberOfOpenSites() >= 25) return;
        int id = encode(row, col);
        openness[id] = true;
        openSites++;

        // connect the current cell to its neighbors:
        if (col > 0 && isOpen(row, col - 1)) {
            int leftNeighbor = encode(row, col - 1);
            weightedUF.union(id, leftNeighbor);
        }

        if (col < N - 1 && isOpen(row, col + 1)) {
            int rightNeighbor = encode(row, col + 1);
            weightedUF.union(id, rightNeighbor);
        }

        if (row > 0 && isOpen(row - 1, col)) {
            int topNeighbor = encode(row - 1, col);
            weightedUF.union(id, topNeighbor);
        }

        if (row < N - 1 && isOpen(row + 1, col)) {
            int bottomNeighbor = encode(row + 1, col);
            weightedUF.union(id, bottomNeighbor);
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        int id = encode(row, col);
        return openness[id];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (!isOpen(row, col)) return false;
        int id = encode(row, col);
        for (int j = 0; j < N; j++) {
            int topRowId = encode(0, j);
            if (weightedUF.connected(id, topRowId)) return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int j = 0; j < N; j++) {
            if (isFull(N - 1, j)) return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    int encode(int x, int y) {
        return x * N + y;
    }

    int[] decode(int id) {
        int row = id / N;
        int col = id % N;
        return new int[] {row, col};
    }

    public static void main(String[] args) {
        int N = 5;
        Percolation p = new Percolation(N);
        System.out.println(p.weightedUF.find(0));
        System.out.println(p.weightedUF.find(9));
        System.out.println(p.weightedUF.find(15));
    }

}
