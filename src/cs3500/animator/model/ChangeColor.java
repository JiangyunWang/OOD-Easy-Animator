package cs3500.animator.model;

/**
 * Changes made here: -We add a new constructor, instead of takes a IShape as its parameter, this
 * one takes in the name of the shape which particular for the reader to read the information of a
 * file. -We add a copy constructor for ChangeColor. -We add a method, getCopyofCommand, which helps
 * to return a copy of ChangeColor. -We add a method, updateShape(int curr), which helps to return a
 * copy of the shape after ChangeColor executed on it with its new state of color. -We add a method,
 * svgCommand(int t), which returns the state of the command in svg format. with its new state.
 */

/**
 * This class represents a command, changeColor, which extends ACommand.
 */
public class ChangeColor extends ACommand {
  private Color oldColor;
  private Color newColor;

  /**
   * Constructs a ChangeColor.
   * @param shape         indicates the shape that the command has.
   * @param appear        indicates the appear time of the command.
   * @param disappear     indicates the disappear time of the command.
   * @param oldColor      indicates the old color before changeColor is executed on the shape.
   * @param newColor      indicates the new color after changeColor is executed on the shape.
   * @throws IllegalStateException when newColor is null.
   */
  public ChangeColor(IShape shape, int appear, int disappear, Color oldColor, Color newColor) {
    super("changeColor", shape, appear, disappear);
    if (newColor == null) {
      throw new IllegalStateException("");
    } else {
      this.newColor = newColor;
      this.oldColor = oldColor;
    }
  }

  /**
   * Constructs a ChangeColor.
   * @param shapeName    indicates the shape's name that the command has.
   * @param appear       indicates the appear time of the command.
   * @param disappear    indicates the disappear time of the command.
   * @param oldColor     indicates the old color before changeColor is executed on the shape.
   * @param newColor     indicates the old color after changeColor is executed on the shape.
   * @throws IllegalStateException when newColor is null.
   */
  public ChangeColor(String shapeName, int appear, int disappear, Color oldColor, Color newColor) {
    super("changeColor", shapeName, appear, disappear);
    if (newColor == null) {
      throw new IllegalStateException("");
    } else {
      this.newColor = newColor;
      this.oldColor = oldColor;
    }
  }

  /**
   * Constructs a copy constructor for ChangeColor.
   *
   * @param c indicates a color.
   * @throws IllegalStateException when c is null.
   */
  public ChangeColor(ICommand c) {
    super("changeColor", c.getShape(), c.getAppear(), c.getDisappear());
    if (c == null) {
      throw new IllegalStateException();
    } else {
      this.newColor = new Color(c.getNewColor());
      this.oldColor = new Color(c.getOldColor());
    }
  }


  public Color getOldColor() {
    return new Color(oldColor);
  }


  public Color getNewColor() {
    return new Color(newColor);
  }


  @Override
  public String getState(int t) {
    return "Shape " + shape.getName() + " moves color from"
            + oldColor.colorToSring() + " to" + newColor.colorToSring() + toStringTime(t);

  }


  @Override
  public ICommand getCopyofCommand() {
    return new ChangeColor(this);
  }

  @Override
  public void updateShape(IShape shape, int curr) {
    if (shape.getName().equals(this.shape.getName())) {
      float r;
      float g;
      float b;
      r = calInter(oldColor.getRed(), curr, newColor.getRed());
      g = calInter(oldColor.getGreen(), curr, newColor.getGreen());
      b = calInter(oldColor.getBlue(), curr, newColor.getBlue());
      shape.setColor(new Color(r, g, b));
    }
  }

  @Override
  public String svgCommand(int t, boolean loop) {
    String s = "";
    s = "    <animate attributeType=\"xml\" begin=\""
            + beginBase(loop) + this.appear * 1000 / (double) t + "ms\" dur=\""
            + (this.disappear - this.appear) * 1000 / (double) t + "ms\" " +
            "attributeName=\"fill\" from=\""
            + this.oldColor.svgColor() + "\" to=\"" + this.newColor.svgColor()
            + "\" fill=\"freeze\" />\n\n";
    if (loop) {
      s = s + "    <animate attributeType=\"xml\" begin=\""
              + "base.end\"" + " dur=\"100ms\" attributeName=\"fill\" " +
              "to=\"" + this.oldColor.svgColor() + "\" fill=\"freeze\" />\n\n";
    }
    return s;
  }
}
