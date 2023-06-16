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
	
	public MyTriangle(int xpt, int ypt, int wpt, int hpt, Color lc, Color fc) {
		super(xpt, ypt, wpt, hpt, lc, fc);
	}
	
	public void setRegion() {
	    int x = getX();
	    int y = getY();
	    int w = getW();
	    int h = getH();

	    int[] xpts = new int[numV];
	    int[] ypts = new int[numV];
	    for(int i = 0; i < numV; i++) {
	        double angle = 2 * Math.PI * i / numV;
	        xpts[i] = x + (int)(w * Math.cos(angle));
	        ypts[i] = y + (int)(h * Math.sin(angle));
	    }

	    int minX = Integer.MAX_VALUE;
	    int minY = Integer.MAX_VALUE;
	    int maxX = Integer.MIN_VALUE;
	    int maxY = Integer.MIN_VALUE;

	    for (int i = 0; i < numV; i++) {
	        if (xpts[i] < minX) minX = xpts[i];
	        if (ypts[i] < minY) minY = ypts[i];
	        if (xpts[i] > maxX) maxX = xpts[i];
	        if (ypts[i] > maxY) maxY = ypts[i];
	    }

	    region = new Rectangle(minX, minY, maxX - minX, maxY - minY);
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

	    int[] xpts = new int[numV];
	    int[] ypts = new int[numV];
	    
	    // マウスクリックした場所を頂点の一つとする
	    xpts[0] = x;
	    ypts[0] = y;

	    for (int i = 1; i < numV; i++) {
	        double angle = 2 * -Math.PI * i / numV;
	        xpts[i] = x + (int) (w * -Math.cos(angle));
	        ypts[i] = y + (int) (h * -Math.sin(angle));
	    }

	    Graphics2D g2 = (Graphics2D) g;
	    if (getShadowed()) {
	        int[] sxpts = new int[numV];
	        int[] sypts = new int[numV];
	        for (int j = 0; j < numV; j++) {
	            sxpts[j] = xpts[j] + 3;
	            sypts[j] = ypts[j] + 3;
	        }
	        g2.setColor(Color.black);
	        g2.fillPolygon(sxpts, sypts, numV);
	    }

	    if (getDashed()) {
	        g2.setStroke(new MyDashStroke(getLineWidth()));
	    } else {
	        g2.setStroke(new BasicStroke(getLineWidth()));
	    }
	    g2.setColor(getFillColor());
	    g2.fillPolygon(xpts, ypts, numV);
	    g2.setColor(getLineColor());
	    g2.drawPolygon(xpts, ypts, numV);
	}

}