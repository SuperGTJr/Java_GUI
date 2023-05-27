import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class DashCheck extends JCheckBox{
	StateManager stateManager;
	
	public DashCheck(StateManager stateManager) {
		super("dash check");
		
		addItemListener(new DashListener());
		
		this.stateManager = stateManager;
	}
	
	class DashListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state==ItemEvent.SELECTED) {
				stateManager.setDashed(true);
			}else {
				stateManager.setDashed(false);
			}
		}
	}
}