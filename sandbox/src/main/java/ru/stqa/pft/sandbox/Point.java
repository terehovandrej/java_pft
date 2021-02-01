package ru.stqa.pft.sandbox;

public class Point {
    public double a;
    public double b;

    public Point(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p1.a - p2.a), 2) + Math.pow((p1.b - p2.b), 2));
    }
}
