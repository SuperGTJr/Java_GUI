import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShortCutKeyListener implements KeyListener {
    Mediator mediator;
    
    public ShortCutKeyListener(Mediator mediator) {
    	this.mediator = mediator;
    }
    
    public void keyPressed(KeyEvent e) {
    	if(e.isControlDown()) {
    		if(e.getKeyCode() == KeyEvent.VK_C) {
    			mediator.copy();
    			System.out.println("copy");
    		}else if(e.getKeyCode() == KeyEvent.VK_X) {
    			mediator.cut();
    		}else if(e.getKeyCode() == KeyEvent.VK_V) {
    			mediator.paste();
    		}
    	}
    	else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            mediator.removeSelectedDrawing();
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}
