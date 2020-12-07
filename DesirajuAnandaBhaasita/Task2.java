import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task2 {

  //Bottom up approach for Largest square area block
  //O(mn) time and O(n) extra space

  private void computeLargestAreaSquareBlock(int[][] grid, int minHeight, int rows, int cols) {
    int[] auxiliaryArray = new int[cols + 1];
    int maxArea = 0, prev = 0, x2 = 0, y2 = 0, maxSize = 0;
    for (int row = 1; row <= rows; row++) {
      for (int col = 1; col <= cols; col++) {
        int temp = auxiliaryArray[col];
        if (grid[row - 1][col - 1] >= minHeight) {
          auxiliaryArray[col] = Math.min(Math.min(auxiliaryArray[col - 1], prev), auxiliaryArray[col]) + 1;
          maxArea = Math.max(maxArea, auxiliaryArray[col]);
          if (maxSize < maxArea) {
            maxSize = maxArea;
            x2 = row;
            y2 = col;
          }
        }
        else {
          auxiliaryArray[col] = 0;
        }
        prev = temp;
      }
    }
    // find the maximum size of sub matrix and store the index
    int x1 = x2 - maxSize + 1;
    int y1 = y2 - maxSize + 1;
    System.out.println("Top left (x1, y1) and bottom right (x2, y2) co-ordinates of the square matrix are:");
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
    //Computing the largest area square block and printing the top left and bottom right coordinates
    // using bottom up approach
    computeLargestAreaSquareBlock(grid, minHeight, rows, columns);
  }
}
