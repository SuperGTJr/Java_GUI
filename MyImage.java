import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class MyImage extends MyDrawing {
    Image image;

    public MyImage(int x, int y, Image image) {
        super(x, y, 0, 0);
        this.image = image;

        setSize(image.getWidth(null), image.getHeight(null));
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
        
        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)getFillAlpha()/255);
        g2.setComposite(alphaComposite);
        
        g.drawImage(image, getX(), getY(), null);
    }
}
