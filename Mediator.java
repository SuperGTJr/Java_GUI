import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator
{
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawing = null;
	Vector<MyDrawing> buffer = null;
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		canvas.requestFocusInWindow();
		drawings = new Vector<MyDrawing>();
		selectedDrawing = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingElements(){
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
//		setSelectedDrawing(d);
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
	}
	
	public void setSelectedDrawing(Vector<MyDrawing> selectedDrawing) {
		this.selectedDrawing = selectedDrawing;
//		System.out.println(selectedDrawing);
	}
	
	public void resetSelectedDrawing() {
		setSelectedDrawing(null);
		selectedDrawing = new Vector<MyDrawing>();
	}
	
	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawing;
	}
	
	public void move(int dx, int dy) {
		if (selectedDrawing != null) {
			for(Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
				MyDrawing d = e.nextElement();
				d.move(dx, dy);
//				System.out.println(d.getSelected());
				d.setRegion();
			}		
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	//20230712：最前面図形の特定をメソッドとして分離
	public MyDrawing getFront(int x, int y) {
		MyDrawing front = null;
		for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        //いったん全部選択解除
	        d.setSelected(false);
	        if (d.contains(x, y)) {
	            if (front == null || drawings.indexOf(d) > drawings.indexOf(front)) {
	                front = d;
	            }
	        }
	    }
		
		return front;
	}
	
	public void setSelected(int x, int y) {
		resetSelectedDrawing();
		// 最前面の選択図形
		MyDrawing front = null;
		
		front = getFront(x, y);
	    
	    if(front!=null) {
		    front.setSelected(true);
		    selectedDrawing.add(front);
	    }
	    canvas.requestFocusInWindow();
	}
	
	public boolean isWithinArea(MyDrawing d, MyRectangle area) {
		int dX = d.getX();
		int dY = d.getY();
		int dW = d.getW();
		int dH = d.getH();
		
		int aX = area.getX();
		int aY = area.getY();
		int aW = area.getW();
		int aH = area.getH();
		
		if(dX > aX+aW) {
			return false;
		}
		if(dX+dW < aX) {
			return false;
		}
		if(dY > aY+aH) {
			return false;
		}
		if(dY+dH < aY) {
			return false;
		}
		
		return true;
	}
		
	
	public void setSelectedByArea(MyRectangle area) {
		resetSelectedDrawing();
		
		for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        
	        if(d!=area && isWithinArea(d, area)) {
	        	d.setSelected(true);
	        	selectedDrawing.add(d);
	        }
		}
		canvas.requestFocusInWindow();
	}
	
	//属性変更
	public void setShadowed(boolean shadowed) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setShadowed(shadowed);
		}
	}
	
	public void setDashed(boolean dashed) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setDashed(dashed);
		}
	}
	
	public void setLineColor(Color color) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLineColor(color);
		}
	}
	
	public void setFillColor(Color color) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setFillColor(color);
		}
	}
	
	public void setLineWidth(int lineWidth) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLineWidth(lineWidth);
		}
	}
	
	public void setLines(int lines) {
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLines(lines);
		}
	}
	
	public void removeSelectedDrawing() {
		if(selectedDrawing != null) {
			for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
		        MyDrawing d = e.nextElement();
		        removeDrawing(d);
				repaint();
			}
			resetSelectedDrawing();
		}
	}
	
	public void clearBuffer() {
		buffer = null;
		buffer = new Vector<MyDrawing>();
	}
	
	public void copy() {
		clearBuffer();
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        buffer.add(d);
		}
	}
	
	public void cut() {
		clearBuffer();
		for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        buffer.add(d);
	        removeDrawing(d);
		}
		repaint();
	}
	
	public void paste() {
		if(selectedDrawing!=null) {
			for (Enumeration<MyDrawing> e = selectedDrawing.elements(); e.hasMoreElements();) {
				MyDrawing d = e.nextElement();
				d.setSelected(false);
			}
		}
		resetSelectedDrawing();
		
		for (Enumeration<MyDrawing> e = buffer.elements(); e.hasMoreElements();) {
	        MyDrawing clone =(MyDrawing)e.nextElement().clone();
			clone.setLocation(clone.getX()+3, clone.getY()+3);
			addDrawing(clone);
			selectedDrawing.add(clone);
			clone.setSelected(true);
		}
		repaint();
		
	}
	
	public void loadData() {
        // File入力
        try {
            FileInputStream fin = new FileInputStream("file.txt");
            ObjectInputStream in = new ObjectInputStream(fin);

            drawings = (Vector<MyDrawing>)in.readObject();
            fin.close();
        } catch (Exception ex) {
        }
	}
	
	public void saveData() {
		try {
            FileOutputStream fout = new FileOutputStream("file.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(drawings);
            out.flush();

            fout.close();
        } catch (Exception ex) {
        }
	}
	
	
}