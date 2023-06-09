public class TriangleState extends State{
	StateManager stateManager;
	MyTriangle triangle;
	
	public TriangleState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		triangle = new MyTriangle(x, y, 0, 0);
		stateManager.mediator.addDrawing(triangle);
	}
	
	public void mouseUp(int x, int y) {
		int w = triangle.getW();
		int h = triangle.getH();
		if((w<1 && w>-1) || (h<1 && h>-1)) {
			stateManager.mediator.removeDrawing(triangle);
		}
		triangle.setRegion();
	}
	
	public void mouseDrag(int x, int y) {
		int x0 = triangle.getX();
		int y0 = triangle.getY();
		int w = x - x0;
		int h = y - y0;
//		stateManager.mediator.removeDrawing(triangle);
//		triangle = new MyTriangle(x0, y0, w, h);
		triangle.setLocation(x0, y0);
		triangle.setSize(w, h);
//		stateManager.mediator.addDrawing(triangle);
	}
}