import static org.junit.Assert.assertEquals;

import cs3500.animator.controllers.Features;
import org.junit.Test;

/**
 * Test class for features.
 */
public class TestFeatures {

  @Test
  public void testPauseAndStart() {
    StringBuilder out = new StringBuilder();
    Features f = new MockFeatures(out);

    f.pauseOrStart();

    assertEquals("To Pause or not to pause.", out.toString());
  }

  @Test
  public void testIncSpeed() {
    StringBuilder out = new StringBuilder();
    Features f = new MockFeatures(out);

    f.editSpeed(10);

    assertEquals("10", out.toString());
  }

  @Test
  public void testRestart() {
    StringBuilder out = new StringBuilder();
    Features f = new MockFeatures(out);

    f.restart();

    assertEquals("Restart Animation", out.toString());
  }

  @Test
  public void testLoop() {
    StringBuilder out = new StringBuilder();
    Features f = new MockFeatures(out);

    f.loop();

    assertEquals("Loop Animation", out.toString());
  }
}
