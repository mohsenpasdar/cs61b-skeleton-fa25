public class Percolation {
    // TODO: Add any necessary instance variables.
    int N;
    boolean[] openness;
    int openSites;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.N = N;
        openness = new boolean[N * N - 1];
        openSites = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        int id = encode(row, col);
        openness[id] = true;
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        int id = encode(row, col);
        return openness[id];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
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

}
