package cs3500.animator.model;

/**
 * Changes we made here:
 * -We add a copy constructor for rectangle.
 * -We add a new method, getCopyofShape, which helps to return a copy of the rectangle.
 * -We add a new method, String svgState(int t), which helps to return the state of rhe rectangle
 * in String as svg format.
 */

/**
 * A class represents rectangle, that extends AShape.
 */
public class Rectangle extends AShape {
  /**
   * Construct an constructor for rectangle.
   *
   * @param name      name of the shape.
   * @param size      size of the shape.
   * @param pos       position(center) of the shape.
   * @param color     color of the shape.
   * @param appear    Time of the shape to appear.
   * @param disappear Time of the shape to disappear.
   * @throws IllegalStateException when the time is invalid.
   */
  public Rectangle(String name, Size size,
                   Position2D pos, Color color, int appear, int disappear, int layer) {
    super(name, ShapeType.RECTANGLE, size, pos, color, appear, disappear, layer);
  }

  /**
   * Constructs a copy constructor for Rectangle.
   *
   * @param o indicates an IShape.
   */
  public Rectangle(IShape o) {
    super(o.getName(), ShapeType.RECTANGLE, o.getSize(), o.getPos(), o.getColor(),
            o.getAppear(), o.getDisappear(), o.getLayer());
  }

  @Override
  public String getState(int t) {
    return toStringHead() + "Lower-left corner: " + pos.getState() + ", " + "Width: " + size.getX()
            + ", Height: " + size.getY() + ", Color: " + getColor().colorToSring() + "\n"
            + toStringTime(t);
  }

  @Override
  public IShape getCopyofShape() {
    return new Rectangle(this);
  }


  @Override
  public String svgState(int t, boolean loop) {
    String s = "";
    s = s + "<rect id=\"" + this.name
            + "\" x=\"" + this.pos.getX()
            + "\" y=\"" + this.pos.getY() +
            "\" width=\"" + this.size.getX()
            + "\" height=\"" + this.size.getY() + "\" fill=\""
            + this.color.svgColor() + "\" visibility="
            + checkVisibility(this.appear / (double) t) + " >\n";
    s = s + loopSvg(t, loop);
    return s;
  }

  @Override
  public String svgReset() {
    String s = "";
    s = s + svgFront() + "attributeName=\"fill\" to=\""
            + getColor().svgColor() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"x\" to=\""
            + getPos().getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"y\" to=\""
            + getPos().getY() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"width\" to=\""
            + getSize().getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"height\" to=\""
            + getSize().getY() + "\" fill=\"freeze\" />\n";
    return s;
  }
}
