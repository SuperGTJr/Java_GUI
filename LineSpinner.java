import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineSpinner extends JSpinner {
    private Mediator mediator;

    public LineSpinner(Mediator mediator) {
        super(new SpinnerNumberModel(1, 1, 10, 1));

        addChangeListener(new LineChangeListener());
        
        this.mediator = mediator;

    }

    class LineChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int lines = (int) getValue();
            mediator.setLines(lines);
        }
    }
}
