package cs3500.animator.util;

import cs3500.animator.controllers.AnimationController;
import cs3500.animator.controllers.AnimationVisualController;
import cs3500.animator.controllers.BasicAnimationController;
import cs3500.animator.models.AnimationModel;
import cs3500.animator.view.AnimationInteractiveView;
import cs3500.animator.view.AnimationSVGView;
import cs3500.animator.view.AnimationTextView;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.AnimationVisualView;

/**
 * Factory class to initialize the view of an animation.
 */
public class AnimationFactory {

  /**
   * Creator method to initialize a view for an animation.
   *
   * @param type A String of the type of view to initialize
   * @return The initialized Animation View as specified by the given String
   */
  public static AnimationView createView(String type) {
    switch (type) {
      case "visual":
        return new AnimationVisualView();
      case "svg":
        return new AnimationSVGView();
      case "interactive":
        return new AnimationInteractiveView();
      default:
        return new AnimationTextView();
    }
  }

  /**
   * Creator method to initialize a controller for an animation.
   *
   * @param type A String of the type of view to initialize
   * @return The initialized Animation Controller as specified by the given String
   */
  public static AnimationController createController(String type, AnimationModel model,
      AnimationView view) {
    if (type.equals("svg") || type.equals("text")) {
      return new BasicAnimationController(model, view);
    } else if (type.equals("visual") || type.equals("interactive")) {
      return new AnimationVisualController(model, view);
    } else {
      throw new IllegalArgumentException("Unsupported view type");
    }
  }
}
