import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyRectangle extends MyDrawing
{
	public MyRectangle(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}
	
	public MyRectangle(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		super(xpt, ypt, wpt, hpt, lc, fc);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		int l = getLines();
		
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
		
		
		g2.setColor(getFillColor());
		g2.fillRect(x, y, w, h);
		g2.setColor(getLineColor());
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