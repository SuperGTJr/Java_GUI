import java.awt.Color;
import java.awt.Graphics;

public class MyDrawing
{
	private int x, y, w, h; //X座標 Y座標 幅 高さ
	private Color lineColor, fillColor; //線の色 塗りの色
	private int lineWidth; //線の太さ
	
	public MyDrawing() {
		 x = y = 0;
		 w = h = 40;
		 lineColor = Color.black;
		 fillColor = Color.white;
		 lineWidth = 1;
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void move(int dx, int dy) {
		//オブジェクトを移動する処理を書く
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
	
	public void setWidth(int lineWidth) {
		//オブジェクトの線の太さを変更する処理を書く
		this.lineWidth = lineWidth;
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
}