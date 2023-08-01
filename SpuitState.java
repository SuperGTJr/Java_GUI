import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class SpuitState extends State{
	StateManager stateManager;
	String type;
	
	public SpuitState(StateManager stateManager, String type) {
		this.stateManager = stateManager;
		this.type = type;
	}
	
	public void mouseDown(int x, int y) {
		try {
			Point displayPoint = MouseInfo.getPointerInfo().getLocation();
	        int displayX = (int) displayPoint.getX();
	        int displayY = (int) displayPoint.getY();

			 // クラスRobotによりクリックした位置の色を取得
			 Robot robot = new Robot();
             Color color = robot.getPixelColor(displayX, displayY);
            
             // 現在選択中のオブジェクトの色を変更
             if(type == "FILL") {
            	 stateManager.mediator.setFillColor(color);
             }
             else if(type == "LINE") {
                stateManager.mediator.setLineColor(color);
             }
             stateManager.mediator.repaint();
		}
		catch (Exception ex) {
		}
	}
	
	public void mouseUp(int x, int y) {}
	
	public void mouseDrag(int x, int y) {}
}