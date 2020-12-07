import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Task5 {

  //DP bottom up approach to calculate the largest area rectangle block
  //O(mn) time and O(n) extra space
  private void computeLargestAreaRectBlock(int[][] grid, int minHeight, int rows, int cols) {
    if (rows == 0) {
      return;
    }

    // initialize left as the leftmost boundary possible with zeroes
    int[] left = new int[cols];
    int[] right = new int[cols];
    int[] height = new int[cols];

    // initialize right as the rightmost boundary possible and fill with column size
    Arrays.fill(right, cols);

    int maxarea = 0, maxSize = 0, x1=0, x2=0, y1=0, y2=0;
    for (int i = 0; i < rows; i++) {
      int curLeft = 0, curRight = cols;

      // update height array
      for (int j = 0; j < cols; j++) {
        //if element in grid is greater than or equal to min height,
        // increment the corresponding element in height by 1
        if (grid[i][j] >= minHeight) {
          height[j]++;
        }
        //else height remains 0
        else {
          height[j] = 0;
        }
      }
      // update left array
      for (int j = 0; j < cols; j++) {
        //if element in grid is greater than or equal to min height,
        // corresponding element's value will be max of left and current left pointer
        if (grid[i][j] >= minHeight) {
          left[j] = Math.max(left[j], curLeft);
        }
        //else left will be zero and current left pointer will get incremented to j+1
        else {
          left[j] = 0;
          curLeft = j + 1;
        }
      }
      // update right array
      for (int j = cols - 1; j >= 0; j--) {
        //if element in grid is greater than or equal to min height,
        //corresponding element's value will be min of right and current right pointer
        if (grid[i][j] >= minHeight) {
          right[j] = Math.min(right[j], curRight);
        }
        //else right will be equal to column size and current right pointer will be set to j
        else {
          right[j] = cols;
          curRight = j;
        }
      }
      // update area of the rectangle
      for (int j = 0; j < cols; j++) {
        maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
        //if maxsize is less than max area, compute the coordinates
        if(maxSize < maxarea) {
          maxSize = maxarea;
          x2 = i+1;
          y1 = j+1;
          x1 = x2 - height[j]+1;
          y2 = y1 + (right[j]- left[j])-1;
        }
      }
    }
    System.out.println("Top left (x1, y1) and bottom right (x2, y2) co-ordinates of the rectangular matrix are:");
    System.out.println(x1+" "+y1+" "+x2+" "+y2);
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
