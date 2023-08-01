import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyRectangle extends MyDrawing
{
	public MyRectangle(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}
	
	public void setRegion() {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		
		region = new Rectangle(x,y,w,h);
	}
	
	public boolean contains(int x, int y) {
		return region.contains(x,y);
	}

	
	public void draw(Graphics g) {
		super.draw(g);
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		int l = getLines();
		Color fc = getFillColor();
		Color lc = getLineColor();
		
		
		//高さや横幅が負のときのための処理
		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		if(getShadowed()) {
			g2.setColor(Color.black);
			g2.fillRect(x+3, y+3, w, h);
		}

		if(getDashed()) {
//			g2.setStroke(new MyDashStroke(getLineWidth(),getLength(), getSpace() ));
			g2.setStroke(new MyDashStroke(getLineWidth()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
			
		}
		
		Color fillColorWithAlpha = new Color(fc.getRed(), fc.getGreen(), fc.getBlue(), getFillAlpha());
		Color lineColorWithAlpha = new Color(lc.getRed(), lc.getGreen(), lc.getBlue(), getLineAlpha());
		
		g2.setColor(fillColorWithAlpha);
		g2.fillRect(x, y, w, h);
		g2.setColor(lineColorWithAlpha);
		if (l > 1) {
		    for (int i = 0; i < l; i++) {
		        int move = i * 2;
		        int shrink = i * 4;
		        g2.drawRect(x + move, y + move, w - shrink, h - shrink);
		    }
		} else {
		    g2.drawRect(x, y, w, h);
		}
	}
}