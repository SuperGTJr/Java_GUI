import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseEventTest extends JFrame
{
	MyMouseEventTest(){
		super("Mouse Event Test");
		addMouseListener(new MyMouseListener());
		setSize(100, 100);
	}
	
	public static void main(String[] args) {
		MyMouseEventTest myapp = new MyMouseEventTest();
		myapp.setVisible(true);
	}
}

class MyMouseListener extends MouseAdapter
{
	public void mouseReleased(MouseEvent e) {
		System.out.println("かにぱんを讃えよ");
	}
}