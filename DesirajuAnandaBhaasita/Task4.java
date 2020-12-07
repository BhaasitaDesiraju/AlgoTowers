import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task4 {

  //DP bottom up approach to calculate the largest area rectangle block
  //O(mn) time and O(mn) extra space
  private void computeLargestAreaRectBlock(int[][] grid, int minHeight, int rows, int cols) {

    if (rows == 0) {
      return;
    }
    int maxarea = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0, maxSize = 0;
    int[][] dpArray = new int[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (grid[row][col] >= minHeight) {

          // compute the maximum width and update dp with it
          dpArray[row][col] = col == 0 ? 1 : dpArray[row][col - 1] + 1;

          int width = dpArray[row][col];

          // compute the maximum area rectangle with a lower right corner at [row, col]
          for (int k = row; k >= 0; k--) {
            width = Math.min(width, dpArray[k][col]);
            maxarea = Math.max(maxarea, width * (row - k + 1));
            //if maxsize is less than max area, compute the coordinates
            if (maxSize < maxarea) {
              maxSize = maxarea;
              x2 = row + 1;
              y2 = col + 1;
              x1 = x2 - (row - k + 1) + 1;
              y1 = y2 - width + 1;
            }
          }
        }
      }
    }
    System.out.println("Top left (x1, y1) and bottom right (x2, y2) co-ordinates of the rectangular matrix are:");
    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
  }

  //Read input from stdin and store it in a 2D array
  public void execute() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String firstLine = reader.readLine();
    Scanner scanner = new Scanner(firstLine);

    //number of rows
    int rows = scanner.nextInt();

    //number of columns
    int columns = scanner.nextInt();

    //min building height allowed
    int minHeight = scanner.nextInt();

    int[][] grid = new int[rows][columns];

    //filling the grid
    for (int i = 0; i < rows; i++) {
      String nextLine = reader.readLine();
      scanner = new Scanner(nextLine);
      for (int j = 0; j < columns; j++) {
        grid[i][j] = scanner.nextInt();
      }
    }
    //Computing the largest area rectangle block and printing the top left and bottom right coordinates
    // using DP bottom up approach
    computeLargestAreaRectBlock(grid, minHeight, rows, columns);
  }
}
