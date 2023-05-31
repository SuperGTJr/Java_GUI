import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineSpinner extends JSpinner {
    private StateManager stateManager;

    public LineSpinner(StateManager stateManager) {
        super(new SpinnerNumberModel(1, 1, 10, 1));

        addChangeListener(new LineChangeListener());
        
        this.stateManager = stateManager;

    }

    class LineChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int lines = (int) getValue();
            stateManager.setLines(lines);
        }
    }
}
