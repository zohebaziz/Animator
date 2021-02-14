package cs3500.animator.view;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.Shape;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Visual implementation of the AnimationView that allows a user with a control to interact with the
 * Animation and send some basic commands to it.
 */
public class AnimationInteractiveView implements AnimationView {

  private final AnimationVisualView anim;
  private final JPanel buttonPanel;
  private final JButton restart;
  private final JButton incSpeed;
  private final JButton decSpeed;
  private final JToggleButton loop;
  private final JToggleButton pause;

  /**
   * Default constructor for AnimationInteractiveView.
   */
  public AnimationInteractiveView() {
    this.anim = new AnimationVisualView();

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    this.restart = new JButton("Restart");
    this.restart.setActionCommand("Restart");
    this.incSpeed = new JButton("Increment Speed");
    this.incSpeed.setActionCommand("Increment");
    this.decSpeed = new JButton("Decrement Speed");
    this.decSpeed.setActionCommand("Decrement");

    this.loop = new JToggleButton("Loop");
    this.loop.setActionCommand("Loop");
    this.pause = new JToggleButton("Pause");
    this.pause.setActionCommand("Pause");

    this.buttonPanel.add(this.incSpeed);
    this.buttonPanel.add(this.decSpeed);
    this.buttonPanel.add(this.restart);
    this.buttonPanel.add(this.loop);
    this.buttonPanel.add(this.pause);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    this.incSpeed.addActionListener(actionListener);
    this.decSpeed.addActionListener(actionListener);
    this.restart.addActionListener(actionListener);
    this.loop.addActionListener(actionListener);
    this.pause.addActionListener(actionListener);
  }

  @Override
  public void render(AnimationModel model, Appendable out, int speed)
      throws IllegalArgumentException {
    this.anim.render(model, out, speed);
    this.anim.getContentPane().add(buttonPanel, BorderLayout.NORTH);
  }

  @Override
  public void update(List<Shape> shapesToDraw) {
    this.anim.update(shapesToDraw);
  }
}
