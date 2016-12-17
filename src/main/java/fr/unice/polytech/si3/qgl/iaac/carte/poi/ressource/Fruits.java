package sample.bot.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fruits extends Res{
    private static java.util.List<Point> fruits = new ArrayList();
    private int amount = 0;


    public Fruits() {
    }
    public boolean hasR() {
        return fruits.size() != 0;
    }
    static public void addFruits(Point point) {
        fruits.add(new Point((int)point.getX(),(int)point.getY()));
    }
    
     public Point getNearest(Point point) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
