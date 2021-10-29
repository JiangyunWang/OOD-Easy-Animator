package cs3500.animator.model;

/**
 * Changes we made here:
 * -We add a copy constructor in order to write a getter method for getting the copy of the
 * position.
 */

/**
 * A class that represents the position of each shape with its x coordinate and y coordinate.
 */
public class Position2D {
  protected float x;
  protected float y;

  /**
   * Construct a constructor for Position2D.
   *
   * @param x int that represents the center of the shape in the x axis.
   * @param y int that represents the center of the shape in the y axis.
   */
  public Position2D(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   *
   * @param pos the pos which needed to be copied.
   * @throws IllegalStateException when pos is null.
   */
  public Position2D(Position2D pos) {
    if (pos == null) {
      throw new IllegalStateException("sides have to be positive number");
    } else {
      this.x = pos.x;
      this.y = pos.y;
    }
  }

  /**
   * To get the x from the position.
   */
  public float getX() {
    return x;
  }

  /**
   * To get the y from the position.
   */
  public float getY() {
    return y;
  }

  /**
   * To show the position as a form of String.
   *
   * @return String which represents position/center state.
   */
  public String getState() {
    return "(" + this.x + "," + this.y + ")";
  }

}
