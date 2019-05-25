/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author bovenzit2681
 */
public class Cone {

    int x;
    int y;

    public Cone(int x, int y) {
        this.x=x;
        this.y=y;   
    }
    
    public void draw(Graphics window){
        window.setColor(Color.white);
        window.drawRect(x,y,1,1);
    }
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
