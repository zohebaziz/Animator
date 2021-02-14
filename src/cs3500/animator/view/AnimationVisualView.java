package cs3500.animator.view;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.Canvas;
import cs3500.animator.models.Shape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;

/**
 * A view that allows a visual representation of the animation using java Swing.
 */
public class AnimationVisualView extends JFrame implements AnimationView {

  private final ShapePanel shapePanel;

  /**
   * Basic constructor initializing all the fields.
   */
  public AnimationVisualView() {
    super();
    this.shapePanel = new ShapePanel();
  }

  @Override
  public void render(AnimationModel model, Appendable out, int speed) {
    if (speed > 0) {
      Canvas canvas = model.getCanvas();
      this.setTitle("Animation");
      this.setSize(canvas.getCanvasWidth(), canvas.getCanvasHeight());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      this.setLayout(new BorderLayout());
      shapePanel.setPreferredSize(new Dimension(canvas.getCanvasWidth(), canvas.getCanvasHeight()));
      this.getContentPane().add(shapePanel);

      this.setVisible(true);
    } else {
      throw new IllegalArgumentException("Invalid speed");
    }
  }

  @Override
  public void update(List<Shape> shapesToDraw) {
    this.shapePanel.setDrawnShapes(shapesToDraw);
    this.repaint();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    /*
    This is empty because in the constructor for the AnimationVisualController,
    the configureButtons(...) method is called which in turn calls View.addActionListener(...).
    If an exception was thrown here then we would =n't be able to use our controller, but since
    the method is void calling an empty method will simply not do anything. We know it's not the
    best design to have an empty method like this, but we figured again with the
    void return type it might be acceptable to do.
   */
  }
}
