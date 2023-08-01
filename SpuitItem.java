import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class SpuitItem extends JMenuItem{
    StateManager stateManager;
    String type;

    public SpuitItem(StateManager stateManager, String name, String type) {
    	super(name);
    	addActionListener(new SpuitListener());
    	
        this.stateManager = stateManager;
        this.type = type;
    }
    
    class SpuitListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		 stateManager.setState(new SpuitState(stateManager, type));
    	 }
    }
}
