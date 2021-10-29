package cs3500.animator.model;

/**
 * Assignment 8: we change the method toStringTime to one line because we've noticed that the if
 * statement is unnecessary.

 * Assignment 6: Changes we made here: -We add a helper method, toStringTime(int t), for getState.
 * -We add a method, checkVisibility(double appear), to check the visibility of the shape and
 * returns it as String.
 */

/**
 * Assignment 6: Changes we made here: -We add a helper method, toStringTime(int t), for getState.
 * -We add a method, checkVisibility(double appear), to check the visibility of the shape
 * and returns it as String.
 */

/**
 * An abstract class represents shape, that implements IShape and helps to handle all of the common
 * fields and methods that different shapes have.
 */
public abstract class AShape implements IShape {
  protected String name;
  protected ShapeType type;
  protected Size size;
  protected Position2D pos;
  protected Color color;
  protected int appear;
  protected int disappear;

  protected int layer;


  /**
   * Construct a contructor for AShape.
   *
   * @param name      name of the shape.
   * @param type      type of the shape.
   * @param size      size of the shape.
   * @param pos       position(center) of the shape.
   * @param color     color of the shape.
   * @param appear    Time of the shape to appear.
   * @param disappear Time of the shape to disappear.
   * @throws IllegalStateException when the time is invalid or one object parameter is null.
   */
  public AShape(String name, ShapeType type, Size size, Position2D pos,
                Color color, int appear, int disappear, int layer) {
    if ((name == null) || (size == null) || (pos == null) || (color == null)) {
      throw new IllegalStateException("Invalid Parm");
    } else if ((appear < 0) || (appear >= disappear)) {
      throw new IllegalStateException("Invalid appear Parameter.");
    } else {
      this.name = name;
      this.type = type;
      this.size = size;
      this.pos = pos;
      this.color = color;
      this.appear = appear;
      this.disappear = disappear;
      this.layer = layer;

    }

  }


  /**
   * Helper method for getState that helps to reduce the duplicate code when the string format of
   * time is the same for all shapes.
   *
   * @param t the tempo.
   * @return String of the header.
   */
  protected String toStringTime(int t) {

    return "Appears at t=" + appear / (double) t + "s\nDisappears at t="
            + disappear / (double) t + "s\n\n";

  }

  @Override
  public String toStringHead() {
    return "Name: " + this.name + "\n" +
            "Type: " + this.type.typeName() + "\n";
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getType() {
    return this.type;

  }

  @Override
  public Size getSize() {
    return new Size(size.getX(), size.getY());
  }


  @Override
  public Position2D getPos() {
    return new Position2D(pos.getX(), pos.getY());
  }

  @Override
  public Color getColor() {
    return new Color(color.getRed(), color.getGreen(), color.getBlue());
  }


  @Override
  public int getAppear() {
    return this.appear;
  }

  @Override
  public int getDisappear() {
    return this.disappear;
  }

  @Override
  public void setSize(Size s) {
    if (s == null) {
      throw new IllegalArgumentException("Invalid Size.");
    } else {
      this.size = s;
    }
  }

  @Override
  public void setPos(Position2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Invalid Pos.");
    } else {
      this.pos = p;
    }
  }

  @Override
  public void setColor(Color c) {
    if (c == null) {
      throw new IllegalArgumentException("Invalid Color.");
    } else {
      this.color = c;
    }
  }

  @Override
  public int compareTo(IShape o) {
    if (this.appear < o.getAppear()) {
      return -1;
    } else if (this.appear > o.getAppear()) {
      return 1;
    } else {
      return 0;
    }
  }


  @Override
  public String checkVisibility(double appear) {
    if (appear == 0) {
      return "\"visible\"";
    } else {
      return "\"hidden\"";
    }
  }

  @Override
  public String svgFront() {
    return "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"100ms\" ";
  }

  @Override
  public String loopSvg(int t, boolean loop) {
    String s = "";
    if (loop) {
      s = s + "    <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\" " +
              "begin=\"base.begin+" + this.appear * 1000 / (double) t
              + "ms\" dur=\"1ms\" fill=\"freeze\"/>";
      s = s + "\n    <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" " +
              "begin=\"base.begin+" + this.disappear * 1000 / (double) t
              + "ms\" dur=\"1ms\" " +
              "fill=\"freeze\"/>\n";
    } else if (this.appear != 0) {
      s = s + "    <set attributeName=\"visibility\" " +
              "from=\"hidden\" " + "to=\"visible\" begin=\""
              + (appear * 1000 / (double) t) + "ms\" " +
              "end=\"" + this.disappear * 1000 / (double) t
              + "ms\"/>\n";
    } else {
      s = s + "    <set attributeName=\"visibility\" " +
              "from=\"visible\" " + "to=\"hidden\" begin=\""
              + (appear * 1000 / (double) t) +
              "ms\"/>\n";
    }
    return s;
  }


  public int getLayer() {
    return this.layer;
  }
}

