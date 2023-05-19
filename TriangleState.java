public class TriangleState extends State{
	StateManager stateManager;
	
	public TriangleState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		stateManager.canvas.addDrawing(new MyTriangle(x, y, 40, 40));
	}
	
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y) {}
}