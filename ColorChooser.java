import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ColorChooser extends JColorChooser{
    Mediator mediator;
    Color color;
    String type;

    public ColorChooser(Mediator mediator, String type) {
    	super(Color.red);
    	getSelectionModel().addChangeListener(new ColorListener());
    	
        this.mediator = mediator;
        this.type = type;
    }
    
    class ColorListener implements ChangeListener{
    	 public void stateChanged(ChangeEvent e) {
    		 color = getColor();
    		if(mediator.selectedDrawings != null) {
    			if(type == "LINE") {
    				mediator.setLineColor(color);
    				mediator.repaint();
    			}
    			else if(type == "FILL") {
    				mediator.setFillColor(color);
    				mediator.repaint();
    			}
    		}
    	}
    }
    
}
