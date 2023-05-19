import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyHendecagonal extends MyDrawing
{
	//w=hのとき正十一角形となるように変更
	public MyHendecagonal(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}
	
	public MyHendecagonal(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		super(xpt, ypt, wpt, hpt, lc, fc);
	}
	
	public MyHendecagonal(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc, boolean b) {
		super(xpt, ypt, wpt, hpt, lc, fc, b);
	}
	
	private int numV = 11;
	
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
		
		int[] xpts = new int[numV];
		int[] ypts = new int[numV];
		for(int i=0; i<numV; i++) {
			double angle = 2 * Math.PI * i / numV;
			xpts[i] = x + (int)(w * Math.cos(angle));
			ypts[i] = y + (int)(h * Math.sin(angle));
		}
		
		
		Graphics2D g2 = (Graphics2D) g;
		if(getDashed()) {
			g2.setStroke(new MyDashStroke(getLineWidth()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
			
		}
		g2.setColor(getFillColor());
		g2.fillPolygon(xpts, ypts, 11);
		g2.setColor(getLineColor());
		g2.drawPolygon(xpts, ypts, 11);
	}
}