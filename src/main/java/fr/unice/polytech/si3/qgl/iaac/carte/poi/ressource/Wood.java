package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wood extends Res {
    private int amount = 0;

    private static List<Point> wood = new ArrayList();

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

    public boolean hasR() {
        return wood.size() != 0;
    }

    public static void addWood(Point point) {
        wood.add(new Point((int) point.getX(), (int) point.getY()));
        tab[(int) point.getX() + 100][(int) point.getY() + 100] = 1;
    }

    @Override
    public Point getNearest(Point point) {
        double norme;
        double Min;
        int index = 0;
        Min = Math.sqrt(Math.abs(((point.getX() - wood.get(0).getX()) * (point.getX() - wood.get(0).getX())) + ((point.getY() - wood.get(0).getY()) * (point.getY() - wood.get(0).getY()))));
        for (int i = 0; i < wood.size(); i++) {
            norme = Math.sqrt(Math.abs(((point.getX() - wood.get(i).getX()) * (point.getX() - wood.get(i).getX())) + ((point.getY() - wood.get(i).getY()) * (point.getY() - wood.get(i).getY()))));
            if (norme < Min) {
                Min = norme;
                index = i;
            }
        }
        return wood.get(index);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
