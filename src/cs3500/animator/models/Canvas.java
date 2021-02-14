package cs3500.animator.models;

import java.util.Objects;

/**
 * Class to represent the parameters of a canvas and the ways shapes can move within it.
 */
public final class Canvas {

  private final int x;
  private final int y;
  private final int canvasWidth;
  private final int canvasHeight;

  /**
   * Basic constructor setting default values for each field.
   */
  public Canvas() {
    this.x = 0;
    this.y = 0;
    this.canvasWidth = 200;
    this.canvasHeight = 200;
  }

  /**
   * Constructor taking in parameters for each field.
   *
   * @param x            left-most point on the canvas
   * @param y            top-most point on the screen
   * @param canvasWidth  max-width of the canvas
   * @param canvasHeight max-height of the canvas
   * @throws IllegalArgumentException if either the width or height is less than or equal to zero
   */
  public Canvas(int x, int y, int canvasWidth, int canvasHeight) throws IllegalArgumentException {
    if (canvasHeight > 0 && canvasWidth > 0) {
      this.x = x;
      this.y = y;
      this.canvasWidth = canvasWidth;
      this.canvasHeight = canvasHeight;
    } else {
      throw new IllegalArgumentException("Invalid canvas size");
    }
  }

  /**
   * Way to retrieve the left-most of this canvas.
   *
   * @return an int of the left-most of this canvas
   */
  public int getX() {
    return this.x;
  }

  /**
   * Way to retrieve the top-most of this canvas.
   *
   * @return an int of the top-most of this canvas
   */
  public int getY() {
    return this.y;
  }

  /**
   * Way to retrieve the max-width of this canvas.
   *
   * @return an int of the max-width of this canvas
   */
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  /**
   * Way to retrieve the max-height of this canvas.
   *
   * @return an int of the max-height of this canvas
   */
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public String toString() {
    return "canvas " + this.getX() + " " + this.getY() + " " +
        this.getCanvasWidth() + " " + this.getCanvasHeight();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Canvas) {
      return ((Canvas) o).getCanvasHeight() == this.getCanvasWidth()
          && ((Canvas) o).getCanvasWidth() == this.getCanvasWidth() && ((Canvas) o).getX() == this
          .getX() && ((Canvas) o).getY() == this.getY();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getCanvasHeight(), this.getCanvasHeight(), this.getX(), this.getY());
  }
}
