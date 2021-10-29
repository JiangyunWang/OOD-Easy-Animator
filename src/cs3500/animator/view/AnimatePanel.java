package cs3500.animator.view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ShapeType;

/**
 * Method: paintComponent(Graphics g) has been revised the size of fillOval has been multiplies by
 * 2.
 */

/**
 * This class represents the Animation Panel of the Swing view.
 */
public class AnimatePanel extends JPanel implements ActionListener {
  private List<IShape> shapes;
  private List<ICommand> commands;
  private int counter;
  private Timer t;
  private int speed;
  private List<IShape> los;

  /**
   * Constructs an AnimationPanel.
   *
   * @param speed indicates the speed.
   */
  public AnimatePanel(List<IShape> los, List<ICommand> loc, int speed) {
    super();
    if (los == null || loc == null || speed < 0) {
      throw new IllegalStateException();
    } else {
      this.setBackground(Color.white);
      this.commands = loc;
      this.shapes = los;
      this.speed = speed;
      this.los = new ArrayList<>();
      this.counter = 0;
      this.t = new Timer(1000 / this.speed, this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.los = sendShapeState(counter);
    this.repaint();
    counter = counter + 1;
    System.out.print("time:" + counter + "\n");
  }

  /**
   * This is a method for calling the timer to start.
   */
  protected void start() {
    t.start();
  }


  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (IShape s : los) {
      float red = s.getColor().getRed();
      float green = s.getColor().getGreen();
      float blue = s.getColor().getBlue();

      if (s.getType() == ShapeType.RECTANGLE) {

        g2d.setPaint(new Color(red, green, blue));
        System.out.println("Rectangle x: " + s.getSize().getY());
        g2d.fillRect((int) s.getPos().getX(),
                (int) s.getPos().getY(), (int) s.getSize().getX(), (int) s.getSize().getY());

      } else {
        g2d.setPaint(new Color(red, green, blue));
        System.out.println("Oval x: " + s.getSize().getY());
        g2d.fillOval((int) s.getPos().getX(),
                (int) s.getPos().getY(), (int) s.getSize().getX() * 2,
                (int) s.getSize().getY() * 2);
      }
    }
  }


  /**
   * Returns a list of shape that has some command to execute in the current time.
   *
   * @param time indicates current time.
   * @return a list of IShape.
   */
  private List<IShape> sendShapeState(int time) {
    List<IShape> currShapes = new ArrayList<>();
    List<ICommand> currCommands = new ArrayList<>();
    List<IShape> result = new ArrayList<>();

    for (IShape s : shapes) {
      if ((s.getAppear() <= time) && (s.getDisappear() > time)) {
        currShapes.add(s);
      }
    }

    for (ICommand c : commands) {
      if ((c.getAppear() <= time) && (c.getDisappear() > time)) {
        currCommands.add(c);
      }
    }

    for (IShape ss : currShapes) {
      for (ICommand cc : currCommands) {
        cc.updateShape(ss, time);
      }
      result.add(ss);
    }
    return result;
  }
}

