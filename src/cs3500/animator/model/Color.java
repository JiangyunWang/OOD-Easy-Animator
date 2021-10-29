package cs3500.animator.model;

/**
 * Changes made here: -We add a copy constructor for color. -We change our colorToString method.
 * -We add a method, svgColor, that returns the svg format of color as string.
 */

/**
 * A class that represents Color, which has values of red, green, blue by float.
 * Moreover, int should in the interval of [0.0, 1.0].
 */
public class Color {
  private float red;
  private float green;
  private float blue;

  /**
   * Construct a constructor for Color.
   *
   * @param red   float to represent red.
   * @param green float to represent green.
   * @param blue  float to represent blue.
   * @throws IllegalStateException when the int is out of the interval [0.0, 1.0].
   */
  public Color(float red, float green, float blue) {
    if ((this.red >= 0.0) && (this.red <= 1.0)
            && (this.green >= 0.0) && (this.green <= 1.0)
            && (this.blue >= 0.0) && (this.blue <= 1.0)) {
      this.red = red;
      this.green = green;
      this.blue = blue;
    } else {
      throw new IllegalStateException("Invalid Color.");
    }
  }

  /**
   * Constructs a Copy constructor for color.
   * @param c the color that try to be replaced.
   * @throws IllegalStateException when c is null.
   */
  public Color(Color c) {
    if (c == null) {
      throw new IllegalArgumentException("");
    } else {
      this.red = c.red;
      this.blue = c.blue;
      this.green = c.green;
    }
  }

  /**
   * Get the float red from the Color.
   * @return float which represents red.
   */
  public float getRed() {
    return this.red;
  }

  /**
   * Get the float green from the Color.
   * @return float which represents green.
   */
  public float getGreen() {
    return this.green;
  }

  /**
   * Get the float blue from the Color.
   * @return float which  represents blue.
   */
  public float getBlue() {
    return this.blue;
  }

  /**
   * Get the state of the Color.
   * @return String state of the color.
   */
  public String colorToSring() {
    return String.format("(%g,%g,%g)", getRed(), getGreen(), getBlue());
  }

  /**
   * This method is to get the svg format of color as string.
   * @return String as svg format of color.
   */
  public String svgColor() {
    return "rgb(" + Math.round(this.red * 255) + "," + Math.round(this.green * 255) + ","
            + Math.round(this.blue * 255) + ")";
  }


}
