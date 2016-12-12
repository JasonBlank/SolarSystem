package tools;
//Vector objects to calculate things easier, cos and sin aren't used
public class Vector {
	private double x=0;
	private double y=0;
	static public Vector XAxis = new Vector();
	static public Vector YAxis = new Vector();
		
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public static void setup() {
		XAxis.x = 1;
		YAxis.y = 1;
	}
	public float getLength(){
		return (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}
	//Actually not used anywhere
	public float getAngle(){
		float angle;
		if (this.x>=0){
			if (this.y>=0){
				angle = (float) (Math.atan(this.y/this.x));
			}else{
				angle = (float) (2*Math.PI - Math.atan(-this.y/this.x));
			}
		}else{
			if (this.y>=0){
				angle = (float) (Math.PI - Math.atan(-this.y/this.x));
			}else{
				angle = (float) (Math.PI + Math.atan(this.y/this.x));
			}
		}
		return angle;
		/*Returns angle of vector and x-axis counterclockwise: 
		 * not clockwise because of the inverted y-axis*/
	}
	public void multiply(float k){
		this.x *= k;
		this.y *= k;
	}
	static public double dotProduct(Vector u, Vector v) {
		return u.x * v.x + u.y * v.y;
	}
	public double LenghtPerpenProj(Vector vectorOnWhichProjected){
		return dotProduct(this, vectorOnWhichProjected)/(vectorOnWhichProjected.getLength());
	}
}