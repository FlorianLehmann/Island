package sample.bot.carte.poi.ressource;

import java.awt.*;
import java.util.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fur extends Res{
    private static java.util.List<Point> fur = new ArrayList();;
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

    public Fur() {
    }
    public boolean hasR() {
        return fur.size() != 0;
    }
    static public void addFur(Point point) {
        tab[(int) point.getX()+100][(int) point.getY()+100] = 1;
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
