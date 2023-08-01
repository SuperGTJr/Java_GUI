import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class ImageItem extends JMenuItem{
    Mediator mediator;

    public ImageItem(Mediator mediator, String name) {
    	super(name);
    	addActionListener(new ImageListener());
    	
        this.mediator = mediator;
    }
    
    class ImageListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		mediator.loadImage();
    		mediator.repaint();
    	 }
    }
}
