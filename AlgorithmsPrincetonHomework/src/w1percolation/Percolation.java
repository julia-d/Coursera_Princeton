package w1percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @version 1.0
 * @author Julia.Denisova
 * @since 07-02-2015
 *
 */
public class Percolation {
    /**
     * A 2D array of boolean values for percolation structure. true = percolated
     * site, false = not percolated site.
     */
    private boolean[][] grid;
    /**
     * UnionFind object with information about connected sites.
     */
    private WeightedQuickUnionUF uf;

    /**
     * Creates a new percolation system of NxN size.
     * 
     * @param N
     *            size of height and width of the percolation system
     * @throws IllegalArgumentException
     *             if N < 0.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be > 0");
        }
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    /**
     * Opens site (row i, column j) if it is not open already.
     * 
     * @param i
     *            row index, 0 < i <= percolation system size.
     * @param j
     *            column index, 0 < j <= percolation system size.
     * @throws OutOfBoundsException
     *             for i, j < 0 and i,j > percolation system size.
     */
    public void open(int i, int j) {
        checkIJ(i, j);

        if (grid[i - 1][j - 1]) {
            return;
        }

        grid[i - 1][j - 1] = true;
        int thisIndex = countIndex(i, j);
        int thatIndex;

        if (i == 1) {
            uf.union(0, thisIndex);
        } else if (isOpen(i - 1, j)) {
            thatIndex = countIndex(i - 1, j);
            uf.union(thisIndex, thatIndex);
        }

        if (j != 1) {
            if (isOpen(i, grid.length - 1)) {
                thatIndex = countIndex(i, grid.length - 1);
                uf.union(thisIndex, thatIndex);
            }
        }

        if (j != grid.length) {
            if (isOpen(i, j + 1)) {
                thatIndex = countIndex(i, j + 1);
                uf.union(thisIndex, thatIndex);
            }
        }

        if (i == grid.length) {
            uf.union(thisIndex, grid.length + 1);
        } else if (isOpen(i + 1, j)) {
            thatIndex = countIndex(i + 1, j);
            uf.union(thisIndex, thatIndex);
        }
    }

    /**
     * verifies if the site (row i, column j) open.
     * 
     * @param i
     *            row index, 0 < i <= percolation system size.
     * @param j
     *            column index, 0 < j <= percolation system size.
     * @return true if the site is open and false if the site is not open
     * @throws IndexOutOfBoundsException
     *             for i, j < 0 and i,j > percolation system size.
     */
    public boolean isOpen(int i, int j) {
        checkIJ(i, j);
        return grid[i - 1][j - 1];
    }

    /**
     * verifies if the site is full (if the site (row i, column j) is connected
     * to the top of the system.
     * 
     * @param i
     *            row index, 0 < i <= percolation system size.
     * @param j
     *            column index, 0 < j <= percolation system size.
     * @return true if the site is full (connected) and false if the site is not
     *         full(not connected).
     * @throws IndexOutOfBoundsException
     *             for i, j < 0 and i,j > percolation system size.
     */
    public boolean isFull(int i, int j) {
        checkIJ(i, j);
        int thisIndex = countIndex(i, j);
        return uf.connected(0, thisIndex);
    }

    /**
     * verifies if the system percolates (if the top is connected to the bottom
     * through sites.
     * 
     * @return true if the system percolates and false if it doesn't.
     */
    public boolean percolates() {
        return uf.connected(0, grid.length - 1);
    }

    /**
     * counts an index for the
     * 
     * @param i
     *            row index, 0 < i <= percolation system size.
     * @param j
     *            column index, 0 < j <= percolation system size.
     * @return index
     * @throws IndexOutOfBoundsException
     *             for i, j < 0 and i,j > percolation system size.
     * @return
     */
    private int countIndex(int i, int j) {
        checkIJ(i, j);
        return (i - 1) * grid.length + j;
    }

    /**
     * private method with verification row and column indices for bounds.
     * 
     * @param i
     *            row index, 0 < i <= percolation system size.
     * @param j
     *            column index, 0 < j <= percolation system size.
     * @throws IndexOutOfBoundsException
     *             for i, j < 0 and i,j > percolation system size.
     */
    private void checkIJ(int i, int j) {
        int size = grid.length * grid.length;
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException(
                    " i and j should be between 1 and " + size + ". (i, j) = ("
                            + i + ", " + j + ").");
        }
    }
    public static void main(String[] args) {
        System.out.println("a"+"\u0000"+"b");
    }

}
