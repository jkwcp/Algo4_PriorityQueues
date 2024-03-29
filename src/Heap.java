

// Heap construction Uses <= 2N compares and exchanges
// Heapsort uses <= 2NlogN compares and exchanges
// In place and garantees NlogN time

// But not used very much
//  Inner loop longer than quicksorts'
//  Makes poor use of cache memory (looks far away down the tree)
//  Not stable (long distance exchanges)
public class Heap {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k < N) {
            int j = 2 * k;
            while (j < N && less(a, j, j + 1)) j++;

            if (!less(a, k, j)) break;

            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
