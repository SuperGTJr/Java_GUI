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
		//現在のstateを更新する：引数にStateの子クラスRectStateを取った場合、四角形描画用のstateに更新される
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