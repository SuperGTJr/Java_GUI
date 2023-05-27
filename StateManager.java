public class StateManager
{
	State state;
	MyCanvas canvas;
	boolean dashed;
	boolean shadowed;
	
	
	public StateManager(MyCanvas canvas) {
		this.canvas = canvas;
		
	}
	
	public void setState(State state) {
		//現在のstateを更新する：引数にStateの子クラスRectStateを取った場合、四角形描画用のstateに更新される
		this.state = state;
	}
	
	public void setDashed(boolean dashed) {
		this.dashed = dashed;
	}
	
	public boolean getDashed() {
		return dashed;
	}
	
	public void setShadowed(boolean shadowed) {
		this.shadowed = shadowed;
	}
	
	public boolean getShadowed(){
		return shadowed;
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