public class SelectState extends State{
	StateManager stateManager;
	int x0;
	int y0;
	
	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		stateManager.mediator.setSelected(x, y);
		x0 = x;
		y0 = y;
	}
	
	public void mouseUp(int x, int y) {
	
	}
	
	public void mouseDrag(int x, int y) {
		// 前回の座標からの変位を計算：最初はdx=dy=0になる
		int dx = x - x0; 
        int dy = y - y0;

     // 図形の移動処理を呼び出す
        stateManager.mediator.move(dx, dy); 

     // 現在の座標を保存する
        x0 = x; 
        y0 = y;
		
	}
}