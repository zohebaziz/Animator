import static org.junit.Assert.assertEquals;

import cs3500.animator.models.AnimationModel;
import cs3500.animator.models.BasicAnimationModel;
import cs3500.animator.models.Position2D;
import cs3500.animator.models.Shape.ShapeType;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.AnimationSVGView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Test class for the SVG animation view.
 */
public class TestAnimationSVGView {

  @Test
  public void testToString() {

    AnimationModel model = new BasicAnimationModel();
    AnimationView svg = new AnimationSVGView(model, new StringBuilder(), 1);

    model.create("R1", ShapeType.RECTANGLE, 3, 5);

    model.motion("R1", 0, new Position2D(100, 50), 3, 5, 0, 0, 0);
    model.motion("R1", 1, new Position2D(10, 50), 3, 5, 0, 50, 0);
    model.motion("R1", 3, new Position2D(10, 70), 5, 3, 0, 100, 0);

    assertEquals(
        "<svg width=\"200\" height=\"200\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R1\" x=\"100.0\" y=\"50.0\" width=\"3\" height=\"5\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeName=\"x\" "
            + "from=\"100.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeName=\"y\" "
            + "from=\"50.0\" to=\"50.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeName=\"width\" "
            + "from=\"3\" to=\"3\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
            + "attributeName=\"height\" from=\"5\" to=\"5\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeName=\"fill\" "
            + "from=\"rgb(0,0,0)\" to=\"rgb(0,50,0)\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"2000ms\" attributeName=\"x\" "
            + "from=\"10.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"2000ms\" attributeName=\"y\" "
            + "from=\"50.0\" to=\"70.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"2000ms\" "
            + "attributeName=\"width\" from=\"3\" to=\"5\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" "
            + "dur=\"2000ms\" attributeName=\"height\" from=\"5\" to=\"3\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"2000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(0,50,0)\" to=\"rgb(0,100,0)\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.toString());
  }

  @Test
  public void testRender() throws IOException {
    AnimationModel model = new BasicAnimationModel();
    StringBuilder out = new StringBuilder();
    AnimationView svg = new AnimationSVGView(model, out, 1);

    model.create("c", ShapeType.OVAL, 20, 30);

    model.motion("c", 0, new Position2D(10, 10), 20, 30, 255, 165, 0);
    model.motion("c", 5, new Position2D(100, 10), 40, 40, 255, 165, 0);
    model.motion("c", 7, new Position2D(100, 10), 40, 40, 0, 165, 0);

    svg.render(model, out, 1);

    assertEquals(
        "<svg width=\"200\" height=\"200\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<ellipse id=\"c\" cx=\"10.0\" cy=\"10.0\" rx=\"20\" ry=\"30\" "
            + "fill=\"rgb(255,165,0)\" "
            + "visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"cx\" "
            + "from=\"10.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"cy\" "
            + "from=\"10.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"rx\" "
            + "from=\"20\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"ry\" "
            + "from=\"30\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"fill\" "
            + "from=\"rgb(255,165,0)\" to=\"rgb(255,165,0)\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" attributeName=\"cx\" "
            + "from=\"100.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" attributeName=\"cy\""
            + " from=\"10.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" attributeName=\"rx\" "
            + "from=\"40\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"40\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255,165,0)\" to=\"rgb(0,165,0)\"/>\n"
            + "</ellipse>\n"
            + "</svg>", out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSpeed() throws IOException {
    AnimationModel model = new BasicAnimationModel();
    StringBuilder out = new StringBuilder();
    AnimationView svg = new AnimationSVGView();

    model.create("c", ShapeType.OVAL, 20, 30);

    model.motion("c", 0, new Position2D(10, 10), 20, 30, 255, 165, 0);
    model.motion("c", 5, new Position2D(100, 10), 40, 40, 255, 165, 0);
    model.motion("c", 7, new Position2D(100, 10), 40, 40, 0, 165, 0);

    svg.render(model, out, 0);
  }

  @Test
  public void testRenderMultipleShapes() throws IOException {
    AnimationModel model = new BasicAnimationModel();
    StringBuilder out = new StringBuilder();
    AnimationView svg = new AnimationSVGView();

    model.create("c", ShapeType.OVAL, 20, 30);
    model.create("r", ShapeType.RECTANGLE, 100, 100);

    model.motion("c", 0, new Position2D(10, 10), 20, 30, 255, 165, 0);
    model.motion("c", 5, new Position2D(100, 10), 40, 40, 255, 165, 0);
    model.motion("c", 7, new Position2D(100, 10), 40, 40, 0, 165, 0);
    model.motion("r", 0, new Position2D(50, 50), 100, 100, 255, 165, 0);
    model.motion("r", 4, new Position2D(200, 200), 100, 100, 255, 165, 255);

    svg.render(model, out, 1);

    assertEquals(
        "<svg width=\"200\" height=\"200\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"r\" x=\"50.0\" y=\"50.0\" width=\"100\" height=\"100\" "
            + "fill=\"rgb(255,165,0)\""
            + " visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"4000ms\" attributeName=\"x\" "
            + "from=\"50.0\" to=\"200.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"4000ms\" attributeName=\"y\" "
            + "from=\"50.0\" to=\"200.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"4000ms\" "
            + "attributeName=\"width\" "
            + "from=\"100\" to=\"100\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"4000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100\" to=\"100\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"4000ms\" attributeName=\"fill\" "
            + "from=\"rgb(255,165,0)\" to=\"rgb(255,165,255)\"/>\n"
            + "</rect>\n"
            + "<ellipse id=\"c\" cx=\"10.0\" cy=\"10.0\" rx=\"20\" ry=\"30\""
            + " fill=\"rgb(255,165,0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"cx\" "
            + "from=\"10.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"cy\" "
            + "from=\"10.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"rx\" "
            + "from=\"20\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"ry\" "
            + "from=\"30\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"fill\" "
            + "from=\"rgb(255,165,0)\" to=\"rgb(255,165,0)\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"cx\" from=\"100.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"cy\" from=\"10.0\" to=\"10.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"rx\" from=\"40\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"ry\" from=\"40\" to=\"40\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,165,0)\" to=\"rgb(0,165,0)\"/>\n"
            + "</ellipse>\n"
            + "</svg>", out.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotUpdateSVG() {
    AnimationView view = new AnimationSVGView();

    view.update(new ArrayList<>());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotAddActionListener() {
    AnimationView view = new AnimationSVGView();
    view.addActionListener(new ButtonListener());
  }
}
