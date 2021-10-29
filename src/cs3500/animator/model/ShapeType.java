package cs3500.animator.model;

/**
 * A enum class that represents the type of the ShapeType, it can only be oval, or rectangle.
 */
public enum ShapeType {
  OVAL, RECTANGLE;

  /**
   * This method is to get the shape type as a form of String.
   * @return String of type name.
   */
  public String typeName() {
    switch (this) {
      case OVAL:
        return "oval";
      case RECTANGLE:
        return "rectangle";
      default:
        throw new IllegalArgumentException();

    }
  }
}
