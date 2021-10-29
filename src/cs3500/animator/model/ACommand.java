package cs3500.animator.model;

/**
 * Changes made here: -We add a new constructor, instead of takes a IShape as its parameter, this
 * one takes in the name of the shape which particular for the reader to read the information of a
 * file. -We add a helper method, toStringTime(int t), for getState. -We add a method,
 * calInter(float a, int currTime, float b), to calculate the intermediate state. -We add a method,
 * getShapeName(), to return the shape's name. -We add a method, svgGetSize(String a), to return the
 * x and y values of a shape in svg format. -We add a method, svgGetPos(String a), to return the x
 * and y values of a shape in svg format.
 */

/**
 * An abstract class represents command, that implements ICommand and helps to handle all of the
 * common fields and methods that different commands have.
 */
public abstract class ACommand implements ICommand {
  protected String name;
  private String shapeName;
  protected IShape shape;
  protected int appear;
  protected int disappear;

  /**
   * Constructs an ACommand.
   *
   * @param name       indicates the name of the command.
   * @param shape      indicates the IShape that the command is executed on.
   * @param appear     indicates the start time of the command.
   * @param disappear  indicates the end time of the command.
   * @throws IllegalStateException when timeline is invalid or the object parameter is null.
   */
  public ACommand(String name, IShape shape, int appear, int disappear) {
    if ((name == null) || (shape == null)) {
      throw new IllegalStateException("Invalid Parm");
    } else if ((appear < 0)
            || (appear > disappear)
            || (appear < shape.getAppear())
            || (disappear > shape.getDisappear())) {
      throw new IllegalStateException("Invalid time interval");
    } else {
      this.shape = shape;
      this.appear = appear;
      this.disappear = disappear;
      this.name = name;
      this.shapeName = shape.getName();
    }
  }

  /**
   * Constructs a constructor for ACommand for reader.
   * @param name          indicates the name of the command.
   * @param shapeName     indicates the name of the IShape that the command is executed on.
   * @param appear        indicates the start time of the command.
   * @param disappear     indicates the end time of the command.
   */
  public ACommand(String name, String shapeName, int appear, int disappear) {
    if ((shapeName == null) || (name == null)) {
      throw new IllegalStateException("Invalid Parm");
    } else if ((appear < 0) || (appear > disappear)) {
      throw new IllegalStateException("Invalid time interval");
    } else {
      this.shapeName = shapeName;
      this.appear = appear;
      this.disappear = disappear;
      this.name = name;
      this.shape = null;
    }
  }


  @Override
  public void setShape(IShape s) {
    if (s == null) {
      throw new IllegalArgumentException();
    } else {
      this.shape = s;
    }

  }

  @Override
  public IShape getShape() {
    return shape.getCopyofShape();
  }


  @Override
  public int getAppear() {
    return appear;
  }


  @Override
  public int getDisappear() {
    return disappear;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int compareTo(ICommand o) {
    if (this.appear < o.getAppear()) {
      return -1;
    } else if (this.appear > o.getAppear()) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Helper method for getState that helps to reduce the duplicate code when the string format of
   * time is the same for all commands.
   *
   * @param t the tempo.
   * @return String of the header.
   */
  protected String toStringTime(int t) {
    return " from t=" + appear / (double) t + "s to t=" + disappear / (double) t + "s\n";
  }

  /**
   * Calculate the intermediate state after a specific command is executed on a shape.
   *
   * @param a        indicates A float multiple.
   * @param currTime indicates the current time.
   * @param b        indicates B float multiple.
   * @return a float.
   */
  public float calInter(float a, int currTime, float b) {
    return ((a * ((this.disappear - currTime) / ((float) (this.disappear - this.appear))))
            + (b * ((currTime - this.appear) / ((float) (this.disappear - this.appear)))));
  }

  @Override
  public String getShapeName() {
    return shapeName;
  }


  @Override
  public String svgGetSize(String a) {
    String ans = "";
    if (this.shape.getType().equals(ShapeType.RECTANGLE)) {
      if (a.equals("x")) {
        ans = "width";
      } else {
        ans = "height";
      }
    } else if (this.shape.getType().equals(ShapeType.OVAL)) {
      if (a.equals("x")) {
        ans = "rx";
      } else {
        ans = "ry";
      }
    } else {
      throw new IllegalArgumentException("Invalid size.");
    }
    return ans;

  }

  @Override
  public String svgGetPos(String a) {
    String ans = "";
    if (this.shape.getType().equals(ShapeType.RECTANGLE)) {
      if (a.equals("x")) {
        ans = "x";
      } else {
        ans = "y";
      }
    } else if (this.shape.getType().equals(ShapeType.OVAL)) {
      if (a.equals("x")) {
        ans = "cx";
      } else {
        ans = "cy";
      }
    } else {
      throw new IllegalArgumentException("Invalid Pos");
    }
    return ans;
  }

  protected String beginBase(boolean loop) {
    if (loop) {
      return "base.begin+";
    }
    return "";
  }

  @Override
  public Color getOldColor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Color getNewColor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Position2D getNewPos() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Position2D getOldPos() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Size getNewSize() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Size getOldSize() {
    throw new UnsupportedOperationException();
  }

}


