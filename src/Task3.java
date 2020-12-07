import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task3 {

  //Brute force approach to calculate the largest area rectangle block
  //O(m3n3) time and O(1) extra space

  private void computeLargestAreaRectBlock(int[][] matrix, int minHeight, int rows, int cols) {
    if (rows == 0) {
      return;
    }
    int maxArea = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0, maxSize = 0;

    for (int i = 0; i < cols; i++) {
      for (int i2 = i; i2 < cols; i2++) {
        for (int j = 0; j < cols; j++) {
          for (int j2 = j; j2 < cols; j2++) {
            if (checkBuildingHeight(matrix, j, j2, i, i2, minHeight)) {
              maxArea = Math.max(maxArea, calculateAreaOfRect(j, j2, i, i2));
              //if maxsize is less than max area, compute the coordinates
              if (maxSize < maxArea) {
                maxSize = maxArea;
                x1 = i + 1;
                y1 = j + 1;
                x2 = i2 + 1;
                y2 = j2 + 1;
              }
            }
          }
        }
      }
    }
    System.out.println("Top left (x1, y1) and bottom right (x2, y2) co-ordinates of the rectangular matrix are:");
    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
  }

  //compute area of the rectangle
  private int calculateAreaOfRect(int x1, int x2, int y1, int y2) {
    return (x2 - x1 + 1) * (y2 - y1 + 1);
  }

  //if building height is less than the min height, return false else return true
  private boolean checkBuildingHeight(int[][] grid, int x1, int x2, int y1, int y2, int minHeight) {

    for (int i = y1; i <= y2; i++) {
      for (int j = x1; j <= x2; j++) {
        if (grid[i][j] < minHeight) {
          return false;
        }
      }
    }
    return true;
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
    // using brute force
    computeLargestAreaRectBlock(grid, minHeight, rows, columns);
  }
}
