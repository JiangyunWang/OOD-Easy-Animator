package cs3500.animator.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JCheckBox;

/**
 * This class represents a Item listener. It is configurable by the controller that instantiates
 * it.
 */
public class ItemListen implements ItemListener {
  Map<String, Runnable> checkB;

  /**
   * Constructs an ItemListen.
   *
   * @param checkB indicates a Map that its key is a String and its value is a runnable object.
   */
  public ItemListen(Map<String, Runnable> checkB) {
    super();
    this.checkB = checkB;
  }


  @Override
  public void itemStateChanged(ItemEvent e) {
    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (checkB.containsKey(who)) {
      checkB.get(who).run();
    }
  }
}
