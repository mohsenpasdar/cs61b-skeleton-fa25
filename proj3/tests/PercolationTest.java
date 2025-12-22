import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    //   test weather converting x, y to an id works fine or not
    @Test
    public void encodeTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.encode(3, 4)).isEqualTo(19);
        assertThat(p.encode(0, 4)).isEqualTo(4);
        assertThat(p.encode(0, 0)).isEqualTo(0);
        assertThat(p.encode(4, 4)).isEqualTo(24);
        assertThat(p.encode(4, 1)).isEqualTo(21);
    }

    //   test weather decoding an id to its corresponding x and y works fine or not
    @Test
    public void decodeTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.decode(24)).asList().containsExactly(4, 4);
        assertThat(p.decode(5)).asList().containsExactly(1, 0);
        assertThat(p.decode(12)).asList().containsExactly(2, 2);
        assertThat(p.decode(16)).asList().containsExactly(3, 1);
    }

//    test open, isOpen, and numberOfOpenSites
    @Test
    public void opennessTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.numberOfOpenSites()).isEqualTo(0);
        assertThat(p.isOpen(2, 3)).isEqualTo(false);
        p.open(2, 3);
        assertThat(p.isOpen(2, 3)).isEqualTo(true);
        assertThat(p.numberOfOpenSites()).isEqualTo(1);

        assertThat(p.isOpen(4, 2)).isEqualTo(false);
        p.open(4, 2);
        assertThat(p.isOpen(4, 2)).isEqualTo(true);
        assertThat(p.numberOfOpenSites()).isEqualTo(2);

        p.open(4, 2);
        assertThat(p.isOpen(4, 2)).isEqualTo(true);
        assertThat(p.numberOfOpenSites()).isEqualTo(2);


    }

    //    assert weightedUF behavior
    @Test
    public void buildUFtest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.weightedUF.count()).isEqualTo(25);
    }


}