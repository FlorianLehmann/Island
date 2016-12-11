package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Flower {
    private static java.util.List<Point> flower;

    public Flower() {
        flower = new ArrayList();
    }

    static public void addFlower(Point point) {
        flower.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-flower.get(0).getX())*(point.getX()-flower.get(0).getX())) +  ((point.getY()-flower.get(0).getY())*(point.getY()-flower.get(0).getY()))));
        for (int i = 0; i < flower.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-flower.get(i).getX())*(point.getX()-flower.get(i).getX())) +  ((point.getY()-flower.get(i).getY())*(point.getY()-flower.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return flower.get(index);
    }
}