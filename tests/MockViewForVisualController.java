import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.Shape;
import cs3500.animator.view.AnimationView;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Mock view to use for testing the Visual Controller.
 */
public class MockViewForVisualController implements AnimationView {

  private final Appendable forTests;

  /**
   * Default Constructor.
   */
  public MockViewForVisualController() {

    this.forTests = new StringBuilder();
  }

  @Override
  public void render(AnimationModel model, Appendable out, int speed)
      throws IOException, IllegalArgumentException {

    out.append("Model rendered");
  }

  @Override
  public void update(List<Shape> shapesToDraw) {
    try {
      this.forTests.append("Update received");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addActionListener(ActionListener listener) {

    throw new UnsupportedOperationException("Catch this.");
  }
}
