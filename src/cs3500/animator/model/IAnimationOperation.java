package cs3500.animator.model;


import java.util.List;

/**
 * Changes we made here:
 * -We add a new method, sendShapeState(int time), to returns a list of shape
 * that has some command to execute in the current given time. And we could directly send the
 * information of those shapes to our view and let the view to show out.
 */

/**
 * This represents the interface of Animation model.
 */
public interface IAnimationOperation {

  /**
   * Add an IShape into the list of IShape.
   *
   * @param s an IShape.
   */
  void addAShape(IShape s);

  /**
   * Add an ICommmand into the list of ICommand and sorts the list of Commands.
   *
   * @param c an ICommand.
   */
  void addACommand(ICommand c);

  /**
   * Delete an IShape from the list of IShape.
   *
   * @param s an IShape.
   * @throws IllegalArgumentException when there is no s in the list.
   */
  void deleteAShape(IShape s);

  /**
   * Delete an ICommand from the list of ICommand.
   *
   * @param c an ICommand.
   * @throws IllegalArgumentException when there is no c in the list.
   */
  void deleteACommand(ICommand c);

  /**
   * Get the background color from the model.
   * @return
   */
  java.awt.Color getColor();

  /**
   * Get the list of IShapes in model.
   *
   * @return list of IShapes.
   */
  List<IShape> getLoShape();

  /**
   * Get the list of ICommands in model.
   *
   * @return list of ICommands.
   */
  List<ICommand> getLoCommand();

  void checkLayerOrder();
}
