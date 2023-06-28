import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator
{
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	MyDrawing selectedDrawing = null;
	MyDrawing buffer = null;
	
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
	
	public void setSelectedDrawing(MyDrawing selectedDrawing) {
		this.selectedDrawing = selectedDrawing;
		canvas.requestFocusInWindow();
//		System.out.println(selectedDrawing);
	}
	
	public MyDrawing getSelectedDrawing() {
		return selectedDrawing;
	}
	
	public void move(int dx, int dy) {
		if (selectedDrawing != null) {
			selectedDrawing.move(dx, dy);
			selectedDrawing.setRegion();	
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelected(int x, int y) {
		// 最前面の選択図形
		MyDrawing check = null; 

	    for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        if (d.contains(x, y)) {
	            if (check == null || drawings.indexOf(d) > drawings.indexOf(check)) {
	                check = d;
	            }
	        }
	    }

	    for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	    	MyDrawing d = e.nextElement();
	        if (d == check) {
	            d.setSelected(true);
//	            System.out.println("true " + d);
	        } else {
	            d.setSelected(false);
//	            System.out.println("false " + d);
	        }
	    }

	    if (selectedDrawing != null && selectedDrawing != check) {
	        selectedDrawing.setSelected(false);
	    }

	    setSelectedDrawing(check);
	}
	
	
	//属性変更
	public void setShadowed(boolean shadowed) {
		selectedDrawing.setShadowed(shadowed);
	}
	
	public void setDashed(boolean dashed) {
		selectedDrawing.setDashed(dashed);
	}
	
	public void setLineColor(Color color) {
		selectedDrawing.setLineColor(color);
	}
	
	public void setFillColor(Color color) {
		selectedDrawing.setFillColor(color);
	}
	
	public void setLineWidth(int lineWidth) {
		selectedDrawing.setLineWidth(lineWidth);
	}
	
	public void setLines(int lines) {
		selectedDrawing.setLines(lines);
		repaint();
	}
	
	public void removeSelectedDrawing() {
		if(selectedDrawing != null) {
			removeDrawing(selectedDrawing);
			selectedDrawing = null;
			repaint();
		}
	}
	
	public void clearBuffer() {
		buffer = null;
	}
	
	public void copy() {
		clearBuffer();
		buffer = selectedDrawing.clone();
	}
	
	public void cut() {
		clearBuffer();
		
		buffer = selectedDrawing.clone();
		removeDrawing(selectedDrawing);
		repaint();
	}
	
	public void paste() {
		selectedDrawing.setSelected(false);
		MyDrawing clone =(MyDrawing)buffer.clone();
		clone.setLocation(clone.getX()+3, clone.getY()+3);
		addDrawing(clone);
		repaint();
	}
	
}