package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Quartz extends Res{
    private static java.util.List<Point> quartz;

    public Quartz() {
        quartz = new ArrayList();
    }
    public boolean hasR() {
        return quartz.size() != 0;
    }
    static public void addQuartz(Point point) {
        quartz.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-quartz.get(0).getX())*(point.getX()-quartz.get(0).getX())) +  ((point.getY()-quartz.get(0).getY())*(point.getY()-quartz.get(0).getY()))));
        for (int i = 0; i < quartz.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-quartz.get(i).getX())*(point.getX()-quartz.get(i).getX())) +  ((point.getY()-quartz.get(i).getY())*(point.getY()-quartz.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return quartz.get(index);
    }

}
