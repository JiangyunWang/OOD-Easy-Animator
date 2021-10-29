package cs3500.animator.model;

/**
 * Changes made here: -We add a new constructor, instead of takes a IShape as its parameter, this
 * one takes in the name of the shape which particular for the reader to read the information of a
 * file. -We add a copy constructor for ChangePos. -We add a method, getCopyofCommand, which helps
 * to return a copy of ChangePos -We add a method, updateShape(int curr), which helps to return a
 * copy of the shape after ChangePos executed on it with its new state of position. -We add a
 * method, svgCommand(int t), which returns the state of the command in svg format. with its new
 * state.
 */


/**
 * This class represents a command, changePos, which extends ACommand.
 */
public class ChangePos extends ACommand {
  private Position2D newPos;
  private Position2D oldPos;

  /**
   * Constructs a ChangePos.
   *
   * @param shape     indicates the shape that the command has.
   * @param appear    indicates the appear time of the command.
   * @param disappear indicates the disappear time of the command.
   * @param oldPos    indicates the old pos before changePos is executed on the shape.
   * @param newPos    indicates the new pos after changePos is executed on the shape.
   * @throws IllegalStateException when newPos is null.
   */
  public ChangePos(IShape shape, int appear, int disappear, Position2D oldPos, Position2D newPos) {
    super("changePos", shape, appear, disappear);
    if (newPos == null) {
      throw new IllegalStateException();
    } else {
      this.newPos = newPos;
      this.oldPos = oldPos;
    }
  }

  /**
   * Constructs a ChangePos.
   *
   * @param shapeName indicates the shape's name that the command has.
   * @param appear    indicates the appear time of the command.
   * @param disappear indicates the disappear time of the command.
   * @param oldPos    indicates the old pos before changePos is executed on the shape.
   * @param newPos    indicates the new pos after changePos is executed on the shape.
   * @throws IllegalStateException when newPos is null.
   */
  public ChangePos(String shapeName, int appear, int disappear, Position2D oldPos,
                   Position2D newPos) {
    super("changePos", shapeName, appear, disappear);
    if (newPos == null) {
      throw new IllegalStateException();
    } else {
      this.newPos = newPos;
      this.oldPos = oldPos;
    }
  }

  /**
   * Constructs a copy constructor for ChangePos.
   *
   * @param ac indicates a ChangePos.
   * @throws IllegalStateException when ac is null.
   */
  public ChangePos(ICommand ac) {
    super("changePos", ac.getShape(), ac.getAppear(), ac.getDisappear());
    if (ac == null) {
      throw new IllegalStateException();
    } else {
      this.newPos = new Position2D(ac.getNewPos());
      this.oldPos = new Position2D(ac.getOldPos());
    }
  }

  @Override
  public Position2D getNewPos() {
    return new Position2D(newPos);
  }

  @Override
  public Position2D getOldPos() {
    return new Position2D(oldPos);
  }

  @Override
  public String getState(int t) {

    return "Shape " + shape.getName() + " moves from "
            + oldPos.getState() + " to " + newPos.getState()
            + toStringTime(t);
  }

  @Override
  public ICommand getCopyofCommand() {
    return new ChangePos(this);
  }

  @Override
  public void updateShape(IShape shape, int curr) {
    if (shape.getName().equals(this.shape.getName())) {
      float x;
      float y;
      x = calInter(oldPos.getX(), curr, newPos.x);
      y = calInter(oldPos.getY(), curr, newPos.y);
      shape.setPos(new Position2D(x, y));
    }
  }

  @Override
  public String svgCommand(int t, boolean loop) {
    String s = "";
    if (this.oldPos.getX() != this.newPos.getX()) {
      s = s + "    <animate attributeType=\"xml\" begin=\""
              + beginBase(loop) + (this.appear * 1000 / (double) t) + "ms\" dur=\""
              + ((this.disappear - this.appear) * 1000 / (double) t)
              + "ms\" attributeName=\"" + svgGetPos("x") + "\" from=\""
              + this.oldPos.getX() + "\" to=\"" + this.newPos.getX()
              + "\" fill=\"freeze\" />\n";
    }

    if (this.oldPos.getY() != this.newPos.getY()) {
      s = s + "    <animate attributeType=\"xml\" begin=\""
              + beginBase(loop) + (this.appear * 1000 / (double) t) + "ms\" dur=\""
              + ((this.disappear - this.appear) * 1000 / (double) t)
              + "ms\" attributeName=\"" + svgGetPos("y") + "\" from=\""
              + this.oldPos.getY() + "\" to=\"" + this.newPos.getY()
              + "\" fill=\"freeze\" />\n\n";
    }

    if (loop) {
      if (this.oldPos.getX() != this.newPos.getX()) {
        s = s + "    <animate attributeType=\"xml\" begin=\""
                + "base.end\"" + " dur=\"100ms\" attributeName=\"" + svgGetPos("x") +
                "\" to=\"" + this.oldPos.getX() + "\" fill=\"freeze\" />\n";
      }

      if (this.oldPos.getY() != this.newPos.getY()) {
        s = s + "    <animate attributeType=\"xml\" begin=\""
                + "base.end\"" + " dur=\"100ms\" attributeName=\"" + svgGetPos("y") +
                "\" to=\"" + this.oldPos.getY() + "\" fill=\"freeze\" />\n\n";
      }
    }
    return s;

  }
}
