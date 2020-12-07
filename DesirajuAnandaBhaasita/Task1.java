import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task1 {

  //Compute largest area square block using recursion and memoization
  //O(mn) time and O(mn) extra space

  private void computeLargestAreaSquareBlock(int[][] grid, int minHeight, int rows, int cols) {

    int[][] auxiliaryArray = new int[rows][cols];

    // copy the first row
    for (int i = 0; i < cols; i++) {
      //if value in the grid matrix is greater than or equal to min height replace it with 1
      if (grid[0][i] >= minHeight) {
        auxiliaryArray[0][i] = 1;
      }
      //else replace it with 0
      else {
        auxiliaryArray[0][i] = 0;
      }
    }

    // copy the first column
    for (int i = 0; i < rows; i++) {
      //if value in the grid matrix is greater than or equal to min height replace it with 1
      if (grid[i][0] >= minHeight) {
        auxiliaryArray[i][0] = 1;
      }
      //else replace it with 0
      else {
        auxiliaryArray[i][0] = 0;
      }
    }

    // for rest of the matrix update the auxiliaryArray starting from row 1 and col 1
    updateAuxiliaryArray(grid, minHeight, auxiliaryArray, 1, 1, rows, cols);

    // find the maximum size of auxiliary matrix and compute x1,y1 and x2, y2
    int maxSize = 0;
    int x2 = 0, y2 = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (auxiliaryArray[i][j] > maxSize) {
          maxSize = auxiliaryArray[i][j];
          x2 = i;
          y2 = j;
        }
      }
    }
    //adjust indices to base 1
    x2 = x2 + 1;
    y2 = y2 + 1;
    //compute x1 and y1 from x2, y2
    int x1 = x2 - maxSize + 1;
    int y1 = y2 - maxSize + 1;
    System.out.println("Top left (x1, y1) and bottom right (x2, y2) co-ordinates of the square matrix are:");
    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
  }

  //Recursively updating the auxiliary array
  private void updateAuxiliaryArray(int[][] grid, int minHeight, int[][] auxiliaryArray, int row, int col, int rows, int cols) {
  //base case - if row+1 is greater than total rows, return
   if (row + 1 > rows) {
      return;
    }
   //else loop through each column and update the column values of the auxiliary array
    for (int j = col; j < cols && row < rows; j++) {
      if (grid[row][j] >= minHeight) {
        auxiliaryArray[row][j] = Math.min(auxiliaryArray[row - 1][j - 1], Math.min(auxiliaryArray[row][j - 1], auxiliaryArray[row - 1][j])) + 1;
      }
      else {
        auxiliaryArray[row][j] = 0;
      }
    }
    //at the end of each column, start updating the next row from the 1st column
    updateAuxiliaryArray(grid, minHeight, auxiliaryArray, row + 1, 1, rows, cols);
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
    //computing the largest area square block and printing the top left and bottom right coordinates
    computeLargestAreaSquareBlock(grid, minHeight, rows, columns);
  }
}
