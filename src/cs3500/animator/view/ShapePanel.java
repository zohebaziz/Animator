package cs3500.animator.view;

import cs3500.animator.models.Shape;
import cs3500.animator.models.Shape.ShapeType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;

/**
 * Overridden JPanel class to animate shapes based on the AnimationModel.
 */
public final class ShapePanel extends JPanel {

  List<Shape> drawnShapes = new ArrayList<>();

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    for (Shape s : drawnShapes) {
      this.drawShape(s, g2D);
    }
  }

  private void drawShape(Shape thisShape, Graphics2D g2D) {
    int x = (int) thisShape.getPosition().getX();
    int y = (int) thisShape.getPosition().getY();
    int width = thisShape.getWidth();
    int height = thisShape.getHeight();
    Color color = new Color(thisShape.getRValue(), thisShape.getGValue(), thisShape.getBValue());
    g2D.setColor(color);

    if (thisShape.getType() == ShapeType.RECTANGLE || thisShape.getType() == ShapeType.SQUARE) {
      g2D.fillRect(x, y, width, height);
    } else if (thisShape.getType() == ShapeType.OVAL || thisShape.getType() == ShapeType.CIRCLE) {
      g2D.fillOval(x, y, width, height);
    }
  }

  /**
   * Updates each Shape in drawnShapes with new attributes or adds it to the map if it's not
   * present.
   *
   * @param updatedShapes The Map of updated shapes
   */
  public void setDrawnShapes(List<Shape> updatedShapes) {
    for (Shape updatedShape : updatedShapes) {
      if (!this.containsShape(drawnShapes, updatedShape.getName())) {
        drawnShapes.add(updatedShape);
      } else {
        Objects.requireNonNull(this.findShape(drawnShapes, updatedShape.getName()))
            .setTime(updatedShape.getTime());
        Objects.requireNonNull(this.findShape(drawnShapes, updatedShape.getName()))
            .setPosition(updatedShape.getPosition());
        Objects.requireNonNull(this.findShape(drawnShapes, updatedShape.getName()))
            .setHeight(updatedShape.getHeight());
        Objects.requireNonNull(this.findShape(drawnShapes, updatedShape.getName()))
            .setWidth(updatedShape.getWidth());
        Objects.requireNonNull(this.findShape(drawnShapes, updatedShape.getName()))
            .setColor(updatedShape.getRValue(), updatedShape.getGValue(), updatedShape.getBValue());
      }
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
}
