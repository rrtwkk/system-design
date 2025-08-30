import java.util.*;

/**
 * Backend implementation of Snake Game suitable for coding interviews.
 * Supports generic grid size and a predefined list of food positions.
 * move() returns the current score or -1 if the game is over.
 */
public class SnakeGame {
    private final int width;
    private final int height;
    private final Deque<int[]> snake;
    private final Set<Integer> occupied;
    private final int[][] food;
    private int foodIndex;
    private int score;

    /**
     * Initialize the game board.
     * 
     * @param width  Grid width
     * @param height Grid height
     * @param food   List of food positions as {row, col}
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;
        this.score = 0;
        this.snake = new LinkedList<>();
        this.occupied = new HashSet<>();

        // Snake starts at top-left corner (0,0)
        snake.add(new int[] { 0, 0 });
        occupied.add(0);
    }

    /**
     * Moves the snake by one unit in the given direction.
     * 
     * @param direction 'U','D','L', or 'R'
     * @return Current score after move, or -1 if game over
     */
    public int move(String direction) {
        // Current head position
        int[] head = snake.peekFirst();
        int row = head[0];
        int col = head[1];

        // Compute new head position
        switch (direction) {
            case "U":
                row--;
                break;
            case "D":
                row++;
                break;
            case "L":
                col--;
                break;
            case "R":
                col++;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        // Check wall collisions
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IllegalArgumentException("Game Over, your highest score:" + score);
        }

        int newHead = row * width + col;

        // Remove tail from occupied set (it moves unless we eat food)
        int[] tail = snake.peekLast();
        occupied.remove(tail[0] * width + tail[1]);

        // Check self-collision
        if (occupied.contains(newHead)) {
            throw new IllegalArgumentException("Game Over, your highest score:" + score);
        }

        // Add new head
        snake.addFirst(new int[] { row, col });

        // Check food consumption
        if (foodIndex < food.length && row == food[foodIndex][0] && col == food[foodIndex][1]) {
            score++;
            foodIndex++;
            // Grow: put tail back into occupied set
            occupied.add(tail[0] * width + tail[1]);
        } else {
            // Normal move: remove tail from snake deque
            snake.pollLast();
        }

        occupied.add(newHead);
        return score;
    }

    // Example usage:
    public static void main(String[] args) {
        int[][] foodPositions = { { 1, 2 }, { 0, 1 } };
        SnakeGame game = new SnakeGame(3, 3, foodPositions);
        System.out.println(game.move("R")); // 0
        System.out.println(game.move("D")); // 0
        System.out.println(game.move("R")); // 1 (eat food at [1,2])
        System.out.println(game.move("U")); // 1
        System.out.println(game.move("L")); // 2 (eat food at [0,1])
        System.out.println(game.move("U")); // -1 (hits wall)
    }
}
