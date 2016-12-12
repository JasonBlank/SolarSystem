package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import objects.Planet;
import objects.Sun;

@SuppressWarnings("unused")
public class Drawing {
	private static BufferStrategy bs;
	public static Graphics g;
	public static Graphics2D g2d;

	public static void render(){
		//creating a bufferstrat to be able to get Graphics to draw with
		if (bs == null){
			bs = Window.getCanvas().getBufferStrategy();
			if (bs == null){
				Window.getCanvas().createBufferStrategy(2);
				return;
			}
		}
		g = bs.getDrawGraphics();
		g2d = (Graphics2D) g;
		//g.clearRect(0, 0, Window.width, Window.height);
		draw();
		bs.show();
		g.dispose();
	}
	//Draw all the things
	private static void draw() {
		Sun.draw();
		Planet.draw();
	}
	//Ook van dat filmpje volgens mij kon je hiermee afbeeldingen laden
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Drawing.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}