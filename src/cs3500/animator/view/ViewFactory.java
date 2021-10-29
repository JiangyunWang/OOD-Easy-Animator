package cs3500.animator.view;


import cs3500.animator.model.IAnimationOperation;

/**
 * This class represents the factory class that has a factory method to output a specific view.
 */
public class ViewFactory {

  /**
   * Output a specific view according to the name of the view.
   *
   * @param s     indicates the name of the view.
   * @param tempo indicates the speed.
   * @param model indicates the IAnimationOperation.
   * @param loop  indicates the loop as boolean value.
   * @return an IView.
   */
  public static IView create(String s, int tempo, IAnimationOperation model, boolean loop) {
    StringBuffer ap = new StringBuffer();
    if (s == null) {
      throw new IllegalArgumentException("Found Null Value");
    }
    switch (s) {
      case "text":
        return new TextualView(model.getLoShape(), model.getLoCommand(), ap, tempo);
      case "visual":
        return new SwingView(model.getLoShape(), model.getLoCommand(), tempo);
      case "svg":
        return new SVGView(model.getLoShape(), model.getLoCommand(), ap, tempo, loop);
      case "interactive":
//        return new HybridView(model.getLoShape(), model.getLoCommand(), tempo, loop);
        return new NewInteractiveView(model.getLoShape(), model.getLoCommand(), tempo, loop, model.getColor());
      default:
        throw new IllegalArgumentException();
    }
  }
}
