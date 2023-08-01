import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FreePolylineButton extends JButton{
	StateManager stateManager;
	
	public FreePolylineButton(StateManager stateManager) {
		super("Free");
		
		addActionListener(new FreePolylineListener());
		
		this.stateManager = stateManager;
	}
	
	class FreePolylineListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new FreePolylineState(stateManager));
		}
	}
}