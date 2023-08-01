import java.awt.Point;

public class FreePolylineState extends State{
	StateManager stateManager;
	MyFreePolyline freePolyline;
	Point startPoint, currentPoint, lastPoint;

	public FreePolylineState(StateManager stateManager) {
		this.stateManager = stateManager;
		freePolyline = new MyFreePolyline();
	}
	
	public void mouseDown(int x, int y) {
		if(freePolyline.vertices.isEmpty()) {
			//始点がまだ設定されていない場合
			startPoint = new Point(x,y);
			currentPoint = new Point(startPoint.x, startPoint.y);
			lastPoint = new Point(startPoint.x, startPoint.y);
			freePolyline.addVertex(startPoint);
			freePolyline.setStartPoint(startPoint);
			stateManager.mediator.addDrawing(freePolyline);
			freePolyline.setRegion();
	        stateManager.mediator.repaint();
			
		}else {
			Point vertex = new Point(x, y);
			double distanceFromStart = Math.sqrt(((vertex.x-startPoint.x) * (vertex.x-startPoint.x)) + ((vertex.y-startPoint.y) * (vertex.y-startPoint.y)));
			double distanceFromCurrent = Math.sqrt(((vertex.x-currentPoint.x) * (vertex.x-currentPoint.x)) + ((vertex.y-currentPoint.y) * (vertex.y-currentPoint.y)));
			if(distanceFromStart <= 4) {
				if(freePolyline.vertices.size() == 1) {
					//1点だけの場合
					stateManager.mediator.removeDrawing(freePolyline);
					freePolyline = new MyFreePolyline();
				}else {
					//閉じる場合
					freePolyline.addVertex(lastPoint);
					freePolyline.setStartPoint(null);
					freePolyline.setCurrentPoint(null);
					freePolyline.setRegion();
			        stateManager.mediator.repaint();
			        freePolyline = new MyFreePolyline();
				}
			}else if(distanceFromCurrent <= 4) {
				//最新の点をクリックした場合
				freePolyline.setStartPoint(null);
				freePolyline.setCurrentPoint(null);
				freePolyline.setRegion();
		        stateManager.mediator.repaint();
		        freePolyline = new MyFreePolyline();
			}else {
				//閉じない場合
				freePolyline.addVertex(vertex);
				freePolyline.setCurrentPoint(vertex);
				currentPoint = freePolyline.getCurrentPoint();
		        stateManager.mediator.repaint();
			}
		}
		System.out.println(freePolyline.vertices);
	}
	
	public void mouseUp(int x, int y) {
	}
	
	public void mouseDrag(int x, int y) {
	}
}