import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

//ウインドウを表すクラス
public class MyApplication extends JFrame
{
	public MyApplication() {
		super("My Painter");
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		this.getContentPane().add(jp);
		
		MyCanvas canvas = new MyCanvas();
		canvas.addDrawing(new MyOval(25, 20, 60, 60, Color.black, Color.white, true));
		canvas.addDrawing(new MyHendecagonal(150, 50, 30, 30));
		canvas.addDrawing(new MyTriangle(240, 50, 30, 30));
		canvas.addDrawing(new MyRectangle(310, 25, 50, 50));
		jp.add(BorderLayout.CENTER, canvas);
		
		//WindowEvent リスナを設定(無名クラスを利用している)
		this.addWindowListener(
				new WindowAdapter() {
					//ウインドウが閉じたら終了する処理
					public void windowClosing(WindowEvent e) {
						System.exit(1);
					}
				}
			);
		}
	
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.setSize(400,400);
		app.setVisible(true);
	}
}