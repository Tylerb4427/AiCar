/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author bovenzit2681
 */
public class aiGroup {

    private ArrayList<Car> group;
    private int numActive;


    public aiGroup(int num) {
        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> test2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
//            test.add(1);
//            test2.add(0);
        }
        group = new ArrayList<Car>();
        numActive = num;
        for (int i = 0; i < num; i++) {
            //group.add(new Car(100, 135, 0, 1, 1, Color.getHSBColor((float) i / num, 1f, .5f)));
            group.add(0,new Car(100, 135, 30, 1, 1, Color.getHSBColor((float) i / num, 1f, .5f),test2,test));
            group.get(i).setSpeed(1);
        }
    }

    public void drawAll(Graphics window) {
        for (Car i : group) {
            i.draw(window);
        }
    }

    public void moveAll() {
        for (Car i : group) {
            if (i.getActive()) {
                i.drive();
                i.move();
            }
        }
        System.out.println(numActive);
    }

    public void sort() {
        {
            int n = group.size();
            Car temp;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < (n - i); j++) {
                    if (group.get(j - 1).getScore() > group.get(j).getScore()) {
                        temp = group.get(j-1);
                        group.set(j-1,group.get(j));
                        group.set(j,temp);
                    }

                }
            }

        }

    }

    public void checkCollision() {
        sort();
        for (int a = group.size() - 1; a >= 0; a--) {
            Car i = group.get(a);
            if (group.get(a).getActive()) {
                for (Cone o : Wall.getList()) {
                    if (i.getX() < o.getX() && i.getX() + i.getWidth() > o.getX() + 1) {
                        if (i.getY() < o.getY() && i.getY() + i.getHeight() > o.getY() + 1) {
                            //group.remove(a);
                            if (i.getActive()) {
                                numActive--;
                            }
                            group.get(a).setActive(false);
                            group.remove(a);
                            int place=group.size()-1;
                            //int place = Util.round(Math.random() * (group.size() - 1));
                            group.add(new Car(100, 135, 30, 1, 1, Color.getHSBColor((float) 100, 1f, .5f), Util.subArray(group.get(place).turns, 30), Util.subArray(group.get(place).gas, 30)));
                        }
                    }
                }
            }
        }
    }
}
