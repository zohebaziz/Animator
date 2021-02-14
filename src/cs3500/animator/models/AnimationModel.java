package cs3500.animator.models;

import cs3500.animator.models.Shape.ShapeType;
import java.util.List;
import java.util.Map;

/**
 * Interface to handle different types of animation models.
 */
public interface AnimationModel {

  /**
   * Creates a shape of given parameters to be animated.
   *
   * @param name     to be given to the created shape
   * @param type     the type of shape to be created
   * @param r        red color value
   * @param b        blue color value
   * @param g        green color value
   * @param position the initial position of the shape to be created
   * @param width    width / radius of a shape (depending on its type)
   * @param height   height / radius of a shape (depending on its type)
   */
  void create(String name, ShapeType type, int r, int g, int b, Position2D position, int width,
      int height) throws IllegalArgumentException;

  /**
   * Creates a shape of given parameters to be animated.
   *
   * @param name   to be given to the created shape
   * @param type   the type of shape to be created
   * @param r      red color value
   * @param b      blue color value
   * @param g      green color value
   * @param width  width / radius of a shape (depending on its type)
   * @param height height / radius of a shape (depending on its type)
   */
  void create(String name, ShapeType type, int r, int g, int b, int width, int height)
      throws IllegalArgumentException;

  /**
   * Creates a shape of given parameters to be animated.
   *
   * @param name to be given to the created shape
   * @param type the type of shape to be created
   * @param r    red color value
   * @param b    blue color value
   * @param g    green color value
   */
  void create(String name, ShapeType type, int r, int g, int b) throws IllegalArgumentException;

  /**
   * Creates a shape of given parameters to be animated.
   *
   * @param name   to be given to the created shape
   * @param type   the type of shape to be created
   * @param width  width / radius of a shape (depending on its type)
   * @param height height / radius of a shape (depending on its type)
   */
  void create(String name, ShapeType type, int width, int height)
      throws IllegalArgumentException;

  /**
   * Creates a shape of given parameters to be animated.
   *
   * @param name   to be given to the created shape
   * @param type   the type of shape to be created
   */
  void create(String name, ShapeType type) throws IllegalArgumentException;


  /**
   * Handles any kind of edits to a shape such as its position, size, and color.
   *
   * @param shapeName Name of the shape to be edited
   * @param t         Time the motion ends
   * @param newPos    new position for the shape
   * @param w         new width of the shape (if any)
   * @param h         new height of the shape (if any)
   * @param r         new red color value for the shape
   * @param b         new blue color value for the shape
   * @param g         new green color value for the shape
   * @throws IllegalArgumentException if any of the inputs are invalid such as
   */
  void motion(String shapeName, int t, Position2D newPos,
      int w, int h, int r, int g, int b) throws IllegalArgumentException;

  /**
   * Remove a specified shape from the animation.
   *
   * @param name The shape to be edited
   * @throws IllegalArgumentException if the name of the shape is invalid
   */
  void remove(String name) throws IllegalArgumentException;

  /**
   * Provides classes with a way to retrieve the different motions occurring in the animation.
   *
   * @return a Map of the different motions occurring in the animation and the state of the shapes
   */
  Map<Integer, List<Shape>> getFrames();

  /**
   * Sets the bounds and canvas for this animation.
   *
   * @param x      left-most point of the animation
   * @param y      top-most point of the animation
   * @param width  of the canvas
   * @param height of the canvas
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Allows views access to the canvas size and boundaries.
   *
   * @return the canvas for this animation
   */
  Canvas getCanvas();

  /**
   * Provides classes with a way to retrieve the all different shapes in the animation.
   *
   * @return a Map of the different shapes in the animation.
   */
  Map<String, Shape> getAllShapes();

  /**
   * Removes a motion from the animation.
   *
   * @param shapeName name of the shape to remove a motion from
   * @param t         time the motion occurs
   * @throws IllegalArgumentException if the shape or time doesn't exist
   */
  void removeMotion(String shapeName, int t) throws IllegalArgumentException;

  /**
   * Finds the state of Shapes at the provided time.
   *
   * @param tick The time to find the state of shapes of
   * @return Map of the state of shapes from the provided time
   */
  List<Shape> shapesAtTime(int tick);

  /**
   * Simple getter to get the time (in ticks that the animation ends).
   *
   * @return the int of when the animation is over
   */
  int getFinalTime();

}
