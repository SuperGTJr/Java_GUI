import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class MyFreePolyline extends MyDrawing
{
	List<Point> vertices;
	private Point startPoint; 
	private Point currentPoint;
	
	public MyFreePolyline() {
		super(0,0,0,0);
		vertices = new ArrayList<>();
	}
	
	public void addVertex(Point vertex) {
		vertices.add(vertex);
		update();
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	public Point getCurrentPoint() {
		return currentPoint;
	}
	
	public void update() {
		int minX = Integer.MAX_VALUE;
	    int minY = Integer.MAX_VALUE;
	    int maxX = Integer.MIN_VALUE;
	    int maxY = Integer.MIN_VALUE;
		
		for(Point vertex : vertices) {
			int x = vertex.x;
			int y = vertex.y;
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
		}
		
		int w = maxX - minX;
		int h = maxY - minY;
		
		setLocation(minX, minY);
		setSize(w, h);
		
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
	
	public void move(int dx, int dy) {
		int minX = Integer.MAX_VALUE;
	    int minY = Integer.MAX_VALUE;

		for(Point vertex : vertices) {
			vertex.x += dx;
			vertex.y += dy;
			minX = Math.min(minX, vertex.x);
			minY = Math.min(minY, vertex.y);
		}
		
		setLocation(minX, minY);
		setSize(w, h);
		
		
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		int l = getLines();
		Color fc = getFillColor();
		Color lc = getLineColor();
		int size = vertices.size();
		
		int[] xpts = new int[size];
		int[] ypts = new int[size];
		
		for (int i = 0; i < size; i++) {
            xpts[i] = vertices.get(i).x;
            ypts[i] = vertices.get(i).y;
        }
		
		Graphics2D g2 = (Graphics2D) g;
		if(getShadowed()) {
			int[] sxpts = new int[size];
			int[] sypts = new int[size];
			for(int i = 0; i < size; i++) {
				sxpts[i] = xpts[i] + 3;
				sypts[i] = ypts[i] + 3;
			}
			g2.setColor(Color.black);
			g2.fillPolygon(sxpts, sypts, size);
		}
		
		if(getDashed()) {
			g2.setStroke(new MyDashStroke(getLineWidth()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
			
		}
		
		Color fillColorWithAlpha = new Color(fc.getRed(), fc.getGreen(), fc.getBlue(), getFillAlpha());
		Color lineColorWithAlpha = new Color(lc.getRed(), lc.getGreen(), lc.getBlue(), getLineAlpha());
		
		g2.setColor(fillColorWithAlpha);
		g2.fillPolygon(xpts, ypts, size);
		g2.setColor(lineColorWithAlpha);
//		if (l > 1) {
//		    for (int i = 0; i < l; i++) {
//		    	int move = i * 2;
//		        int shrink = i * 4;
//		        int[] lxpts = new int[size];
//				int[] lypts = new int[size];
//				for(int j = 0; j < size; j++) {
//					lxpts[j] = xpts[i] + move;
//					lypts[j] = ypts[i] + move;
//				}
//				g2.drawPolyline(lxpts, lypts, size);
//		    }
//		} else {
//			g2.drawPolyline(xpts, ypts, size);
//		}
		g2.drawPolyline(xpts, ypts, size);
		
		if(startPoint != null) {
			int sX = startPoint.x - 4;
			int sY = startPoint.y -4;
			g2.setColor(new Color(51, 102, 255));
			g2.fillOval(sX, sY, 8, 8);
		}
		
		if(currentPoint != null) {
			int sX = currentPoint.x - 4;
			int sY = currentPoint.y -4;
			g2.setColor(new Color(51, 102, 255));
			g2.fillOval(sX, sY, 8, 8);
		}
	}
}