package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sebde on 11/12/2016.
 */
public class Flower extends Res {
    private static java.util.List<Point> flower;
    private int amount = 0;

    private static int[][] tab = new int[200][200];

    /**
     * init the tab
     */
    static {
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                tab[i][j] = 0;
            }
        }
    }

    /**
     * make a convolution in order to know where is located (the most of) a resource
     * @return point
     */
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

    /**
     * default constructor
     */
    public Flower() {
        flower = new ArrayList();
    }

    /**
     * Do there is Flower?
     * @return true if it is the case
     */
    public boolean hasR() {
        return flower.size() != 0;
    }

    /**
     * add a biome Flower
     * @param point
     */
    public static void addFlower(Point point) {
        tab[(int) point.getX() + 100][(int) point.getY() + 100] = 1;
        flower.add(new Point((int) point.getX(), (int) point.getY()));
    }

    /**
     * get the nearest biome from a location
     * @param point of location
     * @return point of biome
     */
    public Point getNearest(Point point) {
        double norme;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX() - flower.get(0).getX()) * (point.getX() - flower.get(0).getX())) + ((point.getY() - flower.get(0).getY()) * (point.getY() - flower.get(0).getY()))));
        for (int i = 0; i < flower.size(); i++) {
            norme = Math.sqrt(Math.abs(((point.getX() - flower.get(i).getX()) * (point.getX() - flower.get(i).getX())) + ((point.getY() - flower.get(i).getY()) * (point.getY() - flower.get(i).getY()))));
            if (norme < Min) {
                Min = norme;
                index = i;
            }
        }
        return flower.get(index);
    }

    /**
     * getter for amount
     * @return amount to collect
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter for amount
     * @param amount collected
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
