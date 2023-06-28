import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineWidthSpinner extends JSpinner {
    private Mediator mediator;

    public LineWidthSpinner(Mediator mediator) {
        super(new SpinnerNumberModel(1, 1, 25, 1));

        addChangeListener(new LineWidthListener());
        
        this.mediator = mediator;
    }
    
//    public void update() {
//    	int lineWidth = mediator.selectedDrawing.getLineWidth();
//    	setValue(lineWidth);
//    }
    
    class LineWidthListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int lineWidth = (int) getValue();
            mediator.setLineWidth(lineWidth);
            mediator.repaint();
        }
    }
}
