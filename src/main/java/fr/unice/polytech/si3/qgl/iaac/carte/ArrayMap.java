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

        //todo juste size pas 2*size
        arrayMap = new Case[2*size][2*size];

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

        boolean [][] edgeTmp = new boolean[size][size];

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                edgeTmp[i][j] = false;
            }
        }

        //pas besoin de gérer le cas ou on sort de l carte car il est imossible d'avoir une edge au bord de la carte
        for (int i = 1; i < size - 1 ; i++) {
            for (int j = 1; j < size - 2; j++) {
                if (!edge[i-1][j]) {
                    //on obtient le point le plus à gauche
                    for (int k = 0; k < size; k++) {
                        if (edge[k][j+2]) {
                            //ici on interpole
                            edgeTmp[k + (int)((i-k)/2.)][j+1] = true;
                            break;
                        }
                    }
                }
                if (!edge[i+1][j])
                {
                    //on obtient le point le plus à droite
                    for (int k = size-1; k >= 0; k--) {
                        if (edge[k][j+2]) {
                            //on interpole
                            edgeTmp[k - (int)((k-i)/2.)][j+1] = true;
                            break;
                        }
                    }
                }
                //et si il n'y a pas de points en dessous? ça fait rien
            }
        }
        //TODO on fait un ou logique sur les deux tableau
    }

    public boolean isEdge(int x, int y) {
        if (x - originX < 0 || y - originY<0 || x - originX >size - 1 || y - originY > size-1)
            return false;
        return edge[x - originX][y - originY];
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY(){
        return originY;
    }
}
