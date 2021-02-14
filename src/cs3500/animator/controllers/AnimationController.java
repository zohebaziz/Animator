package cs3500.animator.controllers;

import java.io.IOException;

/**
 * Interface to handle the different AnimationControllers.
 */
public interface AnimationController {

  /**
   * Starts the animation in the view.
   *
   * @param out   Appends the animation to the given output
   * @param speed The speed at which the animation should run
   * @throws IOException If the animation cannot be written to the appendable
   */
  void start(Appendable out, int speed) throws IOException;
}
