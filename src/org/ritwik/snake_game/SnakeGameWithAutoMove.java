import java.util.*;
import java.util.concurrent.*;

/**
 * Backend implementation of Snake Game suitable for coding interviews.
 * Supports generic grid size and a predefined list of food positions.
 * Automatically moves in the last set direction at 1-second intervals.
 */
public class SnakeGameWithAutoMove {
    private final int width;
    private final int height;
    private final Deque<int[]> snake;
    private final Set<Integer> occupied;
    private final int[][] food;
    private int foodIndex;
    private int score;

    // Direction in which the snake will move automatically
    private volatile String currentDirection;
    private ScheduledExecutorService scheduler;

    /**
     * Initialize the game board.
     * 
     * @param width  Grid width
     * @param height Grid height
     * @param food   List of food positions as {row, col}
     */
    public SnakeGameWithAutoMove(int width, int height, int[][] food) {
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

        // Default movement direction
        this.currentDirection = "R";
    }

    /**
     * Change the snake's movement direction.
     * 
     * @param direction "U","D","L", or "R"
     */
    public void changeDirection(String direction) {
        if (!Arrays.asList("U", "D", "L", "R").contains(direction)) {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        this.currentDirection = direction;
    }

    /**
     * Starts automatic movement in the current direction every 1 second.
     */
    public void startAutoMove() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            int result = move(currentDirection);
            if (result < 0) {
                System.out.println("Game Over! Final Score: " + score);
                scheduler.shutdown();
            } else {
                System.out.println("Score: " + score);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Moves the snake by one unit in the current direction.
     * 
     * @param direction "U","D","L", or "R"
     * @return Current score after move, or -1 if game over
     */
    public int move(String direction) {
        int[] head = snake.peekFirst();
        int row = head[0], col = head[1];

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
        }

        if (row < 0 || row >= height || col < 0 || col >= width) {
            return -1;
        }

        int newHead = row * width + col;
        int[] tail = snake.peekLast();
        occupied.remove(tail[0] * width + tail[1]);

        if (occupied.contains(newHead)) {
            return -1;
        }

        snake.addFirst(new int[] { row, col });

        if (foodIndex < food.length && row == food[foodIndex][0] && col == food[foodIndex][1]) {
            score++;
            foodIndex++;
            occupied.add(tail[0] * width + tail[1]);
        } else {
            snake.pollLast();
        }

        occupied.add(newHead);
        return score;
    }

    // Example usage:
    public static void main(String[] args) {
        int[][] foodPositions = { { 1, 2 }, { 0, 1 }, { 2, 2 }, { 3, 2 }, { 4, 2 } };
        SnakeGameWithAutoMove game = new SnakeGameWithAutoMove(5, 5, foodPositions);
        game.changeDirection("R"); // start moving Up
        game.startAutoMove(); // will move every second

        // Optionally, simulate direction changes after some delay
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            game.changeDirection("D"); // turn Right after 5 seconds
        }, 5, TimeUnit.SECONDS);
    }
}
