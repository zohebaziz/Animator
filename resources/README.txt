=======Homework 5==========

Model Overview:

Our model has four basic commands describe in the interface:
  1. Creating new Shapes
    - At the very minimum a shape name and type need to be specified

  2. The motion a Shape (Such as size, position, and color)
    - This method takes in parameters as a start and end times, new position, width, height, and an rbg value
      - If a shape doesn't do anything for a period of time then the input parameters will simply be equal to the the specified shape's current data
    - Should any inputs be invalid such as a negative width, height, or rgb colors or the specified shape doesn't exists an IllegalArgumentException will be thrown

  3. Removing a shape from the animation should the specified shape exist
    - This givens more control over the animation to add and remove new shapes as the user describes

  4. Returning a log of the commands / changes to a shape
    - Allows us to determine a textual representation of the animation based on the command calls

  Then our BasicAnimationModel adds in a edit and move methods that the motion method calls to delegate handling certain operations

  Our basic model uses a Shape class that we created representing the various elements of a shape such as:
    - Shape Name (equivalent to the key in the model's map)
    - Type of shape (Circle, Square, Oval, Rectangle)
    - Width and Height
    - Position (as a Position2D)
    - Color (As represented by three ints r, g, & b)
    - Speed (to be possibly be used in future parts of this project)
  The Shape class allows us to be aware of what kinds of shapes are in the animation without needing to know the implementation of the controller or view

  The Position2D class was shown in lecture several weeks ago and serves as a way to represent a shape in a 2D place with an x and y position.

=======Homework 6==========

Changes from Homework 5:
 - Redesigned part of the model to better represent an actual animation with a map keeping track of all the different motions that occur
 - Removed log field as frame field allowed easier access to display information
 - Using the new frames field changed the original TextView to fit the description of the homework
 - Removed the Triangle ShapeType because we didn't need it after all
 - Added ways to remove motions to give more flexibility to the animation and how the user can control what the animation looks like

Three Views to Rule Them All:

  Textual View:

    Using the map of maps of all motions that occur at specified ticks we convert that into a map of each shape.
    With each shape we iterate through its respective motions formatting as specified in the homework with a motion and its end state.
    Utilizing the accessors of a shape we're able to format this into a string fairly easily.

  SVG View:

    This followed a similar pattern to the previous view. For the SVG we also convert the map of frames into a map of shapes with a list
    of its respective motions. From that we generate the respective SVG tags. To make our lives simpler instead of determining which
    attributes change we create <animate> tags for each attribute, so even if a single attribute isn't modified it doesn't ruin the animation.

  Visual View:

    Probably the most difficult of the three this view extends the JFrame class to act as the canvas that the animation will appear on.
    From that a ShapePanel is created which will hold the visual information for all the different shapes. In the ShapePanel we keep track of
    what shapes have been drawn so we have access to the references of the data we need to modify a shape's attributes. To handle the different
    motions at different times we check if the tick the animation is on is equivalent to a frame from the model. From there we check if any of the
    shapes from the animation frame have not been drawn yet, and if not we add it to the drawnShapes list. From there we find the next end state of
    each shape (if there are any.) Next for each shape currently in the animation we use the given linear interpolation function to modify the
    attributes.


  *Note:

    There are a few aspects of the views that could probably be put into some kind of abstract class; however, since the visual view didn't use
    all of the same aspects as the text or svg view (or at least not in the same areas) we decided not to create such an abstract class. (also the
    hw deadline was approaching)

  Excellence:

    Acts as our Main class to handle command line inputs. Since these inputs can be in any order variables are used to keep track of the different
    user inputs. The -out specifier was fairly difficult as it could be output as a file or to the console or something else, so we used a boolean tag
    if a the output of the animation was a file. This allowed us to open and close a fileWriter outside of the switched statement in the main method.

    Much like the playGame(...) method of the previous homework assignments our Render took in the AnimationModel, Appendable output, and an int for speed
    as the factory class wouldn't be able to take in these inputs.

=========Homework 7===========
Changes from HW6:
  - Refactored the Model interface with two new methods:
      - shapesAtTime(...) returns a list of shapes and their state at a given tick
      - getFinalTime(...) returns the final tick value of an animation
  - Refactored the BasicAnimationModel to use a Map<Integer, List<Shape>> instead of a Map<Integer, Map<String, Shape>>. This preserved the ordering of
    shapes with how they are drawn so background shapes will now always stay in the background
  - Refactored the VisualAnimationView so it does little of the actual computation of all the shape's states and now merely draws a list of shapes as described
    by the Update(...) method.
  - Added a createController(...) method in the AnimationFactory because the implementation and usage of the controller between the different views varies.
    For example both the SVG and Textual views don't need a timer or button functionality, so different controllers were created.

Interactive View:

  Using the original VisualAnimationView as a component the InteractiveAnimationView uses the JFrame canvas already created by the VisualView and
  simply adds JButtons and JToggle buttons to it. Each JButton is added to a FlowPanel which is placed at the top of the canvas. Each of the buttons'
  functionality is through the addActionListener(...) methods allowing the Controller to handle the functionality of the different buttons

ButtonListener:

  Borrowed from lecture notes, the ButtonListener class contains a list of Strings as keys and different ActionListeners as the values. This allows all
  button functionality to be stored in one place that the view can easily access and depending on the button easily retrieve the necessary ActionListener.

AnimationVisualController:

  Unlike last week, all the functionality for controlling the animation has been moved out of the VisualView and into this controller. Here we handle
  the Timer, incrementing the ticks, and providing the View with the necessary list of Shapes to draw. We also implement the Features interface which acts
  as a way to see which actions a user can make on the Interactive View. The class also handles generating the specific actions that each button will take
  when called.

Features Interface:

  Simply this interface specifies the different functions each button has. While there can be an argument that as more controls are added the interface could
  become unwieldy, the central location for describing the functionality makes understanding the capabilities of the project easier.

BubbleSort Class:

  Serves as a way to algorithmically generate an animation visualizing the bubble sort algorithm. When the class is run a text file is created of the
  animation that can be run through the Excellence class the generate the animation.
