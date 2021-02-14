package cs3500.animator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simply class to create an animation to visualize and animate the bubble sort algorithm.
 */
public class BubbleSortAnimation {

  private static StringBuilder output;

  /**
   * Main class to create the bubble sort animation file.
   * @param args command line arguments
   * @throws IOException if the StringBuilder can't append
   */
  public static void main(String[] args) throws IOException {

    output = new StringBuilder("canvas 0 0 1250 1000\n");

    sortSquares();

    FileWriter fw = new FileWriter("BubbleSort.txt", true);
    fw.write(output.toString());
    fw.close();
  }

  /**
   * Helper method that essentially runs Bubble Sort on an array of integers.
   */
  private static void sortSquares() {
    int[] arr = {30, 50, 10, 100, 40, 90, 60};
    Map<Integer, Integer> origPositions = new HashMap<>();
    origPositions.put(30, 0);
    origPositions.put(50, 1);
    origPositions.put(10, 2);
    origPositions.put(100, 3);
    origPositions.put(40, 4);
    origPositions.put(90, 5);
    origPositions.put(60, 6);

    for (int i = 0; i < arr.length; i++) {
      output.append("shape").append(" r").append(i).append(" rectangle").append("\n");
    }

    int n = arr.length - 1;
    for (int i = 0; i < n + 1; i++) {
      output.append("motion ").append("r").append(i).append(" ").append(1).append(" ")
          .append(150 * (i + 1)).append(" ").append(300).append(" ").append(arr[i]).append(" ")
          .append(arr[i]).append(" ").append(0).append(" ").append(0).append(" ")
          .append(0).append(" ").append(1).append(" ")
          .append(150 * (i + 1)).append(" ").append(300).append(" ").append(arr[i]).append(" ")
          .append(arr[i]).append(" ").append(0).append(" ").append(0).append(" ")
          .append(0).append("\n");
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - i; j++) {
        if (arr[j] > arr[j + 1]) {
          count++;
          output.append("motion ").append("r").append(origPositions.get(arr[j])).append(" ")
              .append((count - 1) * 20 + 5).append(" ")
              .append(150 * (j + 1)).append(" ").append(300).append(" ").append(arr[j]).append(" ")
              .append(arr[j]).append(" ").append(0).append(" ").append(0).append(" ")
              .append(0).append(" ").append(count * 20).append(" ")
              .append(150 * (j + 2)).append(" ").append(300).append(" ").append(arr[j]).append(" ")
              .append(arr[j]).append(" ").append(0).append(" ").append(0).append(" ")
              .append(0).append("\n");

          output.append("motion ").append("r").append(origPositions.get(arr[j + 1])).append(" ")
              .append((count - 1) * 20 + 5).append(" ")
              .append(150 * (j + 2)).append(" ").append(300).append(" ").append(arr[j + 1])
              .append(" ")
              .append(arr[j + 1]).append(" ").append(0).append(" ").append(0).append(" ")
              .append(0).append(" ").append(count * 20).append(" ")
              .append(150 * (j + 1)).append(" ").append(300).append(" ").append(arr[j + 1])
              .append(" ")
              .append(arr[j + 1]).append(" ").append(0).append(" ").append(0).append(" ")
              .append(0).append("\n");

          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }
}
