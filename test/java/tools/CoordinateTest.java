package tools;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CoordinateTest {
    private List<Coordinate> coordinates = new LinkedList<>();

    @Before
    public void setUp(){
        coordinates.add(new Coordinate(150,0, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(150,25, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(125,50, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(125,75, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(150,100, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(150,125, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(125,125, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(100,100, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(50,100, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(25,125, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(0,125, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(0,125, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(0,100, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(25,75, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(25,50, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(0,25, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(0,0, Coordinate.Type.PIXELBASED));
        coordinates.add(new Coordinate(150,0, Coordinate.Type.PIXELBASED));

    }

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    @Test
    public void test() {
        coordinates.sort((o1, o2) -> {
            if(o1.getY()< o2.getY()){
                return -1;
            }else if(o1.getY() == o2.getY()) {
                if(o1.getY()==0) {
                    if (o1.getX() < o2.getX()) {
                        return -1;
                    } else if (o1.getX() == o2.getX()) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }else {
                    if (o1.getX() < o2.getX()) {
                        return 1;
                    } else if (o1.getX() == o2.getX()) {
                        return 0;
                    }
                    else {
                        return -1;
                    }
                }
            }else {
                return 1;
            }
        });

        for (Coordinate coordinate : coordinates) {
            System.out.println(coordinate.getX() + " " + coordinate.getY());
        }
    }

}