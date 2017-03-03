package fr.unice.polytech.si3.qgl.iaac.carte;

import java.awt.*;
import java.util.List;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMap {

    /**
     * attributes
     */
    private Case [][] arrayMap;
    private int originX;
    private int originY;
    private int size;

    private boolean [][] edge;

    public ArrayMap(List<Case> map) {
        size = map.size();
        edge = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                edge[i][j] = false;
            }
        }
        for (int i = 0; i < map.size(); i++) {
            if ( map.get(i).getCoords().x < originX)
                originX = map.get(i).getCoords().x;
            if (map.get(i).getCoords().y < originY)
                originY = map.get(i).getCoords().y;
        }

        arrayMap = new Case[size][size];

        for (int i = originX; i < size - originX; i++) {
            for (int j = originY; j < size - originY; j++) {
                arrayMap[i - originX][j - originY] = new Case(new Point(i,j));
            }
        }

        for (int i = 0; i < map.size(); i++) {
            int x = map.get(i).getCoords().x - originX;
            int y = map.get(i).getCoords().y - originY;
            arrayMap[x][y] = map.get(i);
        }

        computeEdge();
    }

    public Case get(int x, int y) {
        return arrayMap[x - originX][y - originX];
    }

    public void add(Case tile){
        arrayMap[tile.getCoords().x - originX][tile.getCoords().y - originY] = tile;
    }

    private boolean isAnEdge(int x, int y) {
        //si on sort de la carte
        if (x - originX - 1 < 0 || x-originX + 1 > size || y-originY - 1 < 0 || y-originY + 1>size)
            return false;
        //sinon
        if (!arrayMap[x - originX][y - originX].containOcean() && (arrayMap[x - originX - 1][y - originX].containOcean() ||
                arrayMap[x - originX][y - originX - 1].containOcean() || arrayMap[x - originX][y - originX + 1].containOcean() ||
                arrayMap[x - originX + 1][y - originX].containOcean()))
            return true;
        return false;
    }

    private void computeEdge() {
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                edge[i][j] = isAnEdge(i - originX,j - originY);
            }
        }

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                //edge[i][j] = isAnEdge(i - originX,j - originY);
            }
        }
    }

    public boolean isEdge(int x, int y) {
        return edge[x - originX][y - originY];
    }
}
