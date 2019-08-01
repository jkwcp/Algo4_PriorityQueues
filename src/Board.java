
import edu.princeton.cs.algs4.Stack;

public class Board {
    private final int size;
    private final int[][] board;
    private final int hamming;
    private final int manhattan;
    private final int blankTileY;
    private final int blankTileX;


    // Assume
    // receives n-by-n array has n^2 integers between 0 and n^2 - 1
    // n >= 2 && n < 128
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();

        size = tiles.length;
        board = new int[size][size];
        int hammingTemp = 0;
        int manhattanTemp = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = tiles[i][j];
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                } else if (board[i][j] != correctValue(i, j)) {
                    hammingTemp++;

                    int correctRow = (board[i][j] - 1) / size;
                    int correctColumn = (board[i][j] - 1) % size;

                    manhattanTemp += absolute(correctRow, i) + absolute(correctColumn, j);
                }
            }
        }

        hamming = hammingTemp;
        manhattan = manhattanTemp;
        blankTileX = x;
        blankTileY = y;
    }

    // returns a string composed of n + 1 lines,
    // first line: size n
    // rest lines: n-by-n grid of tiles in row major order, 0 for blank square
    public String toString() {
        String lineOne = String.valueOf(size);
        StringBuilder lineTwo = new StringBuilder();
        for (int i = 0; i < size; i++) {
            lineTwo.append("\n");
            for (int j = 0; j < size; j++) {
                lineTwo.append(" ").append(board[i][j]);
            }
        }
        return lineOne + lineTwo;
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of position
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    private int correctValue(int i, int j) {
        return i * size + j + 1;
    }


    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;

        Board target = (Board) y;
        if (target.size != this.size) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (target.board[i][j] != this.board[i][j]) return false;
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();

        if (blankTileX > 0)
            neighbors.push(replaceForNewBoard(blankTileX - 1, blankTileY));
        if (blankTileX < dimension() - 1)
            neighbors.push(replaceForNewBoard(blankTileX + 1, blankTileY));
        if (blankTileY > 0)
            neighbors.push(replaceForNewBoard(blankTileX, blankTileY - 1));
        if (blankTileY < dimension() - 1)
            neighbors.push(replaceForNewBoard(blankTileX, blankTileY + 1));

        return neighbors;
    }

    private Board replaceForNewBoard(int i, int j) {
        int[][] tiles = copyArray(board, size);
        exchItemsInArray(tiles, blankTileX, blankTileY, i, j);
        return new Board(tiles);
    }

   private int[][] copyArray(int[][] tiles, int n) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               array[i][j] = tiles[i][j];
           }
        }
        return array;
   }

   private int absolute(int i, int j) {
        return (i >= j) ? i - j : j - i;
   }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int i = 0, j = 0;
        int[][] tiles = copyArray(board, size);

        if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
            exchItemsInArray(tiles, i, j, i, j + 1);
        } else {
            exchItemsInArray(tiles, i + 1, j, i + 1, j + 1);
        }

        return new Board(tiles);
    }

    private void exchItemsInArray(int[][]a, int i, int j, int n, int m) {
        int temp = a[i][j];
        a[i][j] = a[n][m];
        a[n][m] = temp;
    }

    public static void main(String[] args) {
        int size = 3;
        int[][] tiles = new int[size][size];
        tiles[0][0] = 0;
        tiles[0][1] = 5;
        tiles[0][2] = 4;
        tiles[1][0] = 2;
        tiles[1][1] = 8;
        tiles[1][2] = 7;
        tiles[2][0] = 3;
        tiles[2][1] = 1;
        tiles[2][2] = 6;

        Board b = new Board(tiles);


        System.out.println(b.toString());
        for (Board e : b.neighbors()) {
            System.out.println(e.toString());
        }


    }


}
