package cs3500.animator.model;

/**
 * Changes made here:
 * -We have changed the method name toString to getState in order to
 * differentiate our method with the predefined toString method.
 * -We add a new method, getCopyofShape, which helps to return a copy of the IShape.
 * -We add a method, checkVisibility(double appear), to return the shape's visibility as String.
 * -We add a method, String svgState(int t), to return the shape's state as svg format.
 */

/**
 * This is an Interface that represents an IShape.
 */
public interface IShape extends Comparable<IShape> {
  /**
   * This method is to get the specific state of the specific shape as String.
   *
   * @return String of the shape state.
   */
  String getState(int t);

  /**
   * This method is to get the copy of the IShape.
   *
   * @return copy of IShape.
   */
  IShape getCopyofShape();


  /**
   * This method is to get the appear time of the shape.
   *
   * @return the appear time as int.
   */
  int getAppear();

  /**
   * This method is to get the disappear time of the shape.
   *
   * @return the disappear time as int..
   */
  int getDisappear();

  /**
   * This method is to get the type of the shape.
   *
   * @return ShapeType.
   */
  ShapeType getType();

  /**
   * This method output the previous state(when it’s all same for all shapes) of the shape.
   *
   * @return String.
   */
  String toStringHead();

  /**
   * This method is to get the position of a shape.
   *
   * @return Position2D.
   */
  Position2D getPos();

  /**
   * This method is to get the size of a shape.
   *
   * @return Size.
   */
  Size getSize();

  /**
   * This method is to get the color of a shape.
   *
   * @return Color.
   */
  Color getColor();

  /**
   * This method is to get the name of the shape.
   *
   * @return String which represents name of the Shape.
   */
  String getName();

  /**
   * This method set the shape’s current color to the given one.
   *
   * @param c Color.
   * @throws IllegalStateException when c is null.
   */
  void setColor(Color c);

  /**
   * This method set the shape’s current pos to the given one.
   *
   * @param pos Position2D.
   * @throws IllegalStateException when pos is null.
   */
  void setPos(Position2D pos);

  /**
   * This method set the shape’s current size to the given one.
   *
   * @param s Size.
   * @throws IllegalStateException when s is null.
   */
  void setSize(Size s);

  /**
   * This method helps to check the visibility of the shape and returns it as String.
   *
   * @param appear indicates appear time.
   * @return visibility as String.
   */
  String checkVisibility(double appear);

  /**
   * This method is to get the shape's state in String as svg format.
   *
   * @param t indicates tempo.
   * @return String as svg format.
   */
  String svgState(int t, boolean loop);

  /**
   * This method is to get the shape's state in String as svg format, whiling it's looping.
   *
   * @param t    indicates tempo.
   * @param loop indicates loop as a boolean.
   * @return String as svg format.
   */
  String loopSvg(int t, boolean loop);

  /**
   * This method is to reset the svg when it needs loop.
   * @return string as a svg loop format.
   */
  String svgReset();

  /**
   * This method is to give the loop svg front format.
   * @return string as a svg loop front format.
   */
  String svgFront();


  int getLayer();
}
