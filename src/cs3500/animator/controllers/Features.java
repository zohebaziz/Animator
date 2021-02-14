package cs3500.animator.controllers;

/**
 * Interface to manage the intractable features of an Animation.
 */
public interface Features {

  /**
   * Pauses or resumes an animation based on the provided boolean.
   */
  void pauseOrStart();

  /**
   * Increments or decrements the speed of animation.
   *
   * @param inc The int to change the speed by
   */
  void editSpeed(int inc);

  /**
   * Restarts the animation from tick 0.
   */
  void restart();

  /**
   * Determines if the animation should restart once it ends.
   */
  void loop();


}
