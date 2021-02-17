package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
        private int N;
        private int height;
        private int width;
        private int numOpenSites;
        private boolean[][] grid;
        private WeightedQuickUnionUF uf;
        private boolean isPercolated;

        // creates N-by-N grid, with all sites initially blocked
        public Percolation(int N) {
                this.N = N;
                this.height = N;
                this.width = N;
                this.numOpenSites = 0;
                this.grid = new boolean[width][height];
                for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                                grid[i][j] = false;
                        }
                }
                this.uf = new WeightedQuickUnionUF(N * N);
                this.isPercolated = false;
        }

        // helper function to convert (row, col) inputs to single integer
        private int xyTo1D (int row, int col) {
                return (row * N) + col;
        }

        // helper function to determine if site being accessed is w/in bounds
        public boolean isValidSite(int row, int col, int height, int width) {
                return row >= 0 && row < height && col >= 0 && col < width;
        }

        // helper function that connects just opened site to surrounding sites
        public void connect(int row, int col, int height, int width) {
                for (int dRow = -1; dRow <= 1; dRow++) {
                        if (dRow == 0) continue;
                        int newRow = row + dRow;
                        if (isValidSite(newRow, col, height, width)) {
                                if (isOpen(newRow, col)) {
                                        int newSite = xyTo1D(newRow, col);
                                        int currSite = xyTo1D(row, col);
                                        uf.union(newSite, currSite);
                                }
                        }
                }
                for (int dCol = -1; dCol <= 1; dCol++) {
                        if (dCol == 0) continue;
                        int newCol = col + dCol;
                        if (isValidSite(row, newCol, height, width)) {
                                if (isOpen(row, newCol)) {
                                        int newSite = xyTo1D(row, newCol);
                                        int currSite = xyTo1D(row, col);
                                        uf.union(newSite, currSite);
                                }
                        }
                }
        }


        // opens the site (row, col) if it is not open already
        public void open(int row, int col) {
                if (row >= this.height || col >= this.width) {
                        throw new IndexOutOfBoundsException();
                }
                if (row < 0 || col < 0) {
                        throw new IllegalArgumentException();
                }
                if (isOpen(row, col)) {
                        return;
                }
                grid[row][col] = true;
                connect(row, col, this.height, this.width);
                numOpenSites++;
                // Sets isPercolated to true if it's first site that percolates
                if (!this.isPercolated && row == this.width - 1 && isFull(row, col)) {
                        this.isPercolated = true;
                }
        }



        // is the site (row, col) open?
        public boolean isOpen(int row, int col) {
             return grid[row][col];
        }

        // is the site (row, col) full?
        public boolean isFull(int row, int col) {
                if (!isOpen(row, col)) { return false; }
                int currSite = xyTo1D(row, col);
                for (int i = 0; i < this.width; i++) {
                        if (uf.connected(i, currSite)) {
                                return true;
                        }
                }
                return false;
        }

        // number of open sites
        public int numberOfOpenSites() {
                return this.numOpenSites;
        }

        // does the system percolate?
        public boolean percolates() {
                return isPercolated;
        }
}
