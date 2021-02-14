import static org.junit.Assert.assertEquals;

import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape;
import cs3500.animator.models.Shape.ShapeType;
import org.junit.Test;

/**
 * Class to test methods from the Shape class.
 */
public class ShapeTest {

  Shape circle = new Shape("c", ShapeType.CIRCLE, 255, 255, 255, 50, 50);

  @Test
  public void testModifiers() {

    assertEquals( 50, this.circle.getHeight());
    assertEquals(new Position2D(0, 0), this.circle.getPosition());
    assertEquals(ShapeType.CIRCLE, this.circle.getType());
    assertEquals(50, this.circle.getWidth());
    assertEquals(255, this.circle.getBValue());
    assertEquals(255, this.circle.getGValue());
    assertEquals(255, this.circle.getRValue());

    this.circle.setHeight(10);
    this.circle.setWidth(5);
    this.circle.setPosition(new Position2D(3, 1));
    this.circle.setColor(10, 20, 30);


    assertEquals( 10, this.circle.getHeight());
    assertEquals(new Position2D(3, 1), this.circle.getPosition());
    assertEquals(5, this.circle.getWidth());
    assertEquals(30, this.circle.getBValue());
    assertEquals(20, this.circle.getGValue());
    assertEquals(10, this.circle.getRValue());
  }

  @Test
  public void testAccessors() {

    assertEquals( 50, this.circle.getHeight());
    assertEquals(new Position2D(0, 0), this.circle.getPosition());
    assertEquals(ShapeType.CIRCLE, this.circle.getType());
    assertEquals(50, this.circle.getWidth());
    assertEquals(255, this.circle.getBValue());
    assertEquals(255, this.circle.getGValue());
    assertEquals(255, this.circle.getRValue());

  }

  @Test
  public void testToString() {

    assertEquals("time: 0 type: Circle, color: (255 255 255),"
        + " position: (0.000000, 0.000000), size: 50 50]", circle.toString());
  }
}
