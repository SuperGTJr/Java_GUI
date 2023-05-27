import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class ShadowCheck extends JCheckBox{
	StateManager stateManager;
	
	public ShadowCheck(StateManager stateManager) {
		super("shadow check");
		
		addItemListener(new DashListener());
		
		this.stateManager = stateManager;
	}
	
	class DashListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state==ItemEvent.SELECTED) {
				stateManager.setShadowed(true);
			}else {
				stateManager.setShadowed(false);
			}
		}
	}
}