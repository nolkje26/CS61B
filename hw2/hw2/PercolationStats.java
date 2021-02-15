package hw2;
import java.lang.Math;

public class PercolationStats {
    private int N;
    private int T;
    private double[] percolationThresholds;

        // perform T independent experiments on an N-by-N grid
        public PercolationStats(int N, int T, PercolationFactory pf) {
            if (N <= 0 || T <= 0) {
                throw new IllegalArgumentException();
            }
            this.N = N;
            this.T = T;

            this.percolationThresholds = new double[T];
            for (int xT = 0; xT < T ; xT++) {
                Percolation perc = pf.make(N);
                while (!perc.percolates()) {
                    int randomRow = (int)(Math.random() * (N - 1));
                    int randomCol = (int)(Math.random() * (N - 1));
                    perc.open(randomRow, randomCol);
                }
                double avg = ((double) perc.numberOfOpenSites()) / N;
                percolationThresholds[xT] = avg;
            }
        }

        // sample mean of percolation threshold
        public double mean() {
            return edu.princeton.cs.algs4.StdStats.mean(percolationThresholds);
        }

        // sample standard deviation of percolation threshold
        public double stddev() {
            return edu.princeton.cs.algs4.StdStats.stddev(percolationThresholds);
        }

        // low endpoint of 95% confidence interval
        public double confidenceLow() {
            return this.mean() - ((1.96 * this.stddev())/Math.sqrt(this.T));
        }

        // high endpoint of 95% confidence interval
        public double confidenceHigh() {
            return this.mean() + ((1.96 * this.stddev())/Math.sqrt(this.T));
        }
}
