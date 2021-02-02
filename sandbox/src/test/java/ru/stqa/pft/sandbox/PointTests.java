package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceOneLine(){
        Point p1 = new Point(-1, 3);
        Point p2 = new Point(-1, -4);
        assert p1.distance(p2) == 7.0;
    }

    @Test
    public void testDistanceDifLine(){
        Point p1 = new Point(-1, 1);
        Point p2 = new Point(3, 4);
        assert p1.distance(p2) == 5.0;
    }

    @Test
    public void testDistanceZero(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        assert p1.distance(p2) == 0.0;
    }


}
