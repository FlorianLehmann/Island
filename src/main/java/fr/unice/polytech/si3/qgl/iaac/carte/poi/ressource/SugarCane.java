package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sebde on 11/12/2016.
 */
public class SugarCane extends Res {
    private static java.util.List<Point> sugarCane = new ArrayList();
    private int amount = 0;

    private static int[][] tab = new int[200][200];

    static {
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                tab[i][j] = 0;
            }
        }
    }

    public Point getTabMax() {
        int sum;
        int max = 0;
        Point point = new Point();
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                sum = 0;
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        sum += tab[i + k][j + l];
                    }
                }
                if (sum >= max) {
                    max = sum;
                    point = new Point(i - 100, j - 100);
                }

            }
        }

        return point;


    }

    public SugarCane() {
    }

    public boolean hasR() {
        return sugarCane.size() != 0;
    }

    static public void addSugarCane(Point point) {
        tab[(int) point.getX() + 100][(int) point.getY() + 100] = 1;
        sugarCane.add(new Point((int) point.getX(), (int) point.getY()));
    }

    @Override
    public Point getNearest(Point point) {
        double norme;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX() - sugarCane.get(0).getX()) * (point.getX() - sugarCane.get(0).getX())) + ((point.getY() - sugarCane.get(0).getY()) * (point.getY() - sugarCane.get(0).getY()))));
        for (int i = 0; i < sugarCane.size(); i++) {
            norme = Math.sqrt(Math.abs(((point.getX() - sugarCane.get(i).getX()) * (point.getX() - sugarCane.get(i).getX())) + ((point.getY() - sugarCane.get(i).getY()) * (point.getY() - sugarCane.get(i).getY()))));
            if (norme < Min) {
                Min = norme;
                index = i;
            }
        }
        return sugarCane.get(index);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
