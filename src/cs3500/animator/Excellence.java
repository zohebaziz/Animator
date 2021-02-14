package cs3500.animator;

import cs3500.animator.controllers.AnimationController;
import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel.Builder;
import cs3500.animator.util.AnimationFactory;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to create an run an animation and display it by several means.
 */
public class Excellence {

  /**
   * Main method to run project through the command line.
   *
   * @param args Array of Strings of possible command line arguments
   * @throws IOException If certain animations cannot be run properly or if a file cannot be
   *                     written
   */
  public static void main(String[] args) throws IOException {
    int speed = 1;
    String viewString = "text";
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    boolean toFile = false;
    String fileName = "";

    for (int i = 0; i < args.length; i += 2) {
      String currArg = args[i];
      switch (currArg) {
        case "-in":
          input = new FileReader(new File(args[i + 1]));
          break;
        case "-out":
          output = new StringBuilder();
          toFile = true;
          fileName = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        default:
          viewString = args[i + 1];
      }
    }

    AnimationView view = AnimationFactory.createView(viewString);
    AnimationModel model = AnimationReader.parseFile(input, new Builder());
    AnimationController controller = AnimationFactory.createController(viewString, model, view);

    controller.start(output, speed);

    if (toFile) {
      FileWriter fw = new FileWriter(fileName, true);
      fw.write(output.toString());
      fw.close();
    }
  }
}
