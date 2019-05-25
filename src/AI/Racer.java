package AI;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Racer extends Canvas implements KeyListener, Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Car car;
    private aiGroup ai;
    /* uncomment once you are ready for this part
     *
     private AlienHorde horde;
     private Bullets shots;
     */
    private boolean[] keys;
    private BufferedImage back;
    private Wall wall;
    public Racer() {
        wall = new Wall();
        ai = new aiGroup(5000);
        car = new Car(100, 100, 1, 1, 1, Color.white);
        setBackground(Color.black);

        keys = new boolean[5];

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.black);
        graphToBack.fillRect(0,0,WIDTH,HEIGHT);
        car.draw(graphToBack);          
        car.move();
        graphToBack.setColor(Color.white);
        car.draw(graphToBack);
        ai.checkCollision();
        ai.moveAll();
        ai.drawAll(graphToBack);
        wall.drawAll(graphToBack);
        
        if (keys[0] == true) {
            car.setTheta(car.getTheta()+1);
            car.setSpeed(car.getSpeed()*.995);
        }
        if (keys[1] == true) {
            car.setTheta(car.getTheta()-1);
            car.setSpeed(car.getSpeed()*.995);
        }
        if (keys[2] == true) {
            if(car.getSpeed()<3)
            car.setSpeed(car.getSpeed()+.05);
        }
        if (keys[3] == true) {
            if(car.getSpeed()>-2)
            car.setSpeed(car.getSpeed()-.03);
        }
        if (keys[4] == true) {
            car.setSpeed(car.getSpeed()*.9);
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(1);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
