import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class DashCheck extends JCheckBox{
	Mediator mediator;
	
	public DashCheck(Mediator mediator) {
		super("dash check");
		
		addItemListener(new DashListener());
		
		this.mediator = mediator;
	}
	
	class DashListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state==ItemEvent.SELECTED) {
				mediator.setDashed(true);
				mediator.repaint();
			}else {
				mediator.setDashed(false);
				mediator.repaint();
			}
		}
	}
}