package ar.edu.itba.model.utils;

import javax.persistence.Embeddable;

@Embeddable
public class Point {

    public static int manhattanSq(Point point1, Point point2){
        return (int) ((int) Math.pow((point1.x-point2.x),2) + Math.pow((point1.y-point2.y),2));
    }

    public Point(){}

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return "X: " + x + "\tY: " + y;
    }
}
