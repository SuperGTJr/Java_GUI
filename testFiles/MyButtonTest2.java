package testFiles;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyButtonTest2 extends JFrame
{
	public MyButtonTest2() {
		super("MyButtonTest");
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		getContentPane().add(jp);
		
		Button rectButton = new Button("Rectangle");
		rectButton.addActionListener(new RectButtonListener());
		jp.add(rectButton);
		
		Button circleButton = new Button("circle");
		circleButton.addActionListener(new CircleButtonListener());
		jp.add(circleButton);
		
		Button triangleButton = new Button("triangle");
		triangleButton.addActionListener(new TriangleButtonListener());
		jp.add(triangleButton);
		
		setSize(300, 250);
	}
	
	public static void main(String[] args) {
		MyButtonTest2 myapp = new MyButtonTest2();
		myapp.setVisible(true);
	}
}

//Rectボタンのイベントリスナ
class RectButtonListener implements ActionListener{
	//rectボタンが押されたときの処理をここに書く
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Rect is pressed.");
	}
}

//Circleボタンのイベントリスナ
class CircleButtonListener implements ActionListener{
	//circleボタンが押されたときの処理をここに書く
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Circle is pressed.");
	}
}

//Triangleボタンのイベントリスナ
class TriangleButtonListener implements ActionListener{
	//triangleボタンが押されたときの処理をここに書く
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Triangle is pressed.");
	}
}
