package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;


/**
 * This class represents a HybridView that will interact with the user will have capabilities of the
 * visual view, in that it will actually show the animation being played. It will also have
 * capabilities of the SVG view, in that it will be able to create and export the animation in SVG
 * format.
 */
public class HybridView extends JFrame implements IView {
  protected List<IShape> los;
  protected List<ICommand> loc;
  protected List<IShape> svglos;
  protected List<ICommand> svgloc;

  protected Appendable ap;
  protected int tempo;
  protected AnimationPanel panel;
  protected boolean pause;
  protected boolean needLoop;

  protected JButton startButton;
  protected JButton exitButton;
  protected JButton pauseButton;
  protected JButton loopButton;
  protected JButton restartButton;
  protected JButton upButtom;
  protected JButton downButtom;
  protected JButton exportButton;
  protected JButton selectAllButton;

  //****************************
  protected JButton changeColor;

  protected JCheckBox[] checkBoxes;

  protected JPanel controlPanel;
  protected Map<String, IShape> chosenShapes;

  protected SVGView svg;


  /**
   * Constructs a HybridView.
   *
   * @param los      indicates a list of IShapes.
   * @param loc      indicates a list of ICommands.
   * @param tempo    indicates a tempo.
   * @param needLoop indicates a loop that represents either loop or not.
   */
  public HybridView(List<IShape> los, List<ICommand> loc, int tempo, boolean needLoop) {
    super();
    if (los == null || loc == null || tempo < 0) {
      throw new IllegalStateException("");
    } else {
      this.los = los;
      this.loc = loc;
      this.svgloc = loc;
      this.svglos = los;

      this.tempo = tempo;
      this.ap = new StringBuffer();
      this.chosenShapes = new HashMap<String, IShape>();
      this.needLoop = false;
      this.svg = new SVGView(los, loc, ap, tempo, needLoop);
      this.panel = new AnimationPanel(los, loc, tempo);
      this.controlPanel = new JPanel();


      panel.setPreferredSize(new Dimension(800, 800));
      this.add(panel, BorderLayout.CENTER);

      // control panel
      controlPanel.setLayout(new FlowLayout());
      this.add(controlPanel, BorderLayout.SOUTH);

      //add start button
      startButton = new JButton("Start");
      startButton.setActionCommand("Start Button");
      controlPanel.add(startButton);

      //add exit button
      exitButton = new JButton("Exit");
      exitButton.setActionCommand("Exit Button");
      controlPanel.add(exitButton);

      //add pause button
      pauseButton = new JButton("Pause/Resume");
      pauseButton.setActionCommand("Pause Button");
      controlPanel.add(pauseButton);

      //add loop button
      loopButton = new JButton("Loop Back");
      loopButton.setActionCommand("Loop Button");
      controlPanel.add(loopButton);

      //add restart button
      restartButton = new JButton("Restart");
      restartButton.setActionCommand("Restart Button");
      controlPanel.add(restartButton);

      //add speed up button
      upButtom = new JButton("Speed up");
      upButtom.setActionCommand("Up Button");
      controlPanel.add(upButtom);

      //add speed down button
      downButtom = new JButton("Slow down");
      downButtom.setActionCommand("Down Button");
      controlPanel.add(downButtom);

      //add select all button
      selectAllButton = new JButton("Select All");
      selectAllButton.setActionCommand("Select Button");
      controlPanel.add(selectAllButton);

      exportButton = new JButton("Export");
      exportButton.setActionCommand("Export Button");
      controlPanel.add(exportButton);


      this.pack();

      JPanel checkBoxPanel = new JPanel();
      checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Choose Shapes"));

      checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

      checkBoxes = new JCheckBox[los.size()];


      for (int i = 0; i < checkBoxes.length; i++) {
        checkBoxes[i] = new JCheckBox(los.get(i).getName());
        checkBoxes[i].setSelected(false);
        checkBoxes[i].setActionCommand(los.get(i).getName());
        checkBoxPanel.add(checkBoxes[i]);

      }
      checkBoxPanel.setPreferredSize(new Dimension(150, 10000));
      JScrollPane scrollBox = new JScrollPane(checkBoxPanel);
      this.add(scrollBox, BorderLayout.EAST);
      this.setVisible(true);
    }
  }


  @Override
  public void addActionListener(ActionListener l) throws UnsupportedOperationException {
    startButton.addActionListener(l);
    exitButton.addActionListener(l);
    pauseButton.addActionListener(l);
    loopButton.addActionListener(l);
    restartButton.addActionListener(l);
    upButtom.addActionListener(l);
    downButtom.addActionListener(l);
    exportButton.addActionListener(l);
    selectAllButton.addActionListener(l);
  }

  @Override
  public void loopBack() throws UnsupportedOperationException {
    if (needLoop) {
      panel.notLooping();
    } else {
      panel.looping();
    }
    needLoop = !needLoop;
  }


  @Override
  public void speedUp() throws UnsupportedOperationException {
    panel.setTempo(1);
  }

  @Override
  public void slowDown() throws UnsupportedOperationException {
    panel.setTempo(0);
  }


  @Override
  public String outString() throws UnsupportedOperationException {

    return this.svg.outString();

  }

  @Override
  public void outPutView(List<IShape> los, List<ICommand> loc) {
    panel.start(los, loc);
  }

  @Override
  public void pause() throws UnsupportedOperationException {
    if (!pause) {
      panel.t.stop();
      pause = true;
    } else {
      panel.t.restart();
      pause = false;
    }
  }

  @Override
  public void addItemListener(ItemListener l) throws UnsupportedOperationException {
    for (int i = 0; i < los.size(); i++) {
      checkBoxes[i].addItemListener(l);
    }
  }


  @Override
  public void restart(List<IShape> los, List<ICommand> loc) throws UnsupportedOperationException {
    panel.counter = 0;
    panel.resetLoc();
    panel.resetLos();
    panel.t.start();
  }


  @Override
  public void removeOrAdd(IShape s) {
    if (!(this.chosenShapes.containsKey(s.getName()))) {
      chosenShapes.put(s.getName(), s);
    } else {
      chosenShapes.remove(s.getName(), s);
    }
    panel.setPanel(chosenShapes);
    svglos = new ArrayList<>(chosenShapes.values());
  }

  @Override
  public void selectAll() throws UnsupportedOperationException {
    Map<String, IShape> map = new HashMap<>();
    for (int i = 0; i < los.size(); i++) {
      map.put(los.get(i).getName(), los.get(i));
      checkBoxes[i].setSelected(true);
    }
    panel.setPanel(map);
    svglos = los;
  }


  @Override
  public void exportSVG() {
    List<ICommand> commands = new ArrayList<>();
    for (IShape s : svglos) {
      for (ICommand c : svgloc) {
        if (c.getShapeName().equals(s.getName())) {
          commands.add(c);
        }
      }
    }

    svgloc = commands;
    this.svg = new SVGView(svglos, svgloc, ap, this.tempo, needLoop);

    final JFileChooser CHOOSE = new JFileChooser();
    int v = CHOOSE.showSaveDialog(this.getContentPane());
    if (v == JFileChooser.APPROVE_OPTION) {
      File file = CHOOSE.getSelectedFile();

      File svgf = new File(file.getAbsolutePath() + ".svg");
      try {
        FileWriter writer = new FileWriter(svgf);
        svg.outPutView(svglos, svgloc);
        writer.write(svg.outString());
        writer.flush();
        writer.close();
      } catch (IOException e) {
        System.out.print("Error");
      }
    }
  }

    @Override
    public void backgroundColor() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


}
