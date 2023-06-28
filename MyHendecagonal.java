import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyHendecagonal extends MyDrawing
{
	
	private int numV =11;
	
	public MyHendecagonal(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}
	
	public MyHendecagonal(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		super(xpt, ypt, wpt, hpt, lc, fc);
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
	
	public void draw(Graphics g) {
		super.draw(g);
		int x = getX();
		int y = getY();
		int w = getW()/2;
		int h = getH()/2;
		
		int[] xpts = new int[numV];
		int[] ypts = new int[numV];
		int centerX = x + w;
		int centerY = y + h;
		
		for(int i=0; i<numV; i++) {
			double angle = 2 * Math.PI * i / numV;
			xpts[i] = centerX - (int)(w * Math.cos(angle));
			ypts[i] = centerY - (int)(h * Math.sin(angle));
		}
		
		
		Graphics2D g2 = (Graphics2D) g;
		if (getShadowed()) {
			if(getShadowed()) {
				int[] sxpts = new int[numV];
				int[] sypts = new int[numV];
				for(int i=0; i<numV; i++) {
					sxpts[i] = xpts[i] + 3;
					sypts[i] = ypts[i] + 3;
				}
				g2.setColor(Color.black);
				g2.fillPolygon(sxpts, sypts, numV);
			}
	    }
		
		if(getDashed()) {
			g2.setStroke(new MyDashStroke(getLineWidth()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
			
		}
		g2.setColor(getFillColor());
		g2.fillPolygon(xpts, ypts, numV);
		g2.setColor(getLineColor());
		g2.drawPolygon(xpts, ypts, numV);
	}
}