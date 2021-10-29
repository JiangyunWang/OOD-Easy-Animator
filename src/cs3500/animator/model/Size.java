package cs3500.animator.model;

/**
 * Changes we made here:
 * -We add a copy constructor in order to write a getter method for getting
 * the copy of the size.
 */

/**
 * A class is to represents Size, which uses x, y to show the width and hight.
 */
public class Size {
  protected float x;
  protected float y;

  /**
   * Construct a constructor for Size.
   *
   * @param x float represents the width.
   * @param y float represents the height.
   * @throws IllegalStateException when int is equal or smaller than 0.
   */
  public Size(float x, float y) {
    if ((x > 0.0) && (y > 0.0)) {
      this.x = x;
      this.y = y;
    } else {
      throw new IllegalStateException("Invalid Size");
    }
  }

  /**
   * Constructs a copy constructor for size.
   *
   * @param s size.
   * @throws IllegalStateException when s is null.
   */
  public Size(Size s) {
    if (s == null) {
      throw new IllegalStateException("size cannot be null");
    } else {
      this.x = s.x;
      this.y = s.y;
    }

  }

  /**
   * Get the float x from the Size.
   *
   * @return float which is represents width.
   */
  public float getX() {
    return this.x;
  }

  /**
   * Get the float y from the Size.
   *
   * @return float which is represents height.
   */
  public float getY() {
    return this.y;
  }
}
