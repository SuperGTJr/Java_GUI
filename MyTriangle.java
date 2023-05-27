import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyTriangle extends MyDrawing
{
	public MyTriangle(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}
	
	public MyTriangle(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		super(xpt, ypt, wpt, hpt, lc, fc);
	}
	
	private int numV = 3;
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		int[] xpts = new int[numV];
		int[] ypts = new int[numV];
		for(int i=0; i<numV; i++) {
			double angle = 2 * Math.PI * i / numV;
			xpts[i] = x + (int)(w * Math.cos(angle));
			ypts[i] = y + (int)(h * Math.sin(angle));
		}
		
		Graphics2D g2 = (Graphics2D) g;
		if(getShadowed()) {
			int[] sxpts = new int[numV];
			int[] sypts = new int[numV];
			for(int j=0; j<numV; j++) {
				sxpts[j] = xpts[j] + 3;
				sypts[j] = ypts[j] + 3;
			}
			g2.setColor(Color.black);
			g2.fillPolygon(sxpts, sypts, numV);
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