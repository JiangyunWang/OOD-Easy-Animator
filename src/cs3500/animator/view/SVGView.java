package cs3500.animator.view;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ShapeType;


/**
 * This class represents a SVG view of the simple animation.
 * In this view, it will produce a textual description of the animation
 * that is compliant with the popular SVG file format.
 */
public class SVGView implements IView {
  private Appendable ap;
  private List<IShape> los;
  private List<ICommand> loc;
  private int tempo;
  private boolean loop;
  private Color c;

  /**
   * Constructs a SVGView.
   *
   * @param ap    indicates Appendable.
   * @param tempo indicates the tempo.
   * @throws IllegalStateException when am or ap is null.
   */
  public SVGView(List<IShape> los, List<ICommand> loc, Appendable ap, int tempo, boolean loop) {
    if (ap == null || los == null || loc == null || tempo < 0) {
      throw new IllegalStateException("");
    } else {
      this.los = los;
      this.loc = loc;
      this.ap = ap;
      this.tempo = tempo;
      this.loop = loop;
      this.c = new Color(0,0,0);
    }
  }

  /**
   * Constructs a SVGView.
   *
   * @param ap    indicates Appendable.
   * @param tempo indicates the tempo.
   * @throws IllegalStateException when am or ap is null.
   */
  public SVGView(List<IShape> los, List<ICommand> loc, Appendable ap, int tempo, boolean loop, Color c) {
    if (ap == null || los == null || loc == null || tempo < 0) {
      throw new IllegalStateException("");
    } else {
      this.los = los;
      this.loc = loc;
      this.ap = ap;
      this.tempo = tempo;
      this.loop = loop;
      this.c = c;
    }
  }

  @Override
  public void outPutView(List<IShape> los, List<ICommand> loc) {
    String s = "<svg width=\"1000\" height=\"1000\" version=\"1.1\" \n" +
            "     xmlns=\"http://www.w3.org/2000/svg\" style=\"background-color: rgb( "
            + c.getRed() + "," +  c.getGreen() + "," + c.getBlue() + ");\"> \n\n\n";
    if (loop) {
      int max = 0;
      for (IShape si : los) {
        max = Math.max(max, si.getDisappear());
      }

      s = s + "<rect>\n    <animate id=\"base\" begin=\"0;base.end\" dur=\""
              + (max * 1000) / (float) tempo + "ms\" " + "\n"
              + "    attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n</rect>\n\n";
    }

    s = s + this.svgModel(los, loc, tempo, loop) + "</svg>\n";
    try {
      ap.append(s);
      System.out.println(ap.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid output.");
    }
  }


  /**
   * To output the model's state in string as svg format.
   *
   * @param shapes   indicates a list of shapes.
   * @param commands indicates a list of commands.
   * @param tempo    indicates tempo.
   * @param loop     indicates a boolean that should loop or not.
   */
  private String svgModel(List<IShape> shapes, List<ICommand> commands, int tempo, boolean loop) {
    String s = "";
    for (int i = 0; i < shapes.size(); i++) {
      s = s + shapes.get(i).svgState(tempo, loop);

      for (int j = 0; j < commands.size(); j++) {
        if (commands.get(j).getShapeName().equals(shapes.get(i).getName())) {
          s = s + commands.get(j).svgCommand(tempo, loop);
        }
      }
      if (loop) {
        s = s + shapes.get(i).svgReset();
      }
      if (shapes.get(i).getType() == ShapeType.OVAL) {
        s = s + "</ellipse>\n\n";
      } else {
        s = s + "</rect>\n\n";
      }

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
