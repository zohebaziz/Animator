import static org.junit.Assert.assertEquals;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape;
import cs3500.animator.models.Shape.ShapeType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Test class for the Basic Animation Model.
 */
public class TestBasicAnimationModel {

  /* ----------create(...) Tests------------ */

  @Test
  public void createAllParams() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 0, 255, 0,
        new Position2D(50, 50), 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void createNoPos() {

    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 255, 255, 255, 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 255, 255, 255, 50, 50);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void createOnlyNameAndType() {

    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 0, 0, 0, 0));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void createNameTypeColor() {

    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 0, 255));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 0, 255);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void createNameTypeSize() {

    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 50, 50);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWrongWidth() {

    Map<String, Shape> shapeMapError = new HashMap<>();

    AnimationModel anim = new BasicAnimationModel(shapeMapError);

    anim.create("r", ShapeType.RECTANGLE, -50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWrongWeightZero() {

    Map<String, Shape> shapeMapError = new HashMap<>();

    AnimationModel anim = new BasicAnimationModel(shapeMapError);

    anim.create("r", ShapeType.RECTANGLE, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWrongHeight() {

    Map<String, Shape> shapeMapError = new HashMap<>();

    AnimationModel anim = new BasicAnimationModel(shapeMapError);

    anim.create("r", ShapeType.RECTANGLE, 4, -200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWrongHeightZero() {

    Map<String, Shape> shapeMapError = new HashMap<>();

    AnimationModel anim = new BasicAnimationModel(shapeMapError);

    anim.create("r", ShapeType.RECTANGLE, 40, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWrongShapeType() {

    Map<String, Shape> shapeMapError = new HashMap<>();

    AnimationModel anim = new BasicAnimationModel(shapeMapError);

    anim.create("r", ShapeType.RECTANGLE);
    anim.create("r", ShapeType.CIRCLE);
  }

  /* ----------remove(...) Tests------------ */

  @Test
  public void removeShape() {

    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 0, 255, 0,
        new Position2D(50, 50), 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);

    assertEquals(shapeMapExpected, shapeMapActual);

    shapeMapExpected.clear();

    anim.remove("r");

    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeShapeNotThere() {

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);

    anim.remove("rectangle");
  }

  @Test
  public void editAllParams() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 255, 165, 0,
        new Position2D(200, 200), 100, 10));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.setCanvas(0, 0, 1000, 1000);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 0, new Position2D(10, 10), 50, 50, 255, 165, 0);
    anim.motion("r", 4, new Position2D(75, 75), 100, 100, 255, 165, 0);
    anim.motion("r", 6, new Position2D(200, 200), 100, 10, 255, 165, 0);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void editOnlyColor() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 255, 165, 0,
        new Position2D(50, 50), 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 4, new Position2D(50, 50), 50, 50, 255, 165, 0);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void editOnlyPosition() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 0, 255, 0,
        new Position2D(100, 100), 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 4, new Position2D(100, 100), 50, 50, 0, 255, 0);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void editHeightAndColor() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 255, 165, 0,
        new Position2D(50, 50), 50, 100));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 5, new Position2D(50, 50), 50, 100, 255, 165, 0);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test
  public void editWidthAndColor() {
    Map<String, Shape> shapeMapExpected = new HashMap<>();
    shapeMapExpected.put("r", new Shape("r", ShapeType.RECTANGLE, 0, 255, 165, 0,
        new Position2D(50, 50), 100, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 8, new Position2D(50, 50), 100, 50, 255, 165, 0);
    assertEquals(shapeMapExpected, shapeMapActual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void editBadNaming() {

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("p", 5, new Position2D(50, 50), 50, 50, 0, 255, 0);
  }

  @Test
  public void moveShape() {
    List<Shape> shapeMapExpected = new ArrayList<>();
    shapeMapExpected.add(new Shape("r", ShapeType.RECTANGLE, 5, 0, 255, 0,
        new Position2D(55, 55), 50, 50));

    Map<String, Shape> shapeMapActual = new HashMap<>();
    AnimationModel anim = new BasicAnimationModel(shapeMapActual);
    anim.create("r", ShapeType.RECTANGLE, 0, 255, 0,
        new Position2D(50, 50), 50, 50);
    anim.motion("r", 5, new Position2D(55, 55), 50, 50, 0, 255, 0);
    assertEquals(shapeMapExpected, anim.getFrames().get(5));
  }

  @Test
  public void testGettingFrames() {

    AnimationModel actual = new BasicAnimationModel();

    actual.create("rect", ShapeType.RECTANGLE);
    actual.motion("rect", 0, new Position2D(50, 50), 25, 25, 0, 255, 0);
    actual.motion("rect", 1, new Position2D(50, 100), 25, 25, 0, 255, 0);

    List<Shape> helper = new ArrayList<>();
    List<Shape> helper2 = new ArrayList<>();

    helper.add(new Shape("rect", ShapeType.RECTANGLE, 0, 0, 255,
        0, new Position2D(50, 50), 25, 25));
    helper2.add(new Shape("rect", ShapeType.RECTANGLE, 1, 0, 255,
        0, new Position2D(50, 100), 25, 25));

    Map<Integer, List<Shape>> expected = new HashMap<>();

    expected.put(0, helper);
    expected.put(1, helper2);

    assertEquals(actual.getFrames(), expected);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingMotions() {

    AnimationModel model = new BasicAnimationModel();

    model.create("rect", ShapeType.RECTANGLE);

    model.motion("rect", 4, new Position2D(50, 50), 25, 25, 0, 255, 0);
    model.motion("rect", 3, new Position2D(50, 100), 25, 25, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBounds() {

    AnimationModel model = new BasicAnimationModel();

    model.create("rect", ShapeType.RECTANGLE);

    model.motion("rect", 0, new Position2D(50, 50), 25, 25, 0, 255, 0);
    model.motion("rect", 0, new Position2D(50, 100), 500, 25, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColor() {

    AnimationModel model = new BasicAnimationModel();

    model.create("rect", ShapeType.RECTANGLE);

    model.motion("rect", 0, new Position2D(50, 50), 25, 25, 0, 255, 0);
    model.motion("rect", 0, new Position2D(50, 100), 500, 25, 0, 500, 0);
  }

  /* ----------getAllShapes(...) Tests------------ */

  @Test
  public void testGettingShapes() {

    AnimationModel actual = new BasicAnimationModel();

    actual.create("circ", ShapeType.CIRCLE);
    actual.create("rect", ShapeType.RECTANGLE);

    Map<String, Shape> expected = new HashMap<>();
    expected.put("circ", new Shape("circ", ShapeType.CIRCLE, 0, 0, 0, 0, 0));
    expected.put("rect", new Shape("rect", ShapeType.RECTANGLE, 0, 0, 0, 0, 0));

    assertEquals(actual.getAllShapes(), expected);
  }

  /* ----------removeMotion(...) Tests------------ */

  @Test
  public void testRemoveMotion() {
    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);
    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 2, new Position2D(100, 100),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(50, 100),  20, 20, 255, 1, 1);

    anim.removeMotion("r", 2);

    List<Shape> helper = new ArrayList<>();
    List<Shape> helper2 = new ArrayList<>();

    helper.add(new Shape("r", ShapeType.RECTANGLE, 0, 1, 1,
        1, new Position2D(50, 50), 20, 20));
    helper2.add(new Shape("r", ShapeType.RECTANGLE, 3, 255, 1,
        1, new Position2D(50, 100), 20, 20));

    Map<Integer, List<Shape>> expected = new HashMap<>();

    expected.put(0, helper);
    expected.put(3, helper2);

    assertEquals(expected, anim.getFrames());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalidTime() {
    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);
    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 2, new Position2D(100, 100),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(50, 100),  20, 20, 255, 1, 1);

    anim.removeMotion("r", 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalidShapeMotion() {
    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);
    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 2, new Position2D(100, 100),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(50, 100),  20, 20, 255, 1, 1);

    anim.removeMotion("c", 4);
  }

  @Test
  public void testGetShapesAt() {
    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);
    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 1, new Position2D(75, 75),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(100, 100),  20, 20, 1, 1, 1);
    anim.create("c", ShapeType.CIRCLE);
    anim.motion("c", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("c", 1, new Position2D(50, 50),  60, 60, 1, 1, 1);
    anim.motion("c", 3, new Position2D(50, 50),  100, 100, 1, 1, 1);
    anim.create("s", ShapeType.SQUARE);
    anim.motion("s", 4, new Position2D(50, 50),  20, 20, 1, 1, 1);

    List<Shape> expected = new ArrayList<>();
    expected.add(new Shape("r", ShapeType.RECTANGLE, 2, 1, 1, 1, new Position2D(88, 88), 20, 20));
    expected.add(new Shape("c", ShapeType.RECTANGLE, 2, 1, 1, 1, new Position2D(50, 50), 80, 80));

    assertEquals(expected, anim.shapesAtTime(2));
  }

  @Test
  public void getFinalTime() {
    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);
    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 1, new Position2D(75, 75),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(100, 100),  20, 20, 1, 1, 1);
    assertEquals(3, anim.getFinalTime());
  }

  @Test
  public void testShapeAtATime() {

    BasicAnimationModel anim = new BasicAnimationModel();

    anim.create("c", ShapeType.CIRCLE);
    anim.motion("c", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("c", 2, new Position2D(75, 75),  20, 20, 1, 1, 1);
    anim.motion("c", 4, new Position2D(100, 100),  20, 20, 1, 1, 1);

    List<Shape> expected = new ArrayList<>();

    expected.add(new Shape("c", ShapeType.CIRCLE, 2, 1, 1, 1,
        new Position2D(75, 75), 20, 20));

    assertEquals(expected, anim.shapesAtTime(2));

    anim.create("r", ShapeType.RECTANGLE);

    anim.motion("r", 2, new Position2D(50, 50),  20, 20, 1, 50, 1);

    expected.add(new Shape("r", ShapeType.RECTANGLE, 2, 1, 50, 1,
        new Position2D(50, 50), 20, 20));
  }

  @Test
  public void testGetFinalTime() {

    BasicAnimationModel anim = new BasicAnimationModel();
    anim.create("r", ShapeType.RECTANGLE);

    assertEquals(0, anim.getFinalTime()); // base case

    anim.motion("r", 0, new Position2D(50, 50),  20, 20, 1, 1, 1);
    anim.motion("r", 1, new Position2D(75, 75),  20, 20, 1, 1, 1);
    anim.motion("r", 3, new Position2D(100, 100),  20, 20, 1, 1, 1);

    assertEquals(3, anim.getFinalTime());
  }
}
