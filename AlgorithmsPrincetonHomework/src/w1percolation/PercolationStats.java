package w1percolation;


import edu.princeton.cs.introcs.StdRandom;

/**
 * @version 1.0
 * @author Julia.Denisova
 * @since 07-02-2015
 */
public class PercolationStats {
    /** Percolation object. */
    private Percolation p;
    /** number of tries. */
    private int times;
    /** size of the Percolation system. */
    private int size;
    /** open fractions on every try. */
    private double fractions[];

    /**
     * perform T independent experiments on an N-by-N grid. Measures T
     * percolation thresholds.
     * 
     * @param N
     *            dimension of the percolation system. System size is N*N. N >
     *            0.
     * @param T
     *            number of tries of the experiment. T > 0.
     * @throws IllegalArgumentException
     *             if either T or N <= 0.
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be > 0. T = "
                    + T + ", N = " + N);
        }
        times = T;
        size = N * N;
        fractions = new double[times];

        for (int i = 0; i < times; i++) {
            p = new Percolation(N);
            int k;
            int m;
            int countOpen = 0;
            while (!p.percolates()) {
                do {
                    k = StdRandom.uniform(0, N) + 1;
                    m = StdRandom.uniform(0, N) + 1;
                } while (p.isOpen(k, m));

                p.open(k, m);
                countOpen++;
            }
            fractions[i] = (double) countOpen / (double) size;
         }
    }

    /**
     * sample mean of percolation threshold
     * 
     * @return mean value
     */
    public double mean() {
        double fr = 0;
        if (size != 0) {
            for (int i = 0; i < fractions.length; i++) {
                fr += fractions[i];
            }
        }
        return fr / (double) times;
    }

    /**
     * sample standard deviation of percolation threshold *
     * 
     * @return standard deviation value
     */
    public double stddev() {
        if (times == 1) {
            return Double.NaN;
        }

        double d = 0;
        double mean = mean();
        if (size != 0) {
            for (int i = 0; i < fractions.length; i++) {
                d = (fractions[i] - mean) * (fractions[i] - mean);
            }
        }
        return d / (times - 1);

    }

    /**
     * calculates low endpoint of 95% confidence interval.
     * 
     * @return low endpoint value of 95% confidence interval
     */
    public double confidenceLo() {
        double mean = mean();
        double dev = stddev();
        return mean - (1.96 * dev / (Math.sqrt((double) times)));

    }

    /**
     * calculates high endpoint of 95% confidence interval
     * 
     * @return high endpoint value of 95% confidence interval
     */
    public double confidenceHi() {
        double mean = mean();
        double dev = stddev();
        return mean + (1.96 * dev / (Math.sqrt((double) times)));
    }

    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats pst = new PercolationStats(n, t);
        System.out.println("mean = " + pst.mean());
        System.out.println("stddev = " + pst.stddev());
        System.out.println("95% confidence interval: " + pst.confidenceLo()
                + ", " + pst.confidenceHi());

    }

}
