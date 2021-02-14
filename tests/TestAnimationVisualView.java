import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.AnimationVisualView;
import java.io.IOException;
import org.junit.Test;

/**
 * Test class for the visual view (mostly testing for exceptions).
 */
public class TestAnimationVisualView {

  @Test(expected = IllegalArgumentException.class)
  public void invalidSpeed() throws IOException {
    AnimationModel model = new BasicAnimationModel();
    StringBuilder out = new StringBuilder();
    AnimationView svg = new AnimationVisualView();

    model.create("c", ShapeType.OVAL, 20, 30);

    model.motion("c", 0, new Position2D(10, 10), 20, 30, 255, 165, 0);
    model.motion("c", 5, new Position2D(100, 10), 40, 40, 255, 165, 0);
    model.motion("c", 7, new Position2D(100, 10), 40, 40, 0, 165, 0);

    svg.render(model, out, 0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotAddActionListener() {
    AnimationView view = new AnimationVisualView();
    view.addActionListener(new ButtonListener());
  }
}
