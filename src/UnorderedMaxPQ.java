import edu.princeton.cs.algs4.StdIn;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (max < i) max = 1;

        exch(max, N - 1);
        return pq[--N];
    }

    public int size() {
        return N;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<String> pq = new UnorderedMaxPQ<>(5);

        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            pq.insert(line);
            if (pq.size() > 4) {
                pq.delMax();
            }
        }
    }
}
