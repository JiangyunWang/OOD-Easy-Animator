package cs3500.animator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.model.IAnimationOperation;
import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IView;
import javafx.scene.control.TextFormatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * A Controller interacts with HybridView and IAnimationOperation model. It has
 * configureButtonListener and configureItemListen, and some nested class for buttons.
 */
public class Controller{
  private IView view;
  private boolean stop;
  private List<IShape> los;
  private List<ICommand> loc;
  private IAnimationOperation model;
  protected Map<String, Runnable> buttonMap;


  /**
   * Constructs a controller.
   *
   * @param model indicates and IAnimationOperation.
   * @param view  indicates an IView.
   */
  public Controller(IAnimationOperation model, IView view) {
    if (model == null || view == null) {
      throw new IllegalStateException();
    } else {
      this.model = model;
      this.view = view;
      this.los = model.getLoShape();
      this.loc = model.getLoCommand();
      this.buttonMap = new HashMap<>();
      configureButtonListener();
      configureItemListen();
    }
  }


  /**
   * Creates and sets a button listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a button is click, only for those that the program needs.
   */
  public void configureButtonListener() {
    ButtonListener blistener = new ButtonListener();

    buttonMap.put("Start Button", new Start());
    buttonMap.put("Exit Button", new Exit());
    buttonMap.put("Pause Button", new MakePause());
    buttonMap.put("Loop Button", new Loop());
    buttonMap.put("Restart Button", new Restart());
    buttonMap.put("Up Button", new SpeedUp());
    buttonMap.put("Down Button", new SlowDown());
    buttonMap.put("Export Button", new Export());
    buttonMap.put("Select Button", new SelectAll());
    buttonMap.put("Change", new ChangeBG());
    blistener.setButtonClickedActionMap(buttonMap);
    this.view.addActionListener(blistener);

  }

  /**
   * Creates and sets a Item listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a check box is click, the view will display the objects that
   * user wanted. .
   */
  public void configureItemListen() {
    Map<String, Runnable> itemMap = new HashMap<>();
    for (int i = 0; i < los.size(); i++) {
      IShape s = model.getLoShape().get(i);
      itemMap.put(s.getName(), () -> {
        this.view.removeOrAdd(s);
      });
    }

    ItemListen listen = new ItemListen(itemMap);
    this.view.addItemListener(listen);
  }


//  public void configureChangeListener() {
//    Map<Integer, Runnable> changeMap = new HashMap<>();
//    for (int i = 0; i < los.size(); i++) {
//      IShape s = model.getLoShape().get(i);
//      changeMap.put(s.getDisappear(), () -> {
//        this.
//      }
//  }




  /**
   * A Runnable object, when the user click pause button, the animation will make pause or resume.
   */
  class MakePause implements Runnable {
    @Override
    public void run() {
      view.pause();
    }
  }


  /**
   * A Runnable object, when the user click start button, the animation will start.
   */
  class Start implements Runnable {
    @Override
    public void run() {
      if (stop) {
        stop = false;
      } else {
        stop = true;
        view.outPutView(los, loc);
      }
    }
  }

  /**
   * A Runnable object, when the user click speed up button, the animation will speed up.
   */
  class SpeedUp implements Runnable {
    @Override
    public void run() {
      view.speedUp();
    }
  }

  /**
   * A Runnable object, when the user click speed up button, the animation will slow down.
   */
  class SlowDown implements Runnable {
    @Override
    public void run() {
      view.slowDown();
    }
  }

  /**
   * A Runnable object, when the user click exit button, the animation will exit.
   */
  class Exit implements Runnable {
    @Override
    public void run() {
      System.exit(0);
    }
  }

  /**
   * A Runnable object, when the user click loop button, the animation will either loop or not.
   */
  class Loop implements Runnable {
    @Override
    public void run() {
      view.loopBack();
    }
  }

  /**
   * A Runnable object, when the user click restart button, the animation will restart.
   */
  class Restart implements Runnable {
    @Override
    public void run() {
      view.restart(los, loc);
    }
  }

  /**
   * A Runnable object, when the user click export button, the animation will export it as svg
   * format.
   */
  class Export implements Runnable {
    @Override
    public void run() {
      view.exportSVG();
    }
  }

  /**
   * A Runnable object, when the user click selectAll button, the animation will select all shapes
   * to display.
   */
  class SelectAll implements Runnable {
    @Override
    public void run() {
      view.selectAll();
    }
  }

  class ChangeBG implements Runnable {
    @Override
    public void run() {
      view.backgroundColor();
    }
  }
}
