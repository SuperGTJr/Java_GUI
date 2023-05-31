import java.awt.BasicStroke;

public class MyDashStroke extends BasicStroke
{
	float length;
	float space;
	
	private static float pattern[] = {10, 15};

	public MyDashStroke(float lineWidth) {
		super(lineWidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern, 0);
	}
	
//	public MyDashStroke(float lineWidth, float length, float space) {
//		super(lineWidth, CAP_BUTT, JOIN_BEVEL, 1.0f, new float[] {length, space}, 0);
//		this.length = length;
//		this.space = space;
//	}
	
//	public void setLength(float length) {
//		this.length = length;
//	}
//	
//	public void setSpace(float space) {
//		this.space = space;
//	}
//	
//	public float getLength() {
//		return length;
//	}
//	
//	public float getSpace(){
//		return space;
//	}

	
}