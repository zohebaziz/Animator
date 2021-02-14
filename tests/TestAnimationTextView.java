import static org.junit.Assert.assertEquals;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.AnimationTextView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Test class for the AnimationTextView.
 */
public class TestAnimationTextView {

  @Test
  public void testToString() {
    AnimationModel anim = new BasicAnimationModel();
    AnimationTextView view = new AnimationTextView(anim, new StringBuilder(), 1);

    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 6, new Position2D(50, 50),
        50, 50, 255, 0, 0);
    anim.motion("r", 8, new Position2D(100, 100),
        50, 50, 255, 0, 0);
    anim.remove("r");
    assertEquals("canvas 0 0 200 200\n"
            + "shape r Rectangle\n"
            + "motion r 6000.0 50.0 50.0 50 50 255 0 0 8000.0 100.0 100.0 50 50 255 0 0\n",
        view.toString());

    anim.create("c", ShapeType.CIRCLE, 255, 0, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("c", 6, new Position2D(50, 50),
        200, 50, 255, 0, 0);
    anim.motion("c", 8, new Position2D(100, 100),
        200, 200, 255, 0, 0);
    assertEquals("canvas 0 0 200 200\n"
            + "shape r Rectangle\n"
            + "motion r 6000.0 50.0 50.0 50 50 255 0 0 8000.0 100.0 100.0 50 50 255 0 0\n"
            + "shape c Circle\n"
            + "motion c 6000.0 50.0 50.0 200 50 255 0 0 8000.0 100.0 100.0 200 200 255 0 0\n",
        view.toString());
  }

  @Test
  public void testRender() {
    Appendable app = new StringBuilder();
    AnimationModel anim = new BasicAnimationModel();
    AnimationTextView view = new AnimationTextView(anim, app, 1);

    anim.create("rect", ShapeType.RECTANGLE, 255, 0, 0,
        new Position2D(100, 100), 150, 100);
    anim.motion("rect", 2, new Position2D(75, 300),
        150, 100, 255, 0, 0);
    anim.motion("rect", 4, new Position2D(75, 300),
        150, 100, 255, 0, 0);
    anim.motion("rect", 5, new Position2D(75, 315),
        150, 100, 255, 0, 0);
    anim.motion("rect", 6, new Position2D(75, 60),
        150, 100, 255, 0, 0);

    try {
      view.render(anim, app, 1);
    } catch (IOException e) {
      System.out.println("Error");
    }

    assertEquals("canvas 0 0 200 200\n"
            + "shape rect Rectangle\n"
            + "motion rect 2000.0 75.0 300.0 150 100 255 0 0 4000.0 75.0 300.0 150 100 255 0 0\n"
            + "motion rect 4000.0 75.0 300.0 150 100 255 0 0 5000.0 75.0 315.0 150 100 255 0 0\n"
            + "motion rect 5000.0 75.0 315.0 150 100 255 0 0 6000.0 75.0 60.0 150 100 255 0 0\n",
        app.toString());
  }

  @Test(expected =  IllegalArgumentException.class)
  public void invalidSpeed() throws IOException {
    AnimationModel model = new BasicAnimationModel();
    StringBuilder out = new StringBuilder();
    AnimationView svg = new AnimationTextView();

    model.create("c", ShapeType.OVAL, 20, 30);

    model.motion("c", 0, new Position2D(10, 10), 20, 30, 255, 165, 0);
    model.motion("c", 5, new Position2D(100, 10), 40, 40, 255, 165, 0);
    model.motion("c", 7, new Position2D(100, 10), 40, 40, 0, 165, 0);

    svg.render(model, out, 0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotUpdateView() {
    AnimationView view = new AnimationTextView();

    view.update(new ArrayList<>());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotAddActionListener() {
    AnimationView view = new AnimationTextView();
    view.addActionListener(new ButtonListener());
  }
}
