package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.animator.util.TweenModelBuilder;

/**
 * Changes we made here: -We add a new method, sendShapeState(int time), to returns a list of shape
 * that has some command to execute in the current given time. And we could directly send the
 * information of those shapes to our view and let the view to show out. -We add a new constructor
 * particular for the Builder. -We add a copy constructor so that in the view, it could take a copy
 * constructor of the model. -We add a method, String svgModel(int tempo), to output the model's
 * state in string as svg format. -We add a nested class, Builder, that implements TweenModelBuilder
 * in order to let the builder to read information from model.
 */

/**
 * A class is to represents Animation Model, which implements IAnimationOpration.
 */
public final class AnimationModel implements IAnimationOperation {
    private List<IShape> los;
    private List<ICommand> loc;
    private java.awt.Color c;

    /**
     * Constructs an Animation model.
     */
    public AnimationModel() {
        this.loc = new ArrayList<ICommand>();
        this.los = new ArrayList<IShape>();
        this.c = new java.awt.Color(255, 255, 255);

    }

    /**
     * Constructs an Animation model particular for the Builder.
     *
     * @param b indicates the Builder.
     */
    public AnimationModel(Builder b) {
        List<ICommand> check = b.loc;
        this.los = b.los;
        for (int i = 0; i < check.size(); i++) {
            for (int j = 0; j < los.size(); j++) {
                if (check.get(i).getShapeName().equals(los.get(j).getName())) {
                    check.get(i).setShape(los.get(j));
                }
            }
            this.loc = check;
            this.c = b.c;
        }
    }


    /**
     * Constructs a Copy Constructor of the AnimationModel.
     *
     * @param am the AnimationModel it needed to be copied.
     * @throws IllegalStateException when am is null.
     */
    public AnimationModel(IAnimationOperation am) {
        List<IShape> loshape = new ArrayList<IShape>();
        List<ICommand> locommand = new ArrayList<ICommand>();
        if (am == null) {
            throw new IllegalStateException("");
        } else {
            for (IShape s : am.getLoShape()) {
                loshape.add(s.getCopyofShape());
            }
            this.los = loshape;

            for (ICommand c : am.getLoCommand()) {
                locommand.add(c.getCopyofCommand());
            }
            this.loc = locommand;
            this.c = am.getColor();
        }
    }


    @Override
    public void addAShape(IShape s) {
        this.los.add(s);
        Collections.sort(los);
    }

    @Override
    public java.awt.Color getColor() {
        return new java.awt.Color(c.getRGB());
    }

    /**
     * This method is check for whether two incompatible commands for the same shape is overlapping in
     * the same time intervals.
     *
     * @param c A command that need to be added.
     * @return int to represent overlap or not.
     */
    private int overLap(ICommand c) {
        int ans = 1;
        for (int i = 0; i < loc.size(); i++) {
            if (((c.getAppear() > loc.get(i).getAppear())
                    && (c.getAppear() < loc.get(i).getDisappear())) ||
                    ((c.getDisappear() > loc.get(i).getAppear())
                            && (c.getDisappear() <= loc.get(i).getDisappear())) ||
                    ((c.getAppear() <= loc.get(i).getAppear())
                            && (c.getDisappear() >= loc.get(i).getDisappear()))) {
                if ((c.getShapeName() == loc.get(i).getShapeName())
                        && (c.getName() == loc.get(i).getName())) {
                    ans = 0;
                    break;
                }
            }
        }
        return ans;
    }


    @Override
    public void addACommand(ICommand c) {
        if (overLap(c) == 0) {
            throw new IllegalArgumentException("Overlapping commands.");
        } else {
            this.loc.add(c);
            Collections.sort(loc);
        }
    }

    @Override
    public void deleteAShape(IShape s) {
        if (los.contains(s)) {
            los.remove(s);
        } else {
            throw new IllegalArgumentException("Invalid delete");
        }
    }

    @Override
    public void deleteACommand(ICommand c) {
        if (loc.contains(c)) {
            loc.remove(c);
        } else {
            throw new IllegalArgumentException("Invalid delete");
        }
    }

    @Override
    public void checkLayerOrder() {
        List<IShape> loshapes = new ArrayList<>();
        for (int i = 0; i < los.size() - 1; i++) {
            if (los.get(i).getLayer() < los.get(i + 1).getLayer()) {
                loshapes.add(los.get(i));
            }
        }
    }


    @Override
    public List<IShape> getLoShape() {
        return new ArrayList<IShape>(los);
    }

    @Override
    public List<ICommand> getLoCommand() {
        return new ArrayList<ICommand>(loc);
    }

    /**
     * This represents the nested static class. That helps the TweenModelBuilder reads the animation
     * file.
     */
    public static final class Builder implements TweenModelBuilder<IAnimationOperation> {
        protected List<IShape> los;
        protected List<ICommand> loc;
        private java.awt.Color c;

        /**
         * Constructs a Builder.
         */
        public Builder() {
            this.los = new ArrayList<>();
            this.loc = new ArrayList<>();
            this.c = java.awt.Color.white;
        }


        @Override
        public TweenModelBuilder<IAnimationOperation> addOval(String name, float cx, float cy,
                                                              float xRadius, float yRadius,
                                                              float red, float green, float blue,
                                                              int startOfLife, int endOfLife, int layer) {
            los.add(new Oval(name, new Size(xRadius, yRadius), new Position2D(cx, cy),
                    new Color(red, green, blue), startOfLife, endOfLife, layer));
            return this;
        }

        @Override
        public TweenModelBuilder<IAnimationOperation> addRectangle(String name, float lx, float ly,
                                                                   float width, float height, float red,
                                                                   float green, float blue,
                                                                   int startOfLife, int endOfLife, int layer) {
            Rectangle s = new Rectangle(name, new Size(width, height), new Position2D(lx, ly),
                    new Color(red, green, blue), startOfLife, endOfLife, layer);
            los.add(s);
            return this;
        }

        @Override
        public TweenModelBuilder<IAnimationOperation> addMove(String name, float moveFromX,
                                                              float moveFromY, float moveToX,
                                                              float moveToY, int startTime,
                                                              int endTime) {
            loc.add(new ChangePos(name, startTime, endTime, new Position2D(moveFromX, moveFromY),
                    new Position2D(moveToX, moveToY)));
            return this;
        }

        @Override
        public TweenModelBuilder<IAnimationOperation> addColorChange(String name, float oldR,
                                                                     float oldG, float oldB,
                                                                     float newR, float newG,
                                                                     float newB, int startTime,
                                                                     int endTime) {
            loc.add(new ChangeColor(name, startTime, endTime, new Color(oldR, oldG, oldB),
                    new Color(newR, newG, newB)));
            return this;
        }

        @Override
        public TweenModelBuilder<IAnimationOperation> addScaleToChange(String name, float fromSx,
                                                                       float fromSy, float toSx,
                                                                       float toSy, int startTime,
                                                                       int endTime) {
            loc.add(new ChangeScales(name, startTime, endTime, new Size(fromSx, fromSy),
                    new Size(toSx, toSy)));
            return this;
        }

        @Override
        public TweenModelBuilder<IAnimationOperation> addBackground(int red, int green, int blue) {
            c = new java.awt.Color((float) red, (float) green, (float) blue);
            return this;
        }

        @Override
        public IAnimationOperation build() {
            return new AnimationModel(this);
        }
    }


}
