public class StateManager
{
	State state;
	Mediator mediator;
	boolean dashed;
	float length;
	float space;
	
	
	public StateManager(Mediator mediator) {
		this.mediator = mediator;
	}
	
	public void setState(State state) {
		if(this.state instanceof FreePolylineState) {
			FreePolylineState freePolylineState = (FreePolylineState) this.state;
			freePolylineState.freePolyline.setStartPoint(null);
			freePolylineState.freePolyline.setCurrentPoint(null);
			if(freePolylineState.freePolyline.vertices.size() == 1) {
				mediator.removeDrawing(freePolylineState.freePolyline);
			}
			mediator.canvas.repaint();
		}
		this.state = state;
	}
	
	public void setDashed(boolean dashed) {
		this.dashed = dashed;
	}
	
	public void setLength(float length) {
		this.length = length;
	}
	
	public void setSpace(float space) {
		this.space = space;
	}
	
	public boolean getDashed() {
		return dashed;
	}
	
	public float getLength() {
		return length;
	}
	
	public float getSpace(){
		return space;
	}

	public void mouseDown(int x, int y) {
		//現在のstateについて、対応するmouseDownを実行する
        state.mouseDown(x, y);
	}
	
	public void mouseUp(int x, int y) {
		state.mouseUp(x, y);
	}
	
	public void mouseDrag(int x, int y) {
		state.mouseDrag(x, y);
	}
}