import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DashSpaceSpinner extends JSpinner{
	StateManager stateManager;
	
	public DashSpaceSpinner(StateManager stateManager) {
		super(new SpinnerNumberModel(10, 1, 100, 1));
		
		addChangeListener(new DashSpaceListener());
		
		this.stateManager = stateManager;
	}
	
	class DashSpaceListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			float space = (float)getValue();
			stateManager.setSpace(space);
		}
	}
}