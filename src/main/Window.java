package main;

import java.awt.Canvas;
import java.awt.Frame;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class Window {
	private static Canvas canvas;
	public static JFrame frame;
	public static int width;
	public static int height;

	public static void createWindow() {
		frame = new JFrame();
		frame.setVisible(true);
		/*frame.setExtendedState(Frame.MAXIMIZED_BOTH);*/				//Max size frame
		frame.setSize(1300,800);										//Set specific boundaries
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//New canvas to draw on
		canvas = new Canvas();
		canvas.setPreferredSize(frame.getContentPane().getSize());
		frame.add(canvas);
		width = frame.getWidth();
		height = frame.getHeight();
	}

	public static Canvas getCanvas(){
		return canvas;
	}
}