public class OvalState extends State{
	StateManager stateManager;
	MyOval oval;
	
	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		oval = new MyOval(x, y, 0, 0);
		stateManager.mediator.addDrawing(oval);
	}
	
	public void mouseUp(int x, int y) {
		int w = oval.getW();
		int h = oval.getH();
		if((w<1 && w>-1) || (h<1 && h>-1)) {
			stateManager.mediator.removeDrawing(oval);
		}
		
		oval.setRegion();
	}
	
	public void mouseDrag(int x, int y) {
		int x0 = oval.getX();
		int y0 = oval.getY();
		int w = x - x0;
		int h = y - y0;

//		stateManager.mediator.removeDrawing(oval);
//		oval = new MyOval(x0, y0, w, h);
		oval.setLocation(x0, y0);
		oval.setSize(w, h);
//		stateManager.mediator.addDrawing(oval);
	}
}