package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Ore extends Res{
    private static java.util.List<Point> ore;

    public Ore() {
        ore = new ArrayList();
    }

    static public void addOre(Point point) {
        ore.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-ore.get(0).getX())*(point.getX()-ore.get(0).getX())) +  ((point.getY()-ore.get(0).getY())*(point.getY()-ore.get(0).getY()))));
        for (int i = 0; i < ore.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-ore.get(i).getX())*(point.getX()-ore.get(i).getX())) +  ((point.getY()-ore.get(i).getY())*(point.getY()-ore.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return ore.get(index);
    }
}
