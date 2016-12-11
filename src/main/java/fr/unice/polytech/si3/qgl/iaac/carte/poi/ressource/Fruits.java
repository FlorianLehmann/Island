package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fruits extends Res{
    private static java.util.List<Point> fruits;

    public Fruits() {
        fruits = new ArrayList();
    }

    static public void addFruits(Point point) {
        fruits.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-fruits.get(0).getX())*(point.getX()-fruits.get(0).getX())) +  ((point.getY()-fruits.get(0).getY())*(point.getY()-fruits.get(0).getY()))));
        for (int i = 0; i < fruits.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-fruits.get(i).getX())*(point.getX()-fruits.get(i).getX())) +  ((point.getY()-fruits.get(i).getY())*(point.getY()-fruits.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return fruits.get(index);
    }
}
