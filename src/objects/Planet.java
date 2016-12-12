package objects;
import main.Drawing;
import main.GameLoop;
import tools.Vector;

public class Planet extends Sun{
	static Planet planets[];
	private Sun mPlanet;
	private Vector vToMPlanet = new Vector();
	private double xVelocity = 0;
	private double yVelocity = 0;
	private Moon moonsOfP[];
	public static Planet earth;

	//Getters and Setters
	public Sun getMPlanet() {
		return mPlanet;
	}
	public void setMPlanet(Sun mPlanet) {
		this.mPlanet = mPlanet;
	}
	public Vector getVToMPlanet() {
		return vToMPlanet;
	}
	public void setVToMPlanet(Vector vToMPlanet) {
		this.vToMPlanet = vToMPlanet;
	}
	public double getXVel() {
		return xVelocity;
	}
	public void setXVel(double xSpeed) {
		this.xVelocity = xSpeed;
	}
	public double getYVel() {
		return yVelocity;
	}
	public void setYVel(double ySpeed) {
		this.yVelocity = ySpeed;
	}
	public Moon[] getMoonsOfP() {
		return moonsOfP;
	}
	public void setMoonsOfP(Moon[] moonsOfP) {
		this.moonsOfP = moonsOfP;
	}
	//Updating with another function so later on its easier for the tick function to have a for array loop
	public static void tick(){
		earth.update();
	}

	void update(){
		double acceleration;
		float accelerationX;
		float accelerationY;
		earth.getVToMPlanet().setX(-earth.x*GameLoop.scale*1000); // P*(km/p)*m/km = m
		earth.getVToMPlanet().setY(-earth.y*GameLoop.scale*1000);
		acceleration = GConstant * theSun.getMass()/Math.pow(earth.getVToMPlanet().getLength(), 2); //N*m2/kg2*kg/m2 = N/kg = kg*m/s2/kg = m/s2
		accelerationX = (float) (earth.vToMPlanet.LenghtPerpenProj(Vector.XAxis)/earth.vToMPlanet.getLength()*acceleration);// m/s2
		accelerationY = (float) (earth.vToMPlanet.LenghtPerpenProj(Vector.YAxis)/earth.vToMPlanet.getLength()*acceleration);
		earth.xVelocity += (accelerationX/1000/GameLoop.scale/(GameLoop.ticksPSec*GameLoop.secPDay)/(GameLoop.ticksPSec*GameLoop.secPDay)*3600*3600*24*24);
		earth.yVelocity += (accelerationY/1000/GameLoop.scale/(GameLoop.ticksPSec*GameLoop.secPDay)/(GameLoop.ticksPSec*GameLoop.secPDay)*3600*3600*24*24);
		earth.x += earth.xVelocity;
		earth.y += earth.yVelocity;
	}
	public static void draw(){
		Drawing.g.drawOval((int) (theSun.x + earth.x-earth.getRadius()), (int) (theSun.y + earth.y-earth.getRadius()), earth.getRadius()*2, earth.getRadius()*2);
	}

}
