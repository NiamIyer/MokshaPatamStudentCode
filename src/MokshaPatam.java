
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
        if (!checkWin(boardsize, ladders, snakes)) {
            return -1;
        }
        bfs(boardsize,ladders,snakes,0);
        return 0;
    }
    public static boolean checkWin(int boardsize, int[][] ladders, int[][] snakes) {
        if (snakesInRow(snakes) == -1) {
            return true;
        }
        // Checks if there is a ladder which starts before the snakes that are in a row and ends after
        int beginning = snakesInRow(snakes);
        for (int i = 0; i < ladders.length; i++) {
            if (ladders[i][0] < beginning) {
                if (ladders[i][1] < beginning + 5) {
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
            if (snakeList[i] == snakeList[i+1] - 1) {
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

    public static int bfs(int boardsize, int[][] ladders, int[][] snakes, int counter) {
        return counter;
    }
}
