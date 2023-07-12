import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

//MenuItemを引数に取ろう
//そしたらButtonと同じ感じに出来るんじゃない？

public class ColorItem extends JMenuItem{
    Mediator mediator;
    Color color;
    String type;

    public ColorItem(Mediator mediator, Color color, String name, String type) {
    	super(name);
    	addActionListener(new ColorListener());
    	
        this.mediator = mediator;
        this.color = color;
        this.type = type;
    }
    
    class ColorListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		if(mediator.selectedDrawing != null) {
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
