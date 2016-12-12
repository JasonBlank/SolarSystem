package objects;

import main.Drawing;
import main.GameLoop;
import main.Window;

public class Sun{
	public static Sun theSun;
	public static double GConstant = 6.67408 * Math.pow(10, -11);
	private float mass;
	private int radius;
	private float rotation = 0; //Radians
	private float rotationSpeed; //DaysPerRotation, 10 seconds = 1 day = 600 ticks
	private boolean clockWiseR;

	public double x = 0;		//Coordinates relative to mPlanet, suncoords get set in Sun()
	public double y = 0;
	
	//Getters and Setters
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public float getRotationSpeed() {
		return rotationSpeed;
	}
	public void setRotationSpeed(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	public boolean isClockWiseR() {
		return clockWiseR;
	}
	public void setClockWiseR(boolean clockWiseR) {
		this.clockWiseR = clockWiseR;
	}
	
	public Sun(){
		this.x = (int) Window.width/2;
		this.y = (int) Window.height/2;
	}
	//Get overridden in planet class
	public static void tick(){
		theSun.update();
	}
	//Rotation not implemented
	void update() {
		this.rotation =+(float) ( Math.PI*2/rotationSpeed/(GameLoop.secPDay*GameLoop.ticksPSec));
	}
	//Radius not implemented
	public static void draw() {
		Drawing.g2d.drawOval((int) theSun.x-20, (int) theSun.y-20, 40, 40);
	}
}