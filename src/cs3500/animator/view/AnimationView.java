package cs3500.animator.view;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.Shape;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Interface to be implemented for different ways to show an animation.
 */
public interface AnimationView {
  /**
   * Draws a representation of the animation as per the specific implementation of the view.
   *
   * @param model the animation to be read from and displayed
   * @param out   where to display the animation
   * @param speed of which the animation will run in ticks per second
   * @throws IOException              if an out put cannot be correctly rendered
   * @throws IllegalArgumentException if the speed is zero or negative
   */
  void render(AnimationModel model, Appendable out, int speed) throws IOException,
      IllegalArgumentException;

  /**
   * Updates a visual view on what shapes have to be drawn at a certain tick.
   *
   * @param shapesToDraw the List of Shapes to draw at a tick
   */
  void update(List<Shape> shapesToDraw);

  /**
   * Adds ActionEvents to a view's buttons.
   *
   * @param listener The events to add
   */
  void addActionListener(ActionListener listener);
}
