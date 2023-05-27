import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyApplication extends JFrame{
	StateManager stateManager;
	MyCanvas canvas;
	
	public MyApplication() {
		super("My Paint Application");
		
		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(canvas);
		
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		TriangleButton triangleButton = new TriangleButton(stateManager);
		jp.add(triangleButton);
		HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
		jp.add(hendecagonalButton);
		
		DashCheck dashCheck = new DashCheck(stateManager);
		jp.add(dashCheck);
		ShadowCheck shadowCheck = new ShadowCheck(stateManager);
		jp.add(shadowCheck);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		canvas.addMouseListener(new MouseAdapter() {
			//現在の状態のmouseDown処理の呼び出し
			public void mousePressed(MouseEvent e) {
				stateManager.mouseDown(e.getX(), e.getY());
				canvas.repaint();
			}
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(), e.getY());
				canvas.repaint();
			}
		});
		
		canvas.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				stateManager.mouseDrag(e.getX(), e.getY());
				canvas.repaint();
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			//ウインドウを閉じたら終了
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}
	
	public static void main(String args[]) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}