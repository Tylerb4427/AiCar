package AI;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import javax.swing.JFrame;
import java.awt.Component;

public class Runner extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public Runner()
	{
		super("Racing");
		setSize(WIDTH,HEIGHT);

		Racer theGame = new Racer();
		((Component)theGame).setFocusable(true);

		getContentPane().add(theGame);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main( String args[] )
	{
		Runner run = new Runner();
	}
}