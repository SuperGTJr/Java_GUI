import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DashLengthSpinner extends JSpinner{
	StateManager stateManager;
	
	public DashLengthSpinner(StateManager stateManager) {
		super(new SpinnerNumberModel(10, 1, 100, 1));
		
		addChangeListener(new DashLengthListener());
		
		this.stateManager = stateManager;
	}
	
	class DashLengthListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			float length = (float)getValue();
			stateManager.setLength(length);
		}
	}
}