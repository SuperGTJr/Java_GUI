import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class SaveAndLoadItem extends JMenuItem{
    Mediator mediator;
    String type;

    public SaveAndLoadItem(Mediator mediator, String name, String type) {
    	super(name);
    	addActionListener(new SaveAndLoadListener());
    	
        this.mediator = mediator;
        this.type = type;
    }
    
    class SaveAndLoadListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		if(mediator.selectedDrawing != null) {
    			if(type == "SAVE") {
    				mediator.saveData();
    			}
    			else if(type == "LOAD") {
    				mediator.loadData();
    				mediator.repaint();
    			}
    		}
    	}
    }
    
}
