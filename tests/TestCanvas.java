import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.models.Canvas;
import org.junit.Test;

/**
 * Tests for the Canvas class.
 */
public class TestCanvas {

  @Test(expected = IllegalArgumentException.class)
  public  void invalidCanvasWidth() {
    Canvas c = new Canvas(0, 0, -100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public  void invalidCanvasHeight() {
    Canvas c = new Canvas(0, 0, 100, -100);
  }

  @Test
  public void testGetX() {
    Canvas c = new Canvas(0, 0, 100, 100);
    assertEquals(0, c.getX());
  }

  @Test
  public void testGetY() {
    Canvas c = new Canvas(0, 10, 100, 100);
    assertEquals(10, c.getY());
  }

  @Test
  public void testGetWidth() {
    Canvas c = new Canvas(0, 0, 100, 100);
    assertEquals(100, c.getCanvasWidth());
  }

  @Test
  public void testGetHeight() {
    Canvas c = new Canvas(0, 0, 100, 110);
    assertEquals(110, c.getCanvasHeight());
  }

  @Test
  public void doesEqual() {
    Canvas c1 = new Canvas(0, 0, 100, 100);
    Canvas c2 = new Canvas(0, 0, 100, 100);

    assertEquals(c1, c2);
  }

  @Test
  public void doesNotEqual() {
    Canvas c1 = new Canvas(0, 0, 100, 100);
    Canvas c2 = new Canvas(10, 0, 1000, 100);

    assertNotEquals(c1, c2);
  }

  @Test
  public void testToString() {
    Canvas c1 = new Canvas(0, 0, 100, 100);
    assertEquals("canvas 0 0 100 100", c1.toString());
  }

  @Test
  public void testHash() {
    Canvas c1 = new Canvas(0, 0, 100, 100);
    assertEquals(3998721, c1.hashCode());
  }

  @Test
  public void hashEquals() {
    Canvas c1 = new Canvas(0, 0, 100, 100);
    Canvas c2 = new Canvas(0, 0, 100, 100);
    assertEquals(c1.hashCode(), c2.hashCode());
  }

}
