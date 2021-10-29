package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;

/**
 * This class represents the IView interface of the animation.
 */
public interface IView {
  /**
   * To show a specific view.
   */
  void outPutView(List<IShape> los, List<ICommand> loc);

  /**
   * Output the view as String.
   *
   * @return String.
   */
  String outString() throws UnsupportedOperationException;

  /**
   * Add an actionListener to a specific button.
   *
   * @param l indicates an ActionListener.
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void addActionListener(ActionListener l) throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that either can loop back or not.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void loopBack() throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that speeds up.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void speedUp() throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that slows down.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void slowDown() throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that can pause and resume.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void pause() throws UnsupportedOperationException;

  /**
   * Adds an item listener to a specific button.
   *
   * @param l indicates and ItemListener.
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void addItemListener(ItemListener l) throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality of restart.
   *
   * @param los indicates a list of IShape.
   * @param loc indicates a list of ICommand.
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void restart(List<IShape> los, List<ICommand> loc) throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that enables the user to choose whatever shapes the
   * user wanted to display.
   *
   * @param s indicates an IShape.
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void removeOrAdd(IShape s) throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that enables the user to export the animation as svg
   * file.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void exportSVG() throws UnsupportedOperationException;

  /**
   * Let the animation has the functionality that enables the user to choose all shapes to display.
   *
   * @throws UnsupportedOperationException in other three views besides HybridView.
   */
  void selectAll() throws UnsupportedOperationException;

  void backgroundColor() throws UnsupportedOperationException;


}
