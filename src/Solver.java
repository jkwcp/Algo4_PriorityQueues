import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private SearchNode solution;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial));

        MinPQ<SearchNode> pqTwin = new MinPQ<>();
        pqTwin.insert(new SearchNode(initial.twin()));

        while (true) {
            solution = playBoard(pq);
            if (solution != null || playBoard(pqTwin) != null)
                return;
        }

    }

    private SearchNode playBoard(MinPQ<SearchNode> pq) {
        if (pq.isEmpty()) return null;

        SearchNode min = pq.delMin();

        if (goal(min)) return min;

        for (Board board: min.gameBoard.neighbors()) {
            if (min.prev == null || !board.equals(min.prev.gameBoard)) {
                pq.insert(new SearchNode(board, min));
            }
        }
        return null;
    }

    public boolean isSolvable() {
        return solution != null;
    }

    public int moves() {
        return isSolvable() ? solution.steps : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solutions = new Stack<>();
        for (SearchNode pointer = solution; pointer != null; pointer = pointer.prev) {
            solutions.push(pointer.gameBoard);
        }
        return solutions;
    }

    private boolean goal(SearchNode searchNode) {
        return searchNode.gameBoard.isGoal();
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final int steps;
        private final Board gameBoard;
        private final SearchNode prev;
        private final int manhattanPriority;

        private SearchNode(Board gameBoard) {
            this.gameBoard = gameBoard;
            this.steps = 0;
            this.manhattanPriority = gameBoard.manhattan() + steps;
            this.prev = null;
        }

        private SearchNode(Board gameBoard, SearchNode prev) {
            this.gameBoard = gameBoard;
            this.steps = prev.steps + 1;
            this.manhattanPriority = gameBoard.manhattan() + steps;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return manhattanPriority - that.manhattanPriority;
        }
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


}
