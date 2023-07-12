import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable
{
	int x, y, w, h; //X座標 Y座標 幅 高さ
	private Color lineColor, fillColor; //線の色 塗りの色
	private int lineWidth; //線の太さ
	private boolean isDashed = false; //線の種類
	private boolean isShadowed = false; //ドロップシャドウ
	private float length, space; //破線のパターン
	private int lines; //線の数
	private boolean isSelected = false; //選択状態
	Shape region;	//包含判定用
	final int SIZE = 7;
	
	
	public MyDrawing(int x, int y, int w, int h) {
		setLocation(x, y);
		setSize(w, h);
		setColor(Color.black,Color.white);
	}
	
	public MyDrawing(int x, int y, int w, int h, Color lc, Color fc) {
		this(x, y, w, h);
		setColor(lc, fc);
	}
	
	public void draw(Graphics g) {
		//選択状態を表す四角形を描く
		if(isSelected) {
			g.setColor(Color.black);
			g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
		}
		
	}
	
	@Override
	public MyDrawing clone() {
		try {
            MyDrawing clone = (MyDrawing) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
	
	public void move(int dx, int dy) {
		//オブジェクトを移動する処理を書く
		setLocation(x+dx, y+dy);
	}
	
	public void setLocation(int x, int y) {
		//オブジェクトの位置を変更する処理を書く
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		//オブジェクトのサイズを変更する処理を書く
		this.w = w;
		this.h = h;
	}
	
	public void setColor(Color lineColor, Color fillColor) {
		//オブジェクトの線・塗りの色を変更する処理を書く
		this.lineColor = lineColor;
		this.fillColor = fillColor;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setLineWidth(int lineWidth) {
		//オブジェクトの線の太さを変更する処理を書く
		this.lineWidth = lineWidth;
	}
	
	public void setDashed(boolean b) {
		//オブジェクトの線の種類を変更する処理を書く
		isDashed = b;
	}
	
	public void setShadowed(boolean s) {
		//ドロップシャドウの設定
		isShadowed = s;
	}
	
	public void setLength(float length) {
		this.length = length;
	}

	public void setSpace(float space) {
		this.space = space;
	}
	
	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setRegion() {
		//MyDrawingを継承する子クラス内でそれぞれ定義する
		//包含判定が矩形ならば、たとえば
//		region = new Rectangle(x,y,w,h);
	}
	
	public int getX() {
		//オブジェクトのXの位置を取得する処理を書く
		return x;
	}
	
	public int getY() {
		//オブジェクトのY位置を取得する処理を書く
		return y;
	}
	
	public int getW() {
		//オブジェクトの幅を取得する処理を書く
		return w;
	}
	
	public int getH() {
		//オブジェクトの高さを取得する処理を書く
		return h;
	}
	
	public Color getLineColor(){
		//オブジェクトの線の色を取得する処理を書く
		return lineColor;
	}
	
	public Color getFillColor() {
		//オブジェクトの塗りの色を取得する処理を書く
		return fillColor;
	}
	
	public int getLineWidth() {
		//オブジェクトの線の太さを取得する処理を書く
		return lineWidth;
	}
	
	public boolean getDashed() {
		//オブジェクトの線の種類を取得する処理を書く
		return isDashed;
	}
	
	public boolean getShadowed() {
		//ドロップシャドウを行うか否か
		return isShadowed;
	}
	
	public float getLength() {
		return length;
	}

	public float getSpace(){
		return space;
	}
	
	public int getLines() {
		return lines;
	}
	
	public boolean getSelected() {
		return isSelected;
	}
	
	public boolean contains(int x, int y) {
		//MyDrawingを継承する子クラス内でそれぞれ定義する
		//包含判定が矩形ならば、たとえば
		return region.contains(x,y);
	}
}




