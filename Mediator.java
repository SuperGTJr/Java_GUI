import java.util.Enumeration;
import java.util.Vector;

public class Mediator
{
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	MyDrawing checkDrawing = null;
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingElements(){
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
		setSelectedDrawing(d);
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
	}
	
	public void setSelectedDrawing(MyDrawing checkDrawing) {
		this.checkDrawing = checkDrawing;
		System.out.println(checkDrawing);
	}
	
	public MyDrawing getSelectedDrawing() {
		return checkDrawing;
	}
	
	public void move(int dx, int dy) {
		if (checkDrawing != null) {
			checkDrawing.move(dx, dy);
			repaint();
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelected(int x, int y) {
//		boolean flag = false;
//		//for文で描画インスタンスが入っている配列をチェック
//		//もしインスタンスが(x,y)を含んでいればそれを選択
//		//選択したインスタンスについてメソッドを使えるようにする
//		for(Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
//			MyDrawing d = e.nextElement();
//			if(d.contains(x, y)) {
//				setSelectedDrawing(d);
//				d.setSelected(true);
//				flag = true;
//				System.out.println("true " + d);
//			}else {
//				//setSelectedDrawing(null);
//				d.setSelected(false);
//				System.out.println("false " + d);
//			}
//		}
//		if (!flag) {
//			setSelectedDrawing(null);
//		}
		MyDrawing check = null; // 最前面の選択図形

	    for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        if (d.contains(x, y)) {
	            if (check == null || drawings.indexOf(d) > drawings.indexOf(check)) {
	                check = d;
	            }
	        }
	    }

	    for (MyDrawing d : drawings) {
	        if (d == check) {
	            d.setSelected(true);
	            System.out.println("true " + d);
	        } else {
	            d.setSelected(false);
	            System.out.println("false " + d);
	        }
	    }

	    if (checkDrawing != null && checkDrawing != check) {
	        checkDrawing.setSelected(false);
	    }

	    setSelectedDrawing(check);
	}
}