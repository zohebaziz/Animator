package cs3500.animator.models;

import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Basic implementation of an AnimationModel. Uses the Shape class to create, move or remove shapes
 * to create an animation.
 */
public final class BasicAnimationModel implements AnimationModel {

  // The Methods ensure that each shape has a unique key so when handling a shape's motion it's
  // easy to figure out which shape needs to be edited.
  private final Map<String, Shape> allShapes;
  private final Map<Integer, List<Shape>> frames;
  private Canvas canvas;

  /**
   * Default constructor for BasicAnimationModel. Constructs all fields to default values.
   */
  public BasicAnimationModel() {
    this.frames = new HashMap<>();
    this.allShapes = new HashMap<>();
    this.canvas = new Canvas();
  }

  /**
   * Test constructor for BasicAnimationModel that allows inputs for all of the fields.
   *
   * @param allShapes the map that will hold all the shape values.
   */
  public BasicAnimationModel(Map<String, Shape> allShapes) {
    this.allShapes = allShapes;
    this.frames = new HashMap<>();
    this.canvas = new Canvas();
  }

  @Override
  public void create(String name, ShapeType type) {
    this.keyNotUsed(name);
    this.allShapes.put(name, new Shape(name, type, 0, 0, 0, 0, 0));
  }

  @Override
  public void create(String name, ShapeType type, int r, int g, int b, Position2D position,
      int width,
      int height) throws IllegalArgumentException {
    this.validSize(width, height);
    this.keyNotUsed(name);
    this.allShapes.put(name, new Shape(name, type, 0, r, g, b, position, width, height));
  }

  @Override
  public void create(String name, ShapeType type, int r, int g, int b, int width, int height)
      throws IllegalArgumentException {
    this.validSize(width, height);
    this.keyNotUsed(name);
    this.allShapes.put(name, new Shape(name, type, r, g, b, width, height));
  }

  @Override
  public void create(String name, ShapeType type, int r, int g, int b)
      throws IllegalArgumentException {
    this.keyNotUsed(name);
    this.allShapes.put(name, new Shape(name, type, r, g, b));
  }

  @Override
  public void create(String name, ShapeType type, int width, int height)
      throws IllegalArgumentException {
    this.validSize(width, height);
    this.keyNotUsed(name);
    this.allShapes.put(name, new Shape(name, type, width, height));
  }

  @Override
  public void motion(String shapeName, int t, Position2D newPos, int w, int h, int r,
      int g, int b) throws IllegalArgumentException {
    if (this.allShapes.containsKey(shapeName) && w <= this.canvas.getCanvasWidth()
        && h <= this.canvas.getCanvasHeight() && t >= 0 && r >= 0 && b >= 0 && g >= 0 && r <= 255
        && b <= 255 && g <= 255) {
      for (Integer i : new TreeSet<>(this.frames.keySet())) {
        if (i > t) {
          if (this.containsShape(this.frames.get(i), shapeName)) {
            throw new IllegalArgumentException("Overlapping motion");
          }
        }
      }

      Shape editedShape = this.allShapes.get(shapeName);

      this.move(shapeName, newPos);
      this.edit(shapeName, r, g, b, w, h);

      if (this.frames.containsKey(t)) {
        frames.get(t).add(new Shape(shapeName, editedShape.getType(), t, r, g, b, newPos, w, h));
      } else {
        List<Shape> shapeStates = new ArrayList<>();
        shapeStates.add(new Shape(shapeName, editedShape.getType(), t, r, g, b, newPos, w, h));
        frames.put(t, shapeStates);
      }
    } else {
      throw new IllegalArgumentException("Invalid parameters");
    }
  }

