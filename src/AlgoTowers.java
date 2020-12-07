import java.io.IOException;

public class AlgoTowers {

  public static void main(String[] args) throws IOException {
    int choice = Integer.parseInt(args[0]);
    long startTime = 0, endTime = 0;
    switch (choice) {
      case 1:
        Task1 task1 = new Task1();
//        startTime = System.currentTimeMillis();
        System.out.println("Task1:");
        task1.execute();
//        endTime = System.currentTimeMillis();
//        System.out.println("Task 1 time: "+(endTime - startTime)+" ms");
        break;
      case 2:
        Task2 task2 = new Task2();
//        startTime = System.currentTimeMillis();
        System.out.println("Task2:");
        task2.execute();
//        endTime = System.currentTimeMillis();
//        System.out.println("Task 2 time: "+(endTime - startTime)+" ms");
        break;

      case 3:
        Task3 task3 = new Task3();
//        startTime = System.currentTimeMillis();
        System.out.println("Task3:");
        task3.execute();
//        endTime = System.currentTimeMillis();
//        System.out.println("Task 3 time: "+(endTime - startTime)+" ms");
        break;

      case 4:
        Task4 task4 = new Task4();
//        startTime = System.currentTimeMillis();
        System.out.println("Task4:");
        task4.execute();
//        endTime = System.currentTimeMillis();
//        System.out.println("Task 4 time: "+(endTime - startTime)+" ms");

      case 5:
        Task5 task5 = new Task5();
//        startTime = System.currentTimeMillis();
        System.out.println("Task5:");
        task5.execute();
//        endTime = System.currentTimeMillis();
//        System.out.println("Task 5 time: "+(endTime - startTime)+" ms");
        break;

      default:
        System.out.println("Invalid task number. Enter a number between 1 and 5");
    }
  }
}
