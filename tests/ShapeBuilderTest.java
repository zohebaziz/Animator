import static org.junit.Assert.assertEquals;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.BasicAnimationModel.Builder;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.util.AnimationBuilder;
import org.junit.Test;

/**
 * Tests for the ShapeBuilder class.
 */
public class ShapeBuilderTest {

  @Test
  public void testSetNormalBounds() {
    BasicAnimationModel model = new BasicAnimationModel();
    model.setCanvas(0, 0, 100, 100);

    AnimationBuilder<AnimationModel> builder = new Builder();
    builder.setBounds(0, 0, 100, 100);

    assertEquals(model.getCanvas(), builder.build().getCanvas());
  }

  @Test
  public void testDeclareShape() {
    BasicAnimationModel model = new BasicAnimationModel();
    model.create("r", ShapeType.RECTANGLE);

    AnimationBuilder<AnimationModel> builder = new Builder();
    builder.declareShape("r", "rectangle");

    assertEquals(model.getAllShapes().get("r"), builder.build().getAllShapes().get("r"));
  }

  @Test
  public void testAddMotion() {
    BasicAnimationModel model = new BasicAnimationModel();
    model.create("r", ShapeType.RECTANGLE);
    model.motion("r", 1, new Position2D(50, 50), 50, 50, 255, 255, 255);
    model.motion("r", 5, new Position2D(100, 100), 50, 50, 255, 255, 255);

    AnimationBuilder<AnimationModel> builder = new Builder();
    builder.declareShape("r", "rectangle");
    builder.addMotion("r", 1, 50, 50, 50, 50, 255, 255, 255, 5, 100, 100, 50, 50, 255, 255, 255);

    assertEquals(model.getFrames(), builder.build().getFrames());
  }




}
