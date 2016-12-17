package sample.bot.carte.poi.ressource;

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
    public Wood() {

    }

    public static int[][] getTab() {
        return tab;
    }

    public static Point getTabMax() {
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

    public static String toStringg() {
        String str = new String("");
        for (int i = 5; i < 195; i++) {
            for (int j = 5; j < 195; j++) {
                str += tab[i][j];
            }
            str += "\n";
            }
        return str;
    }

    public boolean hasR() {
        return wood.size() != 0;
    }

    static public void addWood(Point point) {
        wood.add(new Point((int) point.getX(), (int) point.getY()));
        tab[(int) point.getX()+100][(int) point.getY()+100] = 1;
    }

    @Override
    public Point getNearest(Point point) {
        double norme = 0;
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
