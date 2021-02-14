package cs3500.animator.view;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Canvas;
import cs3500.animator.models.Shape;
import cs3500.animator.models.Shape.ShapeType;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * A view that outputs text in a SVG format that can be saved and run in a browser.
 */

public class AnimationSVGView implements AnimationView {

  private AnimationModel model;
  private Appendable out;
  private int speed;

  /**
   * Basic constructor to initialize fields.
   */
  public AnimationSVGView() {
    this.model = new BasicAnimationModel();
    this.out = new StringBuilder();
    this.speed = 1;
  }

  /**
   * Constructs a SVG Animation view.
   *
   * @param model The given model in which to base the view off of.
   * @param out   The appendable given that will handle the output type.
   * @param speed The speed of the animations in the view.
   */
  public AnimationSVGView(AnimationModel model, Appendable out, int speed) {
    this.model = model;
    this.out = out;
    this.speed = speed;
  }

  @Override
  public String toString() {
    Canvas canvas = this.model.getCanvas();
    return "<svg width=\"" + canvas.getCanvasWidth() + "\" height=\"" + canvas.getCanvasHeight()
        + "\"" + " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" + this.drawAllShapes()
        + "</svg>";
  }

  /**
   * Helper method that will iterate through all the shapes and motions in the model.
   *
   * @return SVG formatted string of all the shapes and its motions.
   */
  private String drawAllShapes() {

    /*
    Have list of list for each shape and the motions of each shape
    Need to iterate through the frames and for each motion in a frame
    add it to the corresponding shape in the list of lists
     */
    StringBuilder shapeSVG = new StringBuilder();
    Map<Integer, List<Shape>> animationFrames = this.model.getFrames();
    List<List<Shape>> shapeStates = new ArrayList<>();
    for (Integer key : new TreeSet<>(animationFrames.keySet())) {
      List<Shape> singleFrame = animationFrames.get(key);
      for (Shape shape : singleFrame) {
        if (!this.containsShapeList(shapeStates, shape.getName())) {
          List<Shape> shapeMotions = new ArrayList<>();
          shapeMotions.add(singleFrame.get(singleFrame.indexOf(shape)));
          shapeStates.add(shapeMotions);
        } else {
          shapeStates.get(this.findShape(shapeStates, shape.getName()))
              .add(singleFrame.get(singleFrame.indexOf(shape)));
        }
      }
    }

    for (List<Shape> shape : shapeStates) {
      shapeSVG.append(this.drawSingleShape(shape.get(0).getName(), shape.get(0).getType(), shape))
          .append("\n");
    }

    return shapeSVG.toString();
  }

