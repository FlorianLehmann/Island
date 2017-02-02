package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sebde on 11/12/2016.
 */
public class Fruits extends Res {
    private static java.util.List<Point> fruits = new ArrayList();
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

    public Fruits() {
    }

    public boolean hasR() {
        return fruits.size() != 0;
    }

    public static void addFruits(Point point) {
        tab[(int) point.getX() + 100][(int) point.getY() + 100] = 1;
        fruits.add(new Point((int) point.getX(), (int) point.getY()));
    }

    public Point getNearest(Point point) {
        double norme;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX() - fruits.get(0).getX()) * (point.getX() - fruits.get(0).getX())) + ((point.getY() - fruits.get(0).getY()) * (point.getY() - fruits.get(0).getY()))));
        for (int i = 0; i < fruits.size(); i++) {
            norme = Math.sqrt(Math.abs(((point.getX() - fruits.get(i).getX()) * (point.getX() - fruits.get(i).getX())) + ((point.getY() - fruits.get(i).getY()) * (point.getY() - fruits.get(i).getY()))));
            if (norme < Min) {
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
