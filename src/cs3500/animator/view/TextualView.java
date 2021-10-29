package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;


/**
 * This class represents a textual view of the simple Animation. This view will show a textual
 * description of the animation.
 */
public class TextualView implements IView {
  private Appendable ap;
  private List<IShape> los;
  private List<ICommand> loc;
  private final int tempo;

  /**
   * Constructs a TextualView.
   *
   * @param tempo indicates the tempo.
   * @param ap    indicates the appendable.
   */
  public TextualView(List<IShape> los, List<ICommand> loc, Appendable ap, int tempo) {
    if (ap == null || loc == null || los == null || tempo < 0) {
      throw new IllegalArgumentException("");
    } else {
      this.ap = ap;
      this.los = los;
      this.loc = loc;
      this.tempo = tempo;
    }
  }

  @Override
  public void outPutView(List<IShape> los, List<ICommand> loc) {
    try {
      ap.append(this.textView(los, loc, tempo));
      System.out.println(ap.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid view");
    }

    System.out.println(ap.toString());
  }


  /**
   * To output the model's state in string.
   *
   * @param shapes   indicates a list of IShape.
   * @param commands indicates a list of ICommand.
   * @param tempo    indicates tempo.
   * @return the string which is the textview version.
   */
  private String textView(List<IShape> shapes, List<ICommand> commands, int tempo) {
    String s = "";
    for (int i = 0; i < shapes.size(); i++) {
      s = s + shapes.get(i).getState(tempo);
    }

    for (int j = 0; j < commands.size(); j++) {
      s = s + commands.get(j).getState(tempo);
    }
    return s;
  }

  @Override
  public String outString() {
    return ap.toString();
  }

  @Override
  public void addActionListener(ActionListener l) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void loopBack() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void speedUp() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void slowDown() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void pause() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addItemListener(ItemListener l) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void restart(List<IShape> los, List<ICommand> loc) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeOrAdd(IShape s) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void exportSVG() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void selectAll() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void backgroundColor() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
