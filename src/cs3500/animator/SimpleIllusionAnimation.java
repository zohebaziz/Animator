package cs3500.animator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Creates a txt file that is runnable through the easy animator and will display an
 * optical illusion.
 */
public class SimpleIllusionAnimation {

  private static StringBuilder output;

  /**
   * Main method that will write the txt file for the SimpleIllusionAnimation that is runnable
   * the animator.
   * @param args given command line arguments.
   * @throws IOException if the file writer fails for any reason.
   */
  public static void main(String[] args) throws IOException {

    output = new StringBuilder("canvas 0 0 800 800\n");

    createAnimation();

    FileWriter fw = new FileWriter("OpticalIllusion.txt", true);
    fw.write(output.toString());
    fw.close();
  }

  /**
   * Helper method that basically creates the string that holds all of the commands of the textual
   * representation of this SimpleIllusionAnimation.
   */
  private static void createAnimation() {

    boolean black = true;
    int time = 1;
    Random rd = new Random();

    for (int i = 0; i < 20; i++) {

      if (!black) {

        // create rectangle
        output.append("shape R").append(i).append(" rectangle\n");
        // initialize values
        output.append("motion R").append(i)
            .append(" ").append(time).append(" 380 380 40 40 255 255 255 ").append(time)
            .append(" 380 380 40 40 255 255 255\n");
        // constantly get bigger
        for (int j = 1; j < 20; j++) {

          output.append("motion R").append(i).append(" ").append(time + j - 1)
              .append(" ").append(380 - (20 * j)).append(" ").append(380 - (20 * j)).append(" ")
              .append(40 + (j * 40)).append(" ").append(40 + (j * 40))
              .append(" ").append(rd.nextInt(256)).append(" ")
              .append(rd.nextInt(256)).append(" ")
              .append(rd.nextInt(256)).append(" ");
          output.append(time + j).append(" ").append(380 - (20 * j)).append(" ")
              .append(380 - (20 * j))
              .append(" ").append(40 + (j * 40))
              .append(" ").append(40 + (j * 40))
              .append(" ").append(rd.nextInt(256)).append(" ")
              .append(rd.nextInt(256)).append(" ")
              .append(rd.nextInt(256)).append("\n");
        }

        black = true;
        time += 1;
      }
      else {

        // create rectangle
        output.append("shape R").append(i).append(" rectangle\n");
        // initialize values
        output.append("motion R").append(i)
            .append(" ").append(time).append(" 380 380 40 40 0 0 0 ").append(time)
            .append(" 380 380 40 40 0 0 0\n");
        // constantly get bigger
        for (int j = 1; j < 20; j++) {

          output.append("motion R").append(i).append(" ").append(time + j - 1)
              .append(" ").append(380 - (20 * j)).append(" ").append(380 - (20 * j)).append(" ")
              .append(40 + (j * 40)).append(" ").append(40 + (j * 40)).append(" 0 0 0 ");
          output.append(time + j).append(" ").append(380 - (20 * j)).append(" ")
              .append(380 - (20 * j))
              .append(" ").append(40 + (j * 40))
              .append(" ").append(40 + (j * 40)).append(" 0 0 0\n");
        }

        black = false;
        time += 1;
      }
    }
  }
}
