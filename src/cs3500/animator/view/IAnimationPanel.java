package cs3500.animator.view;

import cs3500.animator.model.IShape;

import java.util.List;

/**
 * This interface represents the IAnimationPanel.
 */
public interface IAnimationPanel {
    List<IShape> sendShapeState(int time);
}
