package cs3500.animator.view;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Shape;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Textual implementation of a view class for AnimationModel. Prints out a textual view of an
 * animation based off of a given model.
 */
public class AnimationTextView implements AnimationView {

  private AnimationModel model;
  private Appendable out;
  private int speed;

  /**
   * Basic constructor initializing all the fields.
   */
  public AnimationTextView() {
    this.model = new BasicAnimationModel();
    this.out = new StringBuilder();
    this.speed = 1;
  }

  /**
   * Constructs an AnimationTextView.
   *
   * @param model A model that is used to base the textual representation of off.
   */
  public AnimationTextView(AnimationModel model, Appendable output, int speed) {
    this.model = model;
    this.speed = speed;
    this.out = output;
  }

  @Override
  public String toString() {
    Map<String, List<Shape>> allMotions = generateShapeMotions();
    StringBuilder textView = new StringBuilder(this.model.getCanvas().toString() + "\n");
    for (String key : allMotions.keySet()) {
      String shapeType = allMotions.get(key).get(0).getType().toString();
      textView.append("shape ").append(key).append(" ").append(shapeType).append("\n");
      List<Shape> shapeMotions = allMotions.get(key);
      for (int i = 1; i < shapeMotions.size(); i++) {
        Shape prevMotion = shapeMotions.get(i - 1);
        Shape currMotion = shapeMotions.get(i);
        textView.append("motion ").append(key).append(" ")
            .append(this.shapeStringData(prevMotion, prevMotion.getTime())).append(" ")
            .append(this.shapeStringData(currMotion, currMotion.getTime())).append("\n");

      }
    }
    return textView.toString();
  }

  private Map<String, List<Shape>> generateShapeMotions() {
    Map<Integer, List<Shape>> animationFrames = this.model.getFrames();
    Map<String, List<Shape>> shapeStates = new HashMap<>();

    for (Integer key : new TreeSet<>(animationFrames.keySet())) {
      List<Shape> singleFrame = animationFrames.get(key);
      for (Shape shape : singleFrame) {
        if (shapeStates.containsKey(shape.getName())) {
          shapeStates.get(shape.getName()).add(singleFrame.get(singleFrame.indexOf(shape)));
        } else {
          List<Shape> shapeMotions = new ArrayList<>();
          shapeMotions.add(singleFrame.get(singleFrame.indexOf(shape)));
          shapeStates.put(shape.getName(), shapeMotions);
        }
      }
    }
    return shapeStates;
  }


  private String shapeStringData(Shape s, int time) {
    return ((double) time / speed) * 1000 + " " + s.getPosition().getX() + " " + s.getPosition()
        .getY() + " "
        + s
        .getWidth() + " " + s.getHeight() + " " + s.getRValue() + " " + s.getGValue() + " " + s
        .getBValue();
  }

  @Override
  public void render(AnimationModel model, Appendable out, int speed) throws IOException {
    if (speed > 0) {
      this.model = model;
      this.out = out;
      this.speed = speed;
      this.out.append(this.toString());
    } else {
      throw new IllegalArgumentException("Invalid Speed");
    }
  }

  @Override
  public void update(List<Shape>  shapesToDraw) {
    throw new UnsupportedOperationException("No shapes need to be drawn in this view");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    throw new UnsupportedOperationException(
        "Cannot add actions when buttons do not exist in this view.");
  }
}
