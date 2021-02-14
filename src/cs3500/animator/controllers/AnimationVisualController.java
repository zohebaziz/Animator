package cs3500.animator.controllers;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

/**
 * Animation Controller interface that mainly focuses on an interactive visual view of
 * and Animation.
 */
public class AnimationVisualController implements AnimationController, Features {

  private final AnimationModel model;
  private final AnimationView view;
  private int ticks;
  private int speed;
  private Timer timer;
  private boolean loop;
  private boolean shouldPause;

  /**
   * Basic AnimationVisualController constructor.
   * @param model the model in which to call commands of.
   * @param view the view to display the given commands.
   */
  public AnimationVisualController(AnimationModel model, AnimationView view) {
    this.model = model;
    this.view = view;
    this.ticks = 0;
    this.speed = 1;
    this.loop = false;
    this.shouldPause = false;
    this.configureButtonListener();
  }

  /**
   * Testing constructor for AnimationVisualController.
   * @param view the view that should display the Animation.
   */
  public AnimationVisualController(AnimationView view) {

    this.model = new BasicAnimationModel();
    this.view = view;
    this.ticks = 0;
    this.speed = 1;
    this.loop = false;
    this.shouldPause = false;
  }

  /**
   * Initializes the buttons of the given view so that the buttons are functional for the
   * user.
   */
  private void configureButtonListener() {
    Map<String,Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Restart", this::restart);
    buttonClickedMap.put("Increment", () -> editSpeed(1));
    buttonClickedMap.put("Decrement", () -> editSpeed(-1));
    buttonClickedMap.put("Loop", this::loop);
    buttonClickedMap.put("Pause", this::pauseOrStart);

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  @Override
  public void start(Appendable out, int speed) throws IOException {
    this.speed = speed;

    this.timer = new Timer(1 / speed * 1000, event -> {
      this.view.update(this.model.shapesAtTime(this.ticks));
      this.ticks++;
      if (this.loop && this.ticks >= this.model.getFinalTime()) {
        this.ticks = 0;
      }
    });
    this.view.render(model, out, speed);
    this.timer.setRepeats(true);
    this.timer.start();
  }

  @Override
  public void pauseOrStart() {
    this.shouldPause = !this.shouldPause;
    if (shouldPause) {
      this.timer.stop();
    } else {
      this.timer.start();
    }
  }

  @Override
  public void editSpeed(int inc) {
    this.speed += inc;
    if (this.speed > 0) {
      this.timer.setDelay((int) ((double) 1 / speed * 1000));
    }
  }

  @Override
  public void restart() {
    this.ticks = 0;
  }

  @Override
  public void loop() {
    this.loop = !this.loop;
  }
}
