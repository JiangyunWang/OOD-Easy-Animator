package cs3500.animator.model;

/**
 * Changes we made here:
 * -We add a copy constructor for oval.
 * -We add a new method, getCopyofShape, which helps to return a copy of the oval.
 * --We add a method, svgState(int), to returns the state of shape in svg format as String.
 */

/**
 * A class represents a shape, Oval, that extends AShape.
 */
public class Oval extends AShape {
  private int layer;

  /**
   * Construct an constructor for oval.
   *
   * @param name      name of the shape.
   * @param size      size of the shape.
   * @param pos       position(center) of the shape.
   * @param color     color of the shape.
   * @param appear    Time of the shape to appear.
   * @param disappear Time of the shape to disappear.
   * @throws IllegalStateException when the time is invalid.
   */
  public Oval(String name, Size size, Position2D pos, Color color, int appear, int disappear, int layer) {
    super(name, ShapeType.OVAL, size, pos, color, appear, disappear, layer);
//    this.layer = layer;
  }
//
//  public Oval(String name, Size size, Position2D pos, Color color, int appear, int disappear) {
//    super(name, ShapeType.OVAL, size, pos, color, appear, disappear);
//   this.layer = 1;
//  }

  /**
   * Constructs a copy constructor for Oval.
   *
   * @param o indicates an Ishape.
   */
  public Oval(IShape o) {
    super(o.getName(), ShapeType.OVAL, o.getSize(), o.getPos(), o.getColor(), o.getAppear(),
            o.getDisappear(), o.getLayer());
//    this.layer = o.getLayer();
  }

  @Override
  public String getState(int t) {
    return toStringHead() + "Center: " + pos.getState() + ", " + "X radius: " + size.getX()
            + ", Y radius: " + size.getY() + ", Color: " + getColor().colorToSring() + "\n"
            + toStringTime(t);
  }


  @Override
  public IShape getCopyofShape() {
    return new Oval(this);
  }


  @Override
  public String svgState(int t, boolean loop) {
    String s = "";
    s = s + "<ellipse id=\"" + this.name
            + "\" cx=\"" + Math.round(this.pos.getX())
            + "\" cy=\"" + Math.round(this.pos.getY()) +
            "\" rx=\"" + Math.round(this.size.getX())
            + "\" ry=\"" + Math.round(this.size.getY()) + "\" fill=\""
            + this.color.svgColor() + "\" visibility="
            + checkVisibility((this.appear / (double) t)) + " >\n";
    s = s + loopSvg(t, loop);
    return s;
  }

  @Override
  public String svgReset() {
    String s = "";
    s = s + svgFront() + "attributeName=\"fill\" to=\""
            + getColor().svgColor() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"cx\" to=\""
            + getPos().getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"cy\" to=\""
            + getPos().getY() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"rx\" to=\""
            + getSize().getX() + "\" fill=\"freeze\" />\n";
    s = s + svgFront() + "attributeName=\"ry\" to=\""
            + getSize().getY() + "\" fill=\"freeze\" />\n";
    return s;
  }


}
