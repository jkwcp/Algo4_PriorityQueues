// A binary heap implementation

// Based on a complete binary tree
//  Binary tree: empty or node with links to left and right binary trees
//  Complete tree: perfectly balanced, except for bottom level
//  Property: Height of complete tree with N nodes is lgN,

// Binary Heap: Array representation of a heap-ordered complete binary tree
// Heap ordered binary tree:
//  Keys in nodes
//  Parents' key no smaller than children's keys
// Array representation:
//  Indices start at 1;
//  Take nodes in level order
//  No explicit links needed


// Largest key is a[1], root of the tree
// Parent of node  k is at k/2
// Children of node k is 2k or 2k + 1

// insert logN, del max: logN

// Immutability of Keys:
// Assumption: client does not change keys while they are on the PQ
// Best practice: use immutable keys

// Underflow and overflow:
// Underflow: throw exception if deleting from empty PQ
// Overflow: add no-arg constructor and use resizing array


public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // Add node at end, then swim it up
    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    // Child's key becomes larger than its parents' key
    // Exchange key in child with key in parent, until heap order restored
    // Peter principle: node promoted to level of incompetence
    // move up
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }


    // Exchange root with node at end, then sink it down
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }
    // Parent's key becomes smaller than one(or both) of its children's
    // Exchange key in parent with key in larger child, repeat until heap order restored
    // Power struggle: better subordinate promoted
    // Move down
    private void sink(int k) {
        while (2 * k  < N) {
            int j = 2 * k;
            if (j < N && less(j, j - 1))
                j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


}
