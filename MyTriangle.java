import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyTriangle extends MyDrawing
{
	private int numV = 3;
	public MyTriangle(int xpt, int ypt, int wpt, int hpt) {
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
		int w = getW()/2;
		int h = getH()/2;
		int l = getLines();
		Color fc = getFillColor();
		Color lc = getLineColor();
		
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
		
		Color fillColorWithAlpha = new Color(fc.getRed(), fc.getGreen(), fc.getBlue(), getFillAlpha());
		Color lineColorWithAlpha = new Color(lc.getRed(), lc.getGreen(), lc.getBlue(), getLineAlpha());
		
		g2.setColor(fillColorWithAlpha);
		g2.fillPolygon(xpts, ypts, numV);
		g2.setColor(lineColorWithAlpha);
		if (l > 1) {
		    for (int i = 0; i < l; i++) {
		        int shrink = i * 4;
		        int[] lxpts = new int[numV];
				int[] lypts = new int[numV];
				for(int j=0; j<numV; j++) {
					double angle = 2 * Math.PI * j / numV;
					lxpts[j] = centerX - (int)((w-shrink) * Math.cos(angle));
					lypts[j] = centerY - (int)((h-shrink) * Math.sin(angle));
				}
				g2.drawPolygon(lxpts, lypts, numV);
		    }
		} else {
			g2.drawPolygon(xpts, ypts, numV);
		}
	}
}