package cs3500.animator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;


/**
 * This class represents a SwingView of the simple animation. In this view, it will draw and play
 * the animation inside a window, effectively reproducing the sample animation.
 */
public class SwingView extends JFrame implements IView {
  private List<IShape> los;
  private List<ICommand> loc;
  private AnimatePanel jp;
  private JScrollPane sp;
  private int tempo;

  /**
   * Constructs a SwingView.
   *
   * @param tempo indicates the tempo.
   * @throws IllegalStateException when am is null.
   */
  public SwingView(List<IShape> los, List<ICommand> loc, int tempo) {
    super();
    if (los == null || loc == null || tempo < 0) {
      throw new IllegalStateException();
    } else {
      this.los = los;
      this.loc = loc;
      this.tempo = tempo;
      this.setSize(500, 500);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new BorderLayout());
      this.jp = new AnimatePanel(los, loc, tempo);
      jp.setPreferredSize(new Dimension(1000, 1000));
      sp = new JScrollPane(jp);
      this.add(sp, BorderLayout.CENTER);
      this.setVisible(true);
      this.pack();
    }

  }


  @Override
  public void outPutView(List<IShape> los, List<ICommand> loc) {
    jp.start();
  }

  @Override
  public String outString() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addKeyListener(KeyListener k) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
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
