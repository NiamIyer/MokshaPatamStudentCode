import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [Niam]
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        if (!checkWin(ladders, snakes)) {
            return -1;
        }
        // Creates a board which only contain the snakes and ladders
        int[] board = new int[boardsize + 1];
        for (int i = 0; i < boardsize + 1; i++) {
            board[i] = i;
        }
        for (int i = 0; i < ladders.length; i ++) {
            board[ladders[i][0]] = ladders[i][1];
        }
        for (int i = 0; i < snakes.length; i ++) {
            board[snakes[i][0]] = snakes[i][1];
        }
        // Creates a queue for the bfs
        // Creates more variables to assist with bfs
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(board[1]);
        int counter = 0;
        boolean[] visited = new boolean[boardsize + 1];
        visited[1] = true;
        visited[board[1]] = true;
        // For each level
        while (!queue.isEmpty()) {
            int level = queue.size();
            // For each element in each level
            for (int i = 0; i < level; i++) {
                // Saves the top of the queue
                int square = queue.poll();
                // For every possible dice roll
                for (int j = 1; j < 7; j++) {
                    // If the roll is in bounds
                    if (square + j <= boardsize) {
                        // Return if it is exact
                        if (board[square + j] == boardsize) {
                            return counter + 1;
                        }
                        // If the tile isn't visited
                        if (visited[board[square + j]] == false) {
                            // Add the tile to the queue and count it as visited
                            queue.add(board[square + j]);
                            visited[square + j] = true;
                        }
                    }
                }
            }
            // Increment the counter (goes to next level)
            counter ++;
        }
        return -1;
    }
    public static boolean checkWin(int[][] ladders, int[][] snakes) {
        if (snakesInRow(snakes) == -1) {
            return true;
        }
        // Checks if there is a ladder which starts before the snakes that are in a row and ends after
        int beginning = snakesInRow(snakes);
        for (int i = 0; i < ladders.length; i++) {
            if (ladders[i][0] < beginning) {
                if (ladders[i][1] > beginning + 5) {
                    return true;
                }
            }
        }
        return false;
    }
    public static int snakesInRow(int[][] snakes) {
        // Sorts the list of snakes to make sure they're in ascending order
        int [] snakeList = new int[snakes.length];
        for (int i = 0; i < snakes.length; i++) {
            snakeList[i] = snakes[i][0];
        }
        sort(snakeList);
        // Checks if there are 6 snakes in a row
        int counter = 0;
        for (int i = 0; i < snakeList.length - 1; i++) {
            if (snakeList[i] + 1 == snakeList[i+1]) {
                counter ++;
            }
        }
        // If it is possible to not need a ladder and win, return -1
        if (counter < 6) {
            return -1;
        }
        // If not, return the first coordinate
        return snakes[0][0];
    }

    public static void sort (int[] array) {
        // Bubble sort
        boolean isSwapped;
        // For every number
        for (int i = 0; i < array.length; i++) {
            isSwapped = false;
            // For every number not locked in
            for (int j = 0; j < array.length - 1 - i; j++) {
                // checks and swaps if necessary
                if (array[j] > array[j+1]) {
                    int num = array[j];
                    array[j] = array[j+1];
                    array[j+1] = num;
                    isSwapped = true;
                }
            }
            // If nothing was swapped, it's sorted
            if (isSwapped == false) {
                break;
            }
        }
    }
}