  /**
   * Determines if the list of shapes contains a shape of the given name.
   *
   * @param lst  The list of shapes to check
   * @param name The name of the shape to check for
   * @return true if the a shape has the given name, false otherwise
   */
  private boolean containsShape(List<Shape> lst, String name) {
    for (Shape s : lst) {
      if (name.equals(s.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper to throw an IllegalArgumentException if the given width and height are less than one.
   *
   * @param width  of the shape to be created
   * @param height of the shape to be created
   * @throws IllegalArgumentException if either parameters are less than one.
   */
  private void validSize(int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Checks if the name of a shape is already in use. Makes sure each shape has a unique name to
   * make movement easier.
   *
   * @param key name of the shape to be created
   * @throws IllegalArgumentException if the map has a key of the same name
   */
  private void keyNotUsed(String key) throws IllegalArgumentException {
    if (this.allShapes.containsKey(key)) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * ` Move a given shape to a new position.
   *
   * @param name     Name of the shape to be moved
   * @param endPoint new Position of the shape.
   */
  private void move(String name, Position2D endPoint) {
    if (allShapes.containsKey(name)) {
      this.allShapes.get(name).setPosition(endPoint);
    } else {
      throw new IllegalArgumentException("Invalid shape to move");
    }
  }

  /**
   * Edits the various aspects of a Shape including its color and size.
   *
   * @param name   of the shape to be edited
   * @param r      red color value to change
   * @param b      blue color value to change
   * @param g      green color value to change
   * @param width  new width of the shape
   * @param height new height of the shape
   */
  private void edit(String name, int r, int b, int g, int width, int height) {
    if (this.allShapes.containsKey(name)) {
      this.allShapes.get(name).setColor(r, b, g);
      this.allShapes.get(name).setWidth(width);
      this.allShapes.get(name).setHeight(height);
    } else {
      throw new IllegalArgumentException("Invalid shape name.");
    }
  }

  @Override
  public void remove(String name) throws IllegalArgumentException {
    if (this.allShapes.containsKey(name)) {
      this.allShapes.remove(name);
    } else {
      throw new IllegalArgumentException("Invalid Shape name");
    }
  }

  @Override
  public Map<Integer, List<Shape>> getFrames() {
    return this.frames;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas = new Canvas(x, y, width, height);
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }

  @Override
  public Map<String, Shape> getAllShapes() {
    return this.allShapes;
  }

  @Override
  public void removeMotion(String name, int time) {
    if (this.frames.containsKey(time) && this.allShapes.containsKey(name)) {
      this.frames.get(time).remove(this.findShape(this.frames.get(time), name));
      if (this.frames.get(time).isEmpty()) {
        this.frames.remove(time);
      }
    } else {
      throw new IllegalArgumentException("Shape or time doesn't exist.");
    }
  }

  /**
   * Finds a specific shape during a frame.
   *
   * @param lst  The list of shapes to check
   * @param name name of the shape to find
   * @return the Shape from the given list with the provided name
   */
  private Shape findShape(List<Shape> lst, String name) {
    for (Shape s : lst) {
      if (name.equals(s.getName())) {
        return s;
      }
    }
    return null;
  }

  @Override
  public List<Shape> shapesAtTime(int tick) {
    List<Shape> prevState = new ArrayList<>();
    List<Shape> nextState = new ArrayList<>();
    List<Shape> currState = new ArrayList<>();
    for (int i = 0; i <= tick; i++) {
      if (this.frames.containsKey(i)) {
        for (Shape s : this.frames.get(i)) {
          prevState.remove(this.findShape(prevState, s.getName()));
          prevState.add(s);
        }
      }
    }

    for (Integer i : new TreeSet<>(this.frames.keySet())) {
      if (i > tick) {
        for (Shape s : this.frames.get(i)) {
          if (!this.containsShape(nextState, s.getName())
              && this.containsShape(prevState, s.getName())) {
            nextState.add(s);
          }
        }
      }
    }

    for (Shape s : prevState) {

      if (!this.containsShape(nextState, s.getName())) {
        nextState.add(s);
      }

      Shape nextShape = this.findShape(nextState, s.getName()) == null ? s
          : this.findShape(nextState, s.getName());

      if (!nextShape.equals(s)) {
        int newX = this.linearInterpolation((int) s.getPosition().getX(),
            (int) nextShape.getPosition().getX(), s.getTime(), nextShape.getTime(),
            tick);
        int newY = this.linearInterpolation((int) s.getPosition().getY(),
            (int) nextShape.getPosition().getY(), s.getTime(), nextShape.getTime(),
            tick);
        int newWidth = this
            .linearInterpolation(s.getWidth(), nextShape.getWidth(), s.getTime(),
                nextShape.getTime(), tick);
        int newHeight = this
            .linearInterpolation(s.getHeight(), nextShape.getHeight(), s.getTime(),
                nextShape.getTime(), tick);
        int newR = this
            .linearInterpolation(s.getRValue(), nextShape.getRValue(), s.getTime(),
                nextShape.getTime(), tick);
        int newG = this
            .linearInterpolation(s.getGValue(), nextShape.getGValue(), s.getTime(),
                nextShape.getTime(), tick);
        int newB = this
            .linearInterpolation(s.getBValue(), nextShape.getBValue(), s.getTime(),
                nextShape.getTime(), tick);

        Shape newShape = new Shape(s.getName(), s.getType(), tick, newR, newG, newB,
            new Position2D(newX, newY), newWidth, newHeight);

        currState.add(newShape);
      } else {
        Shape newShape = new Shape(s.getName(), s.getType(), tick, s.getRValue(), s.getGValue(),
            s.getBValue(),
            new Position2D(s.getPosition().getX(), s.getPosition().getY()), s.getWidth(),
            s.getHeight());

        currState.add(newShape);
      }

    }

    return currState;
  }

  /**
   * An implementation of the mathematical linear interpolation function.
   *
   * @param a  initial state of attribute
   * @param b  end state of attribute
   * @param t1 beginning time
   * @param t2 end time
   * @param t  current time
   * @return an int of the next linear value of the provided data
   */
  private int linearInterpolation(int a, int b, double t1, double t2, double t) {
    double calculation = (a * ((t2 - t) / (t2 - t1))) + (b * ((t - t1) / (t2 - t1)));
    return (int) Math.round(calculation);
  }

  @Override
  public int getFinalTime() {
    int finalTime = 0;
    for (Integer i : frames.keySet()) {
      if (i > finalTime) {
        finalTime = i;
      }
    }
    return finalTime;
  }

  /**
   * Implementation of the AnimationBuilder class. Helps the animation builder interface work
   * specifically with this given model.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private final AnimationModel model = new BasicAnimationModel();

    @Override
    public AnimationModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      this.model.create(name, ShapeType.fromString(type));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      this.model.motion(name, t1, new Position2D(x1, y1), w1, h1, r1, g1, b1);
      this.model.motion(name, t2, new Position2D(x2, y2), w2, h2, r2, g2, b2);
      return this;
    }
  }
}
