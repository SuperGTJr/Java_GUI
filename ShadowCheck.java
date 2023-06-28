import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class ShadowCheck extends JCheckBox{
	Mediator mediator;
	
	public ShadowCheck(Mediator mediator) {
		super("shadow check");
		
		addItemListener(new ShadowListener());
		
		this.mediator = mediator;
	}
	
	class ShadowListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state==ItemEvent.SELECTED) {
				mediator.setShadowed(true);
				mediator.repaint();
			}else {
				mediator.setShadowed(false);
				mediator.repaint();
			}
		}
	}
}