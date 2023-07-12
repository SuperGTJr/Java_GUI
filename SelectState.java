import java.awt.Color;
import java.util.Enumeration;

public class SelectState extends State{
	
	StateManager stateManager;
	MyRectangle area;
	boolean multiFlag = false;
	int x0;
	int y0;
	int x0_a;
	int y0_a;
	
	
	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		MyDrawing front = stateManager.mediator.getFront(x, y);
		if(front != null) {
			if(stateManager.mediator.getSelectedDrawing().contains(front)) {
				x0 = x;
				y0 = y;
				//応急処置：なぜかisSelected=falseになってしまうので……
				for(Enumeration<MyDrawing> e = stateManager.mediator.selectedDrawing.elements(); e.hasMoreElements();) {
					MyDrawing d = e.nextElement();
					d.setSelected(true);
				}	
			}else {
				stateManager.mediator.setSelected(x, y);
				x0 = x;
				y0 = y;
			}
		}else{
			x0_a = x;
			y0_a = y;
			area = new MyRectangle(x, y, 0, 0);
			area.setDashed(true);
			area.setFillColor(new Color(0,0,0,0));
			stateManager.mediator.addDrawing(area);
			multiFlag = true;
		}
	}
	
	public void mouseUp(int x, int y) {
		if(multiFlag) {
			stateManager.mediator.setSelectedByArea(area);
			stateManager.mediator.removeDrawing(area);
//			System.out.println(stateManager.mediator.selectedDrawing);
			multiFlag = false;
		}
	}
	
	public void mouseDrag(int x, int y) {
		if (!multiFlag) {
			// 前回の座標からの変位を計算：最初はdx=dy=0になる
			int dx = x - x0; 
	        int dy = y - y0;

	     // 図形の移動処理を呼び出す
	        stateManager.mediator.move(dx, dy); 

	     // 現在の座標を保存する
	        x0 = x; 
	        y0 = y;
		}else {
			int w = x - x0_a;
			int h = y - y0_a;

			area.setSize(w, h);
		}
		
	}
}