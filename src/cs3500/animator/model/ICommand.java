package cs3500.animator.model;

/**
 * Changes made here:
 * -We add a new interface for commands, because in our model we changed its
 * field from a list of ACommand to list of ICommand. This lets us to make the deep copy of the
 * model easier when sending the information of model to the view.
 * -We made this interface to
 * extends Comparable, because it will let us to sort the command by time in our model.
 */

/**
 * This is an Interface that represents an ICommand.
 */
public interface ICommand extends Comparable<ICommand> {

  /**
   * This method is to get a copy of a command.
   *
   * @return copy of an ICommand.
   */
  ICommand getCopyofCommand();

  /**
   * This method will get the state of the command as a String.
   *
   * @param t  indicates the tempo.
   * @return String of command state.
   */
  String getState(int t);

  /**
   * This method is to get the appear time of the command.
   *
   * @return appear time as int.
   */
  int getAppear();

  /**
   * This method is to get the disappear time of the command.
   *
   * @return disappear time as int.
   */
  int getDisappear();

  /**
   * This method is to get the shape of the command.
   *
   * @return IShape.
   */
  IShape getShape();

  /**
   * This method is to get the name of the command.
   *
   * @return String which is the name of the command.
   */
  String getName();

  /**
   * This method is to get a copy of the IShape that after the command executed on the shape
   * with its new state.
   * @param curr time.
   */
  void updateShape(IShape shape, int curr);


  /**
   * This method is to set this shape to another shape.
   *
   * @param s IShape.
   * @throws IllegalArgumentException when the given IShape is null.
   */
  void setShape(IShape s);

  /**
   * This method is to get the name of the shape.
   *
   * @return String.
   */
  String getShapeName();

  /**
   * This method is to get the state of the command in svg format while looping.
   *
   * @param t  indicates the tempo.
   * @return String as svg format.
   */
  String svgCommand(int t, boolean loop);

  /**
   * This method is to get the x and y value of size of svg format based on different shape type.
   *
   * @param a the attribute name.
   * @return String.
   */
  String svgGetSize(String a);

  /**
   * This method is to get the x and y position of svg format based on different shape type.
   *
   * @param a the attribute name.
   * @return String.
   */
  String svgGetPos(String a);

  /**
   * This method is to get the old color before ChangeColor is executed on a shape.
   * @return a copy of the old Color.
   */
  Color getOldColor();

  /**
   * This method is to get the new color after ChangeColor is executed on a shape.
   * @return a copy of the new Color.
   */
  Color getNewColor();

  /**
   * This method is to get the new position after the command(changePos)is called on a shape.
   * @return new Pos.
   */
  Position2D getNewPos();

  /**
   * This method is to get the old position before the command(changePos)is called on a shape.
   * @return old Pos.
   */
  Position2D getOldPos();

  /**
   * This method is to get the new size before the command(changeScale)is called on a shape.
   * @return new Size.
   */
  Size getNewSize();

  /**
   * This method is to get the old size before the command(changeScale)is called on a shape.
   * @return old Size.
   */
  Size getOldSize();

}
