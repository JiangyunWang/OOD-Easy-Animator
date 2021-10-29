package cs3500.animator.model;

/**
 * Changes made here: -We add a new constructor, instead of takes a IShape as its parameter, this
 * one takes in the name of the shape which particular for the reader to read the information of a
 * file. -We add a copy constructor for ChangeScales. -We add a method, getCopyofCommand, which
 * helps to return a copy of ChangeScales. -We add a method, updateShape(int curr), which helps to
 * return a copy of the shape after ChangeScales executed on it with its new state of scale. -We add
 * a method, svgCommand(int t), which returns the state of the command in svg format. with its new
 * state.
 */

/**
 * This class represents a command, changeScales, which extends ACommand.
 */
public class ChangeScales extends ACommand {
  private Size newSize;
  private Size oldSize;

  /**
   * Constructs a ChangePos.
   * @param shape         indicates the shape that the command has.
   * @param appear        indicates the appear time of the command.
   * @param disappear     indicates the disappear time of the command.
   * @param oldSize       indicates the old size before changeScale is executed on the shape.
   * @param newSize       indicates the new size after changeScale is executed on the shape.
   * @throws IllegalStateException when newSize is null.
   */
  public ChangeScales(IShape shape, int appear, int disappear, Size oldSize, Size newSize) {
    super("changeScales", shape, appear, disappear);
    if (newSize == null) {
      throw new IllegalStateException();
    } else {
      this.newSize = newSize;
      this.oldSize = oldSize;
    }
  }

  /**
   * Constructs a ChangePos.
   * @param shapeName     indicates the shape's name that the command has.
   * @param appear        indicates the appear time of the command.
   * @param disappear     indicates the disappear time of the command.
   * @param oldSize       indicates the old size before changeScale is executed on the shape.
   * @param newSize       indicates the new size after changeScale is executed on the shape.
   * @throws IllegalStateException when newSize is null.
   */
  public ChangeScales(String shapeName, int appear, int disappear, Size oldSize, Size newSize) {
    super("changeScales", shapeName, appear, disappear);
    this.newSize = newSize;
    this.oldSize = oldSize;
  }

  /**
   * Constructs a copy constructor for changeScale.
   * @param ac    indicates ChangeScales.
   * @throws IllegalStateException when ac is null.
   */
  public ChangeScales(ICommand ac) {
    super("changeScales", ac.getShape(), ac.getAppear(), ac.getDisappear());
    if (ac == null) {
      throw new IllegalStateException();
    } else {
      this.newSize = new Size(ac.getNewSize());
      this.oldSize = new Size(ac.getOldSize());
    }
  }


  @Override
  public String getState(int t) {
    switch (shape.getType()) {
      case RECTANGLE:
        return "Shape " + shape.getName() + " scales from "
                + "Width: " + oldSize.getX() + ", Height: " + oldSize.getY() + " to "
                + "Width: " + newSize.getX() + ", Height: " + newSize.getY()
                + toStringTime(t);
      case OVAL:
        return "Shape " + shape.getName() + " scales from "
                + "X radius: " + oldSize.getX() + ", Y radius: " + oldSize.getY()
                + " to "
                + "X radius: " + newSize.getX() + ", Y radius: " + newSize.getY()
                + toStringTime(t);
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public ICommand getCopyofCommand() {
    return new ChangeScales(this);
  }

  @Override
  public void updateShape(IShape shape, int curr) {
    if (shape.getName().equals(this.shape.getName())) {
      float x;
      float y;
      x = calInter(oldSize.getX(), curr, newSize.x);
      y = calInter(oldSize.getY(), curr, newSize.y);
      shape.setSize(new Size(x, y));
    }
  }

  @Override
  public Size getNewSize() {
    return new Size(newSize);
  }

  @Override
  public Size getOldSize() {
    return new Size(oldSize);
  }

  @Override
  public String svgCommand(int t, boolean loop) {
    String s = "";
    if (oldSize.getX() != newSize.getX()) {
      s = s + "    <animate attributeType=\"xml\" begin=\""
              + beginBase(loop) + this.appear * 1000 / (double) t + "ms\" dur=\""
              + (this.disappear - this.appear) * 1000 / (double) t
              + "ms\" attributeName=\"" + svgGetSize("x") + "\" from=\""
              + this.oldSize.getX() + "\" to=\""
              + this.newSize.getX()
              + "\" fill=\"freeze\" />\n";
    }


    if (oldSize.getY() != newSize.getY()) {
      s = s + "    <animate attributeType=\"xml\" begin=\""
              + beginBase(loop) + this.appear * 1000 / (double) t + "ms\" dur=\""
              + (this.disappear - this.appear) * 1000 / (double) t
              + "ms\" attributeName=\"" + svgGetSize("y") + "\" from=\""
              + this.oldSize.getY() + "\" to=\"" + this.newSize.getY()
              + "\" fill=\"freeze\" />\n\n";
    }

    if (loop) {
      if (this.oldSize.getX() != this.newSize.getX()) {
        s = s + "    <animate attributeType=\"xml\" begin=\""
                + "base.end\"" + " dur=\"100ms\" attributeName=\"" + svgGetSize("x") +
                "\" to=\"" + this.oldSize.getX() + "\" fill=\"freeze\" />\n";
      }

      if (this.oldSize.getY() != this.newSize.getY()) {
        s = s + "    <animate attributeType=\"xml\" begin=\""
                + "base.end\"" + " dur=\"100ms\" attributeName=\"" + svgGetSize("y") +
                "\" to=\"" + this.oldSize.getY() + "\" fill=\"freeze\" />\n\n";
      }
    }
    return s;
  }
}
