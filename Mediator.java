import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Mediator
{
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = null;
	Vector<MyDrawing> buffer = null;
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		canvas.requestFocusInWindow();
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
		canvas.requestFocusInWindow();
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
	
	public void setSelectedDrawings(Vector<MyDrawing> selectedDrawings) {
		this.selectedDrawings = selectedDrawings;
	}
	
	public void resetSelectedDrawings() {
		setSelectedDrawings(null);
		selectedDrawings = new Vector<MyDrawing>();
	}
	
	public Vector<MyDrawing> getSelectedDrawings() {
		return selectedDrawings;
	}
	
	public void move(int dx, int dy) {
		if (selectedDrawings != null) {
			for(Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
				MyDrawing d = e.nextElement();
				d.move(dx, dy);
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
		resetSelectedDrawings();
		// 最前面の選択図形
		MyDrawing front = null;
		
		front = getFront(x, y);
	    
	    if(front!=null) {
		    front.setSelected(true);
		    selectedDrawings.add(front);
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
		resetSelectedDrawings();
		
		for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        
	        if(d!=area && isWithinArea(d, area)) {
	        	d.setSelected(true);
	        	selectedDrawings.add(d);
	        }
		}
		canvas.requestFocusInWindow();
	}
	
	//属性変更
	public void setShadowed(boolean shadowed) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setShadowed(shadowed);
		}
	}
	
	public void setDashed(boolean dashed) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setDashed(dashed);
		}
	}
	
	public void setFillColor(Color color) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setFillColor(color);
		}
	}
	
	public void setLineColor(Color color) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLineColor(color);
		}
	}

	public void setFillAlpha(int alpha) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setFillAlpha(alpha);
		}
	}
	
	public void setLineAlpha(int alpha) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLineAlpha(alpha);
		}
	}
	
	public void setLineWidth(int lineWidth) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLineWidth(lineWidth);
		}
	}
	
	public void setLines(int lines) {
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        d.setLines(lines);
		}
	}
	
	public void removeSelectedDrawing() {
		if(selectedDrawings != null) {
			for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
		        MyDrawing d = e.nextElement();
		        removeDrawing(d);
			}
			repaint();
			resetSelectedDrawings();
		}
	}
	
	public void clearBuffer() {
		buffer = null;
		buffer = new Vector<MyDrawing>();
	}
	
	public void copy() {
		clearBuffer();
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        buffer.add(d.clone());
		}
	}
	
	public void cut() {
		clearBuffer();
		for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
	        MyDrawing d = e.nextElement();
	        buffer.add(d.clone());
	        removeDrawing(d);
		}
		repaint();
	}
	
	public void paste() {
		if(selectedDrawings!=null) {
			for (Enumeration<MyDrawing> e = selectedDrawings.elements(); e.hasMoreElements();) {
				MyDrawing d = e.nextElement();
				d.setSelected(false);
			}
		}
		resetSelectedDrawings();
		
		for (Enumeration<MyDrawing> e = buffer.elements(); e.hasMoreElements();) {
	        MyDrawing clone =(MyDrawing)e.nextElement().clone();
			clone.setLocation(clone.getX()+3, clone.getY()+3);
			addDrawing(clone);
			selectedDrawings.add(clone);
			clone.setSelected(true);
		}
		repaint();
		
	}
	
	public void loadData() {
        // File入力
		JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);  

        if (returnVal == JFileChooser.APPROVE_OPTION) { 
            File file = fc.getSelectedFile();
            
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fin);

                drawings = (Vector<MyDrawing>)in.readObject();
                for (Enumeration<MyDrawing> e = drawingElements(); e.hasMoreElements();) {
        	        MyDrawing d = e.nextElement();
        	        d.setSelected(false);
                }
                
                fin.close();
            } catch (Exception ex) {
            }
 
        }

	}
	
	public void saveData() {
		JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);  

        if (returnVal == JFileChooser.APPROVE_OPTION) { 
            File file = fc.getSelectedFile();
            
        	try {
                FileOutputStream fout = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fout);

                out.writeObject(drawings);
                out.flush();

                fout.close();
            } catch (Exception ex) {
            }
        }
	}
	
	public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Only", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try {
                BufferedImage image = ImageIO.read(file);
                // キャンバスに追加
                MyImage myImage = new MyImage(50,50,image);
                addDrawing(myImage);
                myImage.setRegion();
            } catch (Exception ex) {
                // 画像の読み込みに失敗した場合
                ex.printStackTrace();
            }
        }
    }

}

		