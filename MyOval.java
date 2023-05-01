import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyOval extends MyDrawing
{
	public MyOval(int xpt, int ypt, int wpt, int hpt) {
		super();
		setLocation(xpt, ypt);
		setSize(wpt, hpt);
	}
	
	public MyOval(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		this(xpt, ypt, wpt, hpt);
		setColor(lc, fc);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
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
		g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
	}
}