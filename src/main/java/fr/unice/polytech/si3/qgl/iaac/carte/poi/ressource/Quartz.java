package sample.bot.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Quartz extends Res{
    private static java.util.List<Point> quartz = new ArrayList();;
    private int amount = 0;

    private static int[][] tab = new int[200][200];

    static {
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                tab[i][j] = 0;
            }
        }
    }


    public static int[][] getTab() {
        return tab;
    }

    public Point getTabMax() {
        int sum = 0;
        int max = 0;
        Point point = new Point();
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                sum = 0;
                for (int k = 0; k < 5 ; k++) {
                    for (int l = 0; l < 5; l++) {
                        sum += tab[i+k][j+l];
                    }
                }
                if (sum >= max) {
                    max = sum;
                    point = new Point(i-100,j-100);//dépend du coté apr lequel on commence
                }

            }
        }

        return point;


    }

    public Quartz() {
    }
    public boolean hasR() {
        return quartz.size() != 0;
    }
    static public void addQuartz(Point point) {
        tab[(int) point.getX()+100][(int) point.getY()+100] = 1;
        quartz.add(new Point((int)point.getX(),(int)point.getY()));
    }
    
    @Override
    public Point getNearest(Point point) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
