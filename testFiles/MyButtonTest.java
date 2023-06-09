import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyButtonTest extends JFrame
{
	public MyButtonTest() {
		super("MyButtonTest");
		
		//FlowLayoutを使って部品を左から配置するパネルを作成
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		getContentPane().add(jp);
		
		Button rectButton = new Button("Rectangle");
		jp.add(rectButton);
		
		Button circleButton = new Button("Circle");
		jp.add(circleButton);
		
		Button triangleButton = new Button("Triangle");
		jp.add(triangleButton);
		
		setSize(300, 250);
	}
	
	public static void main(String[] args) {
		MyButtonTest myapp = new MyButtonTest();
		myapp.setVisible(true);
	}
}