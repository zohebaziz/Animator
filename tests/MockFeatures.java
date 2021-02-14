import cs3500.animator.controllers.Features;
import java.io.IOException;

/**
 * Mock implementation of the features interface to test that it works as intended with a
 * controller.
 */
public class MockFeatures implements Features {

  Appendable testOutput;

  /**
   * Default constructor for MockFeatures.
   * @param testOutput Appendable used in testng the functionality of each method.
   */
  public MockFeatures(Appendable testOutput) {
    this.testOutput = testOutput;
  }

  @Override
  public void pauseOrStart() {
    try {
      this.testOutput.append("To Pause or not to pause.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void editSpeed(int inc) {
    try {
      this.testOutput.append(Integer.toString(inc));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void restart() {
    try {
      this.testOutput.append("Restart Animation");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void loop() {
    try {
      this.testOutput.append("Loop Animation");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