  /**
   * Helper that actual builds each SVG formatted String for the given shape.
   *
   * @param name    name of the given shape to output.
   * @param type    type of the shape to output.
   * @param motions the motions of the given shape.
   * @return SVG formatted string for a shape and its motions.
   */
  private String drawSingleShape(String name, ShapeType type, List<Shape> motions) {
    StringBuilder svg = new StringBuilder();
    Shape initShape = motions.get(0);

    if (isRectangleOrSquare(type)) {
      svg.append("<rect ");

      svg.append("id=\"").append(name).append("\" ").append("x=\"")
          .append(initShape.getPosition().getX()).append("\" ").append("y=\"")
          .append(initShape.getPosition().getY()).append("\" ").append("width=\"")
          .append(initShape.getWidth()).append("\" ").append("height=\"")
          .append(initShape.getHeight()).append("\" ").append("fill=\"rgb(")
          .append(initShape.getRValue()).append(",").append(initShape.getGValue()).append(",")
          .append(initShape.getBValue()).append(")\" visibility=\"visible\">\n");
    } else {
      svg.append("<ellipse ");

      svg.append("id=\"").append(name).append("\" ").append("cx=\"")
          .append(initShape.getPosition().getX()).append("\" ").append("cy=\"")
          .append(initShape.getPosition().getY()).append("\" ").append("rx=\"")
          .append(initShape.getWidth()).append("\" ").append("ry=\"")
          .append(initShape.getHeight()).append("\" ").append("fill=\"rgb(")
          .append(initShape.getRValue()).append(",").append(initShape.getGValue()).append(",")
          .append(initShape.getBValue()).append(")\" visibility=\"visible\">\n");
    }

    for (int i = 1; i < motions.size(); i++) {

      Shape prevMotion = motions.get(i - 1);
      Shape currMotion = motions.get(i);

      if (this.isRectangleOrSquare(type)) {
        svg.append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000).append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"x\" from=\"").append(prevMotion.getPosition().getX())
            .append("\"").append(" to=\"")
            .append(currMotion.getPosition().getX()).append("\"").append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"y\" from=\"").append(prevMotion.getPosition().getY())
            .append("\"").append(" to=\"").append(currMotion.getPosition().getY()).append("\"")
            .append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"width\" from=\"").append(prevMotion.getWidth())
            .append("\"").append(" to=\"").append(currMotion.getWidth()).append("\"")
            .append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"height\" from=\"").append(prevMotion.getHeight())
            .append("\"").append(" to=\"").append(currMotion.getHeight()).append("\"")
            .append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"fill\" from=").append("\"rgb(")
            .append(prevMotion.getRValue()).append(",").append(prevMotion.getGValue()).append(",")
            .append(prevMotion.getBValue()).append(")\"")
            .append(" to=").append("\"rgb(")
            .append(currMotion.getRValue()).append(",").append(currMotion.getGValue()).append(",")
            .append(currMotion.getBValue()).append(")\"/>").append("\n");
      } else {

        svg.append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000).append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"cx\" from=\"").append(prevMotion.getPosition().getX())
            .append("\"").append(" to=\"")
            .append(currMotion.getPosition().getX()).append("\"").append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"cy\" from=\"").append(prevMotion.getPosition().getY())
            .append("\"").append(" to=\"").append(currMotion.getPosition().getY()).append("\"")
            .append(" fill=\"freeze\"/>\n").append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append(((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000)
            .append("ms\" ")
            .append("attributeName=\"rx\" from=\"").append(prevMotion.getWidth())
            .append("\"").append(" to=\"").append(currMotion.getWidth()).append("\"")
            .append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"ry\" from=\"").append(prevMotion.getHeight())
            .append("\"").append(" to=\"").append(currMotion.getHeight()).append("\"")
            .append(" fill=\"freeze\"/>\n")
            .append("<animate attributeType=\"xml\" begin=\"")
            .append((prevMotion.getTime() / this.speed) * 1000)
            .append("ms\" dur=\"")
            .append((((currMotion.getTime() - prevMotion.getTime()) / this.speed) * 1000))
            .append("ms\" ")
            .append("attributeName=\"fill\" from=").append("\"rgb(")
            .append(prevMotion.getRValue()).append(",").append(prevMotion.getGValue()).append(",")
            .append(prevMotion.getBValue()).append(")\"")
            .append(" to=").append("\"rgb(")
            .append(currMotion.getRValue()).append(",").append(currMotion.getGValue()).append(",")
            .append(currMotion.getBValue()).append(")\"/>").append("\n");
      }

    }

    if (this.isRectangleOrSquare(type)) {
      svg.append("</rect>");
    } else {
      svg.append("</ellipse>");
    }
    return svg.toString();
  }

  /**
   * Determines if a type is either a rectangle or square to create to correct svg shape.
   *
   * @param type the given ShapeType to check
   * @return True if the given type is a rectangle or square and false otherwise
   */
  private boolean isRectangleOrSquare(ShapeType type) {
    return type.equals(ShapeType.RECTANGLE) || type.equals(ShapeType.SQUARE);
  }

  @Override
  public void render(AnimationModel model, Appendable out, int speed) throws IOException {
    if (speed > 0) {
      this.model = model;
      this.out = out;
      this.speed = speed;
      this.out.append(this.toString());
    } else {
      throw new IllegalArgumentException("Invalid speed");
    }
  }

  @Override
  public void update(List<Shape> shapesToDraw) {
    throw new UnsupportedOperationException("No shapes need to be drawn in this view");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    throw new UnsupportedOperationException(
        "Cannot add actions when buttons do not exist in this view.");
  }

  /**
   * Determines if the list of shapes contains a shape of the given name.
   *
   * @param lst  The list of shapes to check
   * @param name The name of the shape to check for
   * @return true if the a shape has the given name, false otherwise
   */
  private boolean containsShapeList(List<List<Shape>> lst, String name) {
    for (List<Shape> shapeList : lst) {
      if (name.equals(shapeList.get(0).getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds a specific shape during a frame.
   *
   * @param lst  The list of shapes to check
   * @param name name of the shape to find
   * @return the Shape from the given list with the provided name
   */
  private int findShape(List<List<Shape>> lst, String name) {
    for (int i = 0; i < lst.size(); i++) {
      if (lst.get(i).get(0).getName().equals(name)) {
        return i;
      }
    }
    return 0;
  }
}
