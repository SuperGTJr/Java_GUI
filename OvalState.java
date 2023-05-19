public class OvalState extends State{
	StateManager stateManager;
	
	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		stateManager.canvas.addDrawing(new MyOval(x, y, 20, 20));
	}
	
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y) {}
}