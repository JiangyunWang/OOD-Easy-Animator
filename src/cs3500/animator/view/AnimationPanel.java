package cs3500.animator.view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;


import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ChangePos;
import cs3500.animator.model.ChangeScales;
import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;

/**
 * Method: paintComponent(Graphics g) has been revised
 * the size of fillOval has been multiplies by 2.
 */

/**
 * This class represents the Animation Panel of the Hybrid view.
 */
public class AnimationPanel extends AnimatePanel implements IAnimationPanel{
  private List<IShape> orilos;
  private List<ICommand> oriloc;
  protected int counter;
  protected Timer t;
  protected int speed;
  protected List<IShape> shapes;
  protected List<ICommand> commands;
  protected boolean stop;
  protected List<IShape> instantShapes;
  protected boolean loop;
  protected Map<String, IShape> panelmap;

  protected boolean start;

  /**
   * Constructs an AnimationPanel.
   *
   * @param speed indicates the speed.
   */
  public AnimationPanel(List<IShape> los, List<ICommand> loc, int speed) {
    super(los, loc, speed);
    if (los == null || loc == null || speed < 0) {
      throw new IllegalStateException();
    } else {
      this.setBackground(Color.white);
      this.orilos = new ArrayList<>();
      this.oriloc = new ArrayList<>();
      this.speed = speed;
      this.counter = 0;
      this.t = new Timer(1000 / speed, this);

      this.stop = false;
      this.instantShapes = new ArrayList<IShape>();
      this.loop = false;

    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.instantShapes = sendShapeState(counter);
    this.repaint();
    counter = counter + 1;

    if (loop) {
      if (counter == getMaxDisappear()) {
        counter = 0;
        this.restart();
      }
    }


  }

  /**
   * Get the maximum disappear time of the list of IShape.
   *
   * @return int.
   */
  private int getMaxDisappear() {
    int cur = 0;
    for (int i = 0; i < instantShapes.size(); i++) {
      if (instantShapes.get(i).getDisappear() > cur) {
        cur = instantShapes.get(i).getDisappear();
      }
    }
    return cur;
  }


  /**
   * Get the maximum layer of the list of IShape.
   *
   * @return int.
   */
  private int getMaxLayer() {
    int cur = 1;
    for (IShape s : orilos) {
      if (s.getLayer() > cur) {
        cur = s.getLayer();
      }
    }
    return cur;
  }

  /**
   * To set the list of IShapes to another list of IShape.
   *
   * @param los a list of IShape.
   */
  protected void setLos(List<IShape> los) {
    this.orilos = los;
    resetLos();
  }

  /**
   * To set the list of ICommand to another list of ICommand.
   *
   * @param loc a list of IShape.
   */
  protected void setLoc(List<ICommand> loc) {
    this.oriloc = loc;
    resetLoc();
  }

  /**
   * To make a copy of the list of ICommand.
   */
  protected void resetLoc() {
    this.commands = new ArrayList<>();
    for (ICommand c : oriloc) {
      if (c.getName().equals("changeColor")) {
        commands.add(new ChangeColor(c));
      } else if (c.getName().equals("changeScales")) {
        commands.add(new ChangeScales(c));
      } else {
        commands.add(new ChangePos(c));
      }
    }
  }


  /**
   * To make a copy of the list of IShape.
   */
  protected void resetLos() {
    this.shapes = new ArrayList<>();
    for (IShape s : orilos) {
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        shapes.add(new Rectangle(s));
      } else {
        shapes.add(new Oval(s));
      }
    }
  }

  /**
   * Returns a list of shape that has some command to execute in the current time.
   *
   * @param time indicates current time.
   * @return a list of IShape.
   */
  @Override
  public List<IShape> sendShapeState(int time) {
    List<IShape> currShape = new ArrayList<>();
    List<ICommand> currCommands = new ArrayList<>();
    ArrayList<IShape> results = new ArrayList<>();
    ArrayList<IShape> layerlos = new ArrayList<>();

    for (IShape s : shapes) {
      if ((s.getAppear() <= time) && (s.getDisappear() >= time)) {
        currShape.add(s);
      }
    }

    for (ICommand c : commands) {
      if ((c.getAppear() <= time) && (c.getDisappear() >= time)) {
        currCommands.add(c);
      }
    }

    for (IShape s : currShape) {
      for (ICommand c : currCommands) {
        c.updateShape(s, time);
      }
      results.add(s);
    }

    for (int j = 1; j < (getMaxLayer() + 1) ; j++) {
      for (int i = 0; i < results.size(); i++) {
        if (results.get(i).getLayer() == j) {
          layerlos.add(results.get(i));
        }
      }

    }
    return layerlos;
  }


  /**
   * This is a method for calling the timer to start.
   */
  public void start(List<IShape> los, List<ICommand> loc) {
    setLoc(loc);
    setLos(los);
    t.start();
    start = true;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (IShape s : instantShapes) {
      if (panelmap.containsKey(s.getName())) {
        float red = s.getColor().getRed();
        float green = s.getColor().getGreen();
        float blue = s.getColor().getBlue();

        if (s.getType() == ShapeType.RECTANGLE) {

          g2d.setPaint(new Color(red, green, blue));
          g2d.fillRect((int) s.getPos().getX(),
                  (int) s.getPos().getY(), (int) s.getSize().getX(), (int) s.getSize().getY());
        } else {
          g2d.setPaint(new Color(red, green, blue));
          g2d.fillOval((int) s.getPos().getX(),
                  (int) s.getPos().getY(), (int) s.getSize().getX() * 2,
                  (int) s.getSize().getY() * 2);
        }
      }
    }
  }

  /**
   * To set the tempo.
   *
   * @param i indicates an int.
   */
  protected void setTempo(int i) {
    int dec = speed - 5;
    if (i == 1) {
      this.speed = speed + 5;
    } else if (i == 0) {
      if (dec <= 0) {
        this.speed = 1;
      } else {
        speed = dec;
      }
    }
  }


  /**
   * To set the boolean value, loop, to be true.
   */
  protected void looping() {
    this.loop = true;
  }

  /**
   * To set the boolean value, loop, to be false.
   */
  protected void notLooping() {
    this.loop = false;
  }

  /**
   * To reset the list of IShape and list of ICommand as their copies when calling restart.
   */
  protected void restart() {
    resetLos();
    resetLoc();
  }

  /**
   * To set the panelmap as the given one.
   *
   * @param map indicates a HashMap.
   */
  protected void setPanel(Map<String, IShape> map) {
    this.panelmap = map;
  }
}
