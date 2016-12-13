package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class SugarCane extends Res{
    private static java.util.List<Point> sugarCane;

    public SugarCane() {
        sugarCane = new ArrayList();
    }
    public boolean hasR() {
        return sugarCane.size() != 0;
    }
    static public void addSugarCane(Point point) {
        sugarCane.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-sugarCane.get(0).getX())*(point.getX()-sugarCane.get(0).getX())) +  ((point.getY()-sugarCane.get(0).getY())*(point.getY()-sugarCane.get(0).getY()))));
        for (int i = 0; i < sugarCane.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-sugarCane.get(i).getX())*(point.getX()-sugarCane.get(i).getX())) +  ((point.getY()-sugarCane.get(i).getY())*(point.getY()-sugarCane.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return sugarCane.get(index);
    }
}
