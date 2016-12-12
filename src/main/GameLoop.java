package main;

import java.awt.Font;

import objects.Planet;
import objects.Sun;
import tools.Vector;

@SuppressWarnings("unused")
public class GameLoop implements Runnable {
	public static Window window;
	private Thread thread;
	private boolean running = false;
	public static int ticksPSec = 60;
	public static double secPDay = 1.0/20.0;
	public static float scale = 500000; // 1 pixel equals 500.000 km, earth is around 300 pixels away from sun

	public static void main(String[] args){
		GameLoop gameLoop = new GameLoop();
		gameLoop.start();
	}

	public void run(){
		init();
		double timePTick = 1000000000 / ticksPSec;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		//Loop
		//Dit hele system van delta enzo heb ik van een filmpje, het zorgt ervoor dat hij niet meer dan 60 keer per sec rekent
		//Bij draw heb ik dat bs gebeuren ook van dat filmpje, ik weet niet of je elke keer een nieuwe graphics moet creeeren
		//Ga ik zo eff uittesten of je die niet gewoon bij init kan zetten en dan draw() in loop zetten
		//Volgens mij was dat filmpje toen best wel up to date weet niet of dat nog steeds zo is
		//Het werkt tenminste heeeeel goed en hij heeft zelfs met deze grote berekeningen geen framerate lager dan 60 gehad
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/timePTick;
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1){
				tick();
				Drawing.render();
				ticks++;
				delta--;
			}
			//Framerate
			if (timer > 1000000000){
				System.out.println(ticks);
				Drawing.g.setFont(new Font("Arial", Font.BOLD, 14));
				Drawing.g2d.drawString("Framerate: " + String.valueOf(ticks), 100, 100); //Werkt niet geen enkel idee waarom
				timer = 0;
				ticks = 0;
			}
		}
		stop();
	}
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); //Starts the run function
	}
	public synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Setup all the things
	private void init(){
		Window.createWindow();
		Sun.theSun = new Sun();
		Sun.theSun.setMass((float)(1.989*Math.pow(10, 30)));
		Planet.earth = new Planet();
		Planet.earth.setMPlanet(Sun.theSun);
		Planet.earth.y = 149600000/scale; //km/(km/p)= p
		Planet.earth.x = 0;
		Planet.earth.setMass((float)(5.972*Math.pow(10, 24)));
		Planet.earth.setRadius(5);
		Planet.earth.setXVel(Planet.earth.y*2*Math.PI/365/secPDay/ticksPSec);
		Vector.setup();
	}
	//Update stuff here
	private static void tick(){
		Sun.tick();
		Planet.tick();
	}
}






