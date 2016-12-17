package sample.bot.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fish extends Res{
    private static java.util.List<Point> fish = new ArrayList();
    private int amount = 0;

    
    public Fish() {
        
    }



    static public void addFish(Point point) {
        fish.add(new Point((int)point.getX(),(int)point.getY()));
    }
    
    public boolean hasR() {
        return fish.size() != 0;
    }
    public Point getNearest(Point point) {
        double norme = 0;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX()-fish.get(0).getX())*(point.getX()-fish.get(0).getX())) +  ((point.getY()-fish.get(0).getY())*(point.getY()-fish.get(0).getY()))));
        for (int i = 0; i < fish.size() ; i++) {
            norme = Math.sqrt(Math.abs(((point.getX()-fish.get(i).getX())*(point.getX()-fish.get(i).getX())) +  ((point.getY()-fish.get(i).getY())*(point.getY()-fish.get(i).getY()))));
            if (norme < Min ) {
                Min = norme;
                index = i;
            }
        }
        return fish.get(index);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
