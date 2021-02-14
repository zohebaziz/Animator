package cs3500.animator.controllers;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.view.AnimationView;
import java.io.IOException;

/**
 * Basic controller for View classes that don't have complicated visualizations.
 */
public class BasicAnimationController implements AnimationController {

  private final AnimationModel model;
  private final AnimationView view;

  public BasicAnimationController(AnimationModel model, AnimationView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void start(Appendable out, int speed) throws IOException {
    this.view.render(model, out, speed);
  }
}
