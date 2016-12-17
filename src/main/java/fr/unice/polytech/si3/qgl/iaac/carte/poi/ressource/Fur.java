package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fur extends Res{
    private static java.util.List<Point> fur = new ArrayList();;
    private int amount = 0;

    public Fur() {
    }
    public boolean hasR() {
        return fur.size() != 0;
    }
    static public void addFur(Point point) {
        fur.add(new Point((int)point.getX(),(int)point.getY()));
    }
    
     public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-fur.get(0).getX())*(point.getX()-fur.get(0).getX())) +  ((point.getY()-fur.get(0).getY())*(point.getY()-fur.get(0).getY()))));
        for (int i = 0; i < fur.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-fur.get(i).getX())*(point.getX()-fur.get(i).getX())) +  ((point.getY()-fur.get(i).getY())*(point.getY()-fur.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return fur.get(index);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
