import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineWidthSpinner extends JSpinner {
    private StateManager stateManager;

    public LineWidthSpinner(StateManager stateManager) {
        super(new SpinnerNumberModel(1, 1, 25, 1));

        addChangeListener(new LineWidthListener());
        
        this.stateManager = stateManager;

    }

    class LineWidthListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int lineWidth = (int) getValue();
            stateManager.setWidth(lineWidth);
        }
    }
}
