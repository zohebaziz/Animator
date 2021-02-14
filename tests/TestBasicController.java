import static org.junit.Assert.assertEquals;

import cs3500.animator.controllers.BasicAnimationController;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.view.AnimationTextView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import org.junit.Test;

/**
 * Tester class for BasicAnimationController.
 */
public class TestBasicController {

  BasicAnimationModel model = new BasicAnimationModel();

  AnimationView view = new AnimationTextView();

  BasicAnimationController controllerT = new BasicAnimationController(this.model, this.view);

  @Test
  public void testBasicControllerWithText() throws IOException {

    StringBuilder expected = new StringBuilder();

    // some actions to make sure the view has something to display
    this.model.create("r", ShapeType.RECTANGLE);

    this.model.motion("r", 0, new Position2D(50, 50), 50, 50, 255, 255, 255);
    this.model.motion("r", 2, new Position2D(80, 50), 50, 80, 255, 255, 255);
    this.model.motion("r", 4, new Position2D(100, 50), 50, 100, 255, 255, 255);

    // to show that appendable is initially empty.
    assertEquals("", expected.toString());

    // will call render on the view which will cause expected to build a string.
    this.controllerT.start(expected, 1);

    assertEquals("canvas 0 0 200 200\n"
        + "shape r Rectangle\n"
        + "motion r 0.0 50.0 50.0 50 50 255 255 255 2000.0 80.0 50.0 50 80 255 255 255\n"
        + "motion r 2000.0 80.0 50.0 50 80 255 255 255 4000.0 100.0 50.0 50 100 255 255 255\n",
        expected.toString());
  }

}
