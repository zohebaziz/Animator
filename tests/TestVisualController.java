import static org.junit.Assert.assertEquals;

import cs3500.animator.controllers.AnimationController;
import cs3500.animator.controllers.AnimationVisualController;
import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests for the AnimationVisualController.
 */
public class TestVisualController {

  AnimationModel model = new BasicAnimationModel();

  @Test(expected = UnsupportedOperationException.class)
  public void testConfigureButtonListener() {

    AnimationView view = new MockViewForVisualController();

    // Should fail because mock model throws an exception if the controller calls the
    // addActionListener() method.
    AnimationController toFail = new AnimationVisualController(this.model, view);
  }

  @Test
  public void testGo() throws IOException {

    StringBuilder expectedRender = new StringBuilder();

    AnimationView view = new MockViewForVisualController();

    AnimationController controller = new AnimationVisualController(view);

    this.model.create("r1", ShapeType.RECTANGLE);
    this.model.create("r2", ShapeType.RECTANGLE);

    this.model.motion("r1", 0, new Position2D(50, 50), 50, 50, 255, 255, 255);
    this.model.motion("r1", 2, new Position2D(80, 50), 50, 80, 255, 255, 255);
    this.model.motion("r2", 0, new Position2D(50, 50), 50, 50, 255, 255, 255);
    this.model.motion("r2", 2, new Position2D(80, 50), 50, 80, 255, 255, 255);

    // Values are are at expected originals before controller is run

    assertEquals("", expectedRender.toString());

    controller.start(expectedRender, 1);

    assertEquals("Model rendered", expectedRender.toString());
  }

  //  Testing the update method from the controller is not possible due to how the Java Swing Timer
  //  functions. Since no Swing animation is being rendered the Timer's event listener is never
  //  called, and therefore the view.update(...) method is never called. Below is a hypothetical
  //  test if the event listener was called.
  //  @Test
  //  public void testUpdate() throws IOException {
  //    StringBuilder builder = new StringBuilder();
  //
  //    AnimationView view = new MockViewForVisualConntroller(builder);
  //
  //    AnimationController controller = new AnimationVisualController(view);
  //
  //
  //    this.model.create("r1", ShapeType.RECTANGLE);
  //    this.model.create("r2", ShapeType.RECTANGLE);
  //
  //
  //    this.model.motion("r1", 0, new Position2D(50, 50), 50, 50, 255, 255, 255);
  //    this.model.motion("r1", 2, new Position2D(80, 50), 50, 80, 255, 255, 255);
  //    this.model.motion("r2", 0, new Position2D(50, 50), 50, 50, 255, 255, 255);
  //    this.model.motion("r2", 2, new Position2D(80, 50), 50, 80, 255, 255, 255);
  //
  //    assertEquals("", builder.toString());
  //
  //    controller.go(new StringBuilder(), 1);
  //
  //    assertEquals("Update Received", builder.toString());
  //
  //  }

}
