package cs3500.animator.models;

import java.util.Objects;

/**
 * Class to represent the different shapes that can appear in an animation.
 */
public class Shape {

  /**
   * The different types of shapes that can be created.
   */
  public enum ShapeType {
    CIRCLE, OVAL, SQUARE, RECTANGLE;

    @Override
    public String toString() throws IllegalArgumentException {
      switch (this) {
        case CIRCLE:
          return "Circle";
        case OVAL:
          return "Oval";
        case SQUARE:
          return "Square";
        case RECTANGLE:
          return "Rectangle";
        default:
          throw new IllegalArgumentException("Invalid Shape");
      }
    }

    /**
     * Creates a ShapeType from a given String.
     *
     * @param type The String of the ShapeType to create
     * @return A new ShapeType representing the given String
     */
    public static ShapeType fromString(String type) {
      switch (type.toLowerCase()) {
        case "circle":
          return ShapeType.CIRCLE;
        case "ellipse":
          return ShapeType.OVAL;
        case "rectangle":
          return ShapeType.RECTANGLE;
        default:
          return ShapeType.SQUARE;
      }
    }
  }

  private final String name;
  private final ShapeType type;
  private int r;
  private int g;
  private int b;
  private Position2D position;
  private int width;
  private int height;
  private int time;

  /**
   * Constructor that takes initializes all fields of a shape.
   *
   * @param name   the name of the shape
   * @param type     the type of shape
   * @param time     the time this shape's state
   * @param r        red color value
   * @param b        blue color value
   * @param g        green color value
   * @param position the shape's initial position
   * @param width    the width of the shape
   * @param height   the height of the shape
   */
  public Shape(String name, ShapeType type, int time, int r, int g,
      int b, Position2D position, int width, int height) {
    this.name = name;
    this.r = r;
    this.b = b;
    this.g = g;
    this.type = type;
    this.position = position;
    this.width = width;
    this.height = height;
    this.time = time;
  }

  /**
   * Constructor that takes initializes most fields of a shape.
   *
   * @param name   the name of the shape
   * @param type   the type of shape
   * @param r      red color value
   * @param b      blue color value
   * @param g      green color value
   * @param width  the width of the shape
   * @param height the height of the shape
   */
  public Shape(String name, ShapeType type, int r, int g, int b, int width,
      int height) {
    this(name, type, 0, r, g, b, new Position2D(0, 0), width, height);
  }

  /**
   * Constructor that takes initializes all fields of a shape.
   *
   * @param name   the name of the shape
   * @param type   the type of shape
   * @param width  the width of the shape
   * @param height the height of the shape
   */
  public Shape(String name, ShapeType type, int width,
      int height) {
    this(name, type, 255, 255, 255, width, height);
  }

  /**
   * Constructor that takes initializes all fields of a shape.
   *
   * @param name   the name of the shape
   * @param type the type of shape
   * @param r    red color value
   * @param b    blue color value
   * @param g    green color value
   */
  public Shape(String name, ShapeType type, int r, int g, int b) {
    this(name, type, r, g, b, 50, 50);
  }

  /**
   * Modifies the current color to a new color.
   *
   * @param r red color value
   * @param b blue color value
   * @param g green color value
   */
  public void setColor(int r, int g, int b) {
    this.r = r;
    this.b = b;
    this.g = g;
  }

  /**
   * Modifies the current position of this shape.
   *
   * @param newPos the new color to modify
   */
  public void setPosition(Position2D newPos) {
    this.position = newPos;
  }

  /**
   * Modifies the current width of this shape.
   *
   * @param newWidth new color to modify
   */
  public void setWidth(int newWidth) {
    this.width = newWidth;
  }

  /**
   * Modifies the current width of this shape.
   *
   * @param newHeight new color to modify
   */
  public void setHeight(int newHeight) {
    this.height = newHeight;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Returns the type of this shape.
   *
   * @return ShapeType of this shapes type
   */
  public ShapeType getType() {
    return this.type;
  }

  /**
   * Returns the r value of this shape.
   *
   * @return int of the shape's r color value
   */
  public int getRValue() {
    return this.r;
  }

  /**
   * Returns the b value of this shape.
   *
   * @return int of the shape's b color value
   */
  public int getBValue() {
    return this.b;
  }

  /**
   * Returns the g value of this shape.
   *
   * @return int of the shape's g color value
   */
  public int getGValue() {
    return this.g;
  }

  /**
   * Returns the position of this shape.
   *
   * @return Position of this shape
   */
  public Position2D getPosition() {
    return this.position;
  }

  /**
   * Returns the width of this shape.
   *
   * @return int of the shape's width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of this shape.
   *
   * @return int of the shape's height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the time of this shape state.
   *
   * @return int of the shape's time.
   */
  public int getTime() {
    return this.time;
  }

  /**
   * Modifies the time of this shape.
   *
   * @param time time that this motion occurs
   */
  public void setTime(int time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "time: " + this.time + " type: " + this.type.toString() + ", color: (" + this.r
        + " " + this.b + " " + this.g + ")" + ", position: " + this.position.toString() + ", size: "
        + this.width + " " + this.height + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Shape)) {
      return false;
    } else {
      Shape that = (Shape) o;

      return (this.r + this.b + this.g == that.r + that.b + that.g)
          && this.position.equals(that.position) && this.width == that.width
          && this.height == that.height && this.time == that.time;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.type, this.r, this.b, this.g,
        this.position, this.width, this.height, this.time);
  }


}
