public class HendecagonalState extends State{
	StateManager stateManager;
	MyHendecagonal hendecagonal;
	
	public HendecagonalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		hendecagonal = new MyHendecagonal(x, y, 0, 0);
		stateManager.mediator.addDrawing(hendecagonal);
	}
	
	public void mouseUp(int x, int y) {
		int w = hendecagonal.getW();
		int h = hendecagonal.getH();
		if((w<1 && w>-1) || (h<1 && h>-1)) {
			stateManager.mediator.removeDrawing(hendecagonal);
		}
		hendecagonal.setRegion();
	}
	
	public void mouseDrag(int x, int y) {
		int x0 = hendecagonal.getX();
		int y0 = hendecagonal.getY();
		int w = x - x0;
		int h = y - y0;
//		stateManager.mediator.removeDrawing(hendecagonal);
//		hendecagonal = new MyHendecagonal(x0, y0, w, h);
		hendecagonal.setLocation(x0, y0);
		hendecagonal.setSize(w, h);
//		stateManager.mediator.addDrawing(hendecagonal);
	}
}