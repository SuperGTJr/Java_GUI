public class RectState extends State{
	StateManager stateManager;
	MyRectangle rect;
	
	public RectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		rect = new MyRectangle(x, y, 0, 0);
		stateManager.canvas.addDrawing(rect);
	}
	
	public void mouseUp(int x, int y) {
		int w = rect.getW();
		int h = rect.getH();
		if((w<1 && w>-1) || (h<1 && h>-1)) {
			stateManager.canvas.removeDrawing(rect);
		}
	}
	
	public void mouseDrag(int x, int y) {
		int x0 = rect.getX();
		int y0 = rect.getY();
		int w = x - x0;
		int h = y - y0;
		stateManager.canvas.removeDrawing(rect);
		rect = new MyRectangle(x0, y0, w, h);
//		rect.setLength(stateManager.getLength());
//		rect.setSpace(stateManager.getSpace());
		rect.setDashed(stateManager.getDashed());
		rect.setShadowed(stateManager.getShadowed());
		rect.setLines(stateManager.getLines());
		rect.setWidth(stateManager.getWidth());
		stateManager.canvas.addDrawing(rect);
	}
}