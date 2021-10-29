package cs3500.animator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JFrame;


import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationOperation;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;



import static java.lang.Integer.parseInt;

/**
 * We add one if statement to decide to use which controller according to user's input.
 */

/**
 * This class represents the easyAnimator that helps to run the animation.
 */
public final class EasyAnimator {


  /**
   * The static void main method runs the animation.
   */
  public static void main(String[] args) throws IOException {
    int speed = 1;
    String file = "";
    String out = "out";
    ViewFactory factory = new ViewFactory();
    AnimationFileReader reader = new AnimationFileReader();
    String stringView = null;
    IView vew = null;



    IAnimationOperation am = null;
    JFrame frame = new JFrame("Error1");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(50, 30);
    List<String> list = Arrays.asList(args);

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).equals("-speed")) {
        speed = parseInt(list.get(i + 1));
      } else if (list.get(i).equals("-o")) {
        out = list.get(i + 1);
      } else if (list.get(i).equals("-if")) {
        file = list.get(i + 1);
        am = reader.readFile(file, new AnimationModel.Builder());
      } else if (list.get(i).equals("-iv")) {
        stringView = list.get(i + 1);
      }
    }


    if ((file == null) || (stringView == null) || (speed < 0)
            || (file.equals("")) || (stringView.equals(""))) {
      JOptionPane.showMessageDialog(frame,
              "Error Input.",
              "Inane error",
              JOptionPane.ERROR_MESSAGE);
    } else {
      try {

        if (!out.equals("out")) {
          try {
            new FileWriter(out);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

        if (!(stringView.equals("provider"))) {
          vew = factory.create(stringView, speed, am, true);
        }
      } catch (Exception e) {
        e.printStackTrace();

      }
    }

    FileWriter writer = new FileWriter(out);
    if (stringView.equals("interactive")) {


      Controller controller = new Controller(am, vew);
//    } else if (stringView.equals("provider")) {
//
//      IROAnimationModel iro = new AdapterModel(am, speed);
//      //------------ provider's ------------------
//      IViewInteractive pView = new ViewInteractive(iro);
//      cs3500.animator.PController pcontroller = new cs3500.animator.PController(iro, pView);

    } else {
      vew.outPutView(am.getLoShape(), am.getLoCommand());
      writer.write(vew.outString());
      writer.flush();
      writer.close();
    }
  }
}

