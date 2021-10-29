package cs3500.animator.view;

import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the new Interactive view which extends the Hybrid view.
 * It has the new functionality of changing background color,
 * drawing shapes by their layers as well as letting the user to drag the progress bar.
 */
public class NewInteractiveView extends HybridView {
    protected JButton changeColor;
    private Color c;
    private JSlider slider;
    private JPanel slidepanel;

    /**
     * Constructs a HybridView.
     *
     * @param los      indicates a list of IShapes.
     * @param loc      indicates a list of ICommands.
     * @param tempo    indicates a tempo.
     * @param needLoop indicates a loop that represents either loop or not.
     */
    public NewInteractiveView(List<IShape> los, List<ICommand> loc, int tempo, boolean needLoop, Color c) {
        super(los, loc, tempo, needLoop);
        changeColor = new JButton("Change Color");
        changeColor.setActionCommand("Change");
        controlPanel.add(changeColor);
        this.c = c;
        super.panel.setBackground(c);

        JPanel dialogBoxesPanel = new JPanel();
        JPanel colorChooserPanel = new JPanel();
        colorChooserPanel.setLayout(new FlowLayout());
        dialogBoxesPanel.add(colorChooserPanel);
        controlPanel.add(dialogBoxesPanel);
        controlPanel.add(colorChooserPanel);
        this.c = new Color(0,0,0);


        slidepanel = new JPanel();
        slidepanel.setPreferredSize(new Dimension(800, 200));
        controlPanel.add(slidepanel, BorderLayout.SOUTH);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);

        Event e = new Event();
        slider.addChangeListener(e);

        slidepanel.add(slider);

    }

    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l);
        changeColor.addActionListener(l);
    }


    @Override
    public void backgroundColor() {
        c = JColorChooser.showDialog(this, "Choose a background color", panel.getBackground());
        System.out.println(c.toString());
        this.panel.setBackground(c);
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
        this.svg = new SVGView(svglos, svgloc, ap, this.tempo, needLoop, c);

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

    /**
     * This method sets the counter to the given one.
     * @param c
     */
    private void setCounter(int c) {
        panel.counter = c;
    }


    /**
     * It represents a nested class for a ChangeEvent which implements the ChangeListener interface.
     */
    class Event implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int v = slider.getValue();
            pause();
            panel.sendShapeState(v);
            panel.repaint();
            setCounter(v);
        }
    }
}

