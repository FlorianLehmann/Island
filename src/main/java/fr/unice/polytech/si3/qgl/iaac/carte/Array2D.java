package fr.unice.polytech.si3.qgl.iaac.carte;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 01/03/2017.
 */
public class Array2D {

    /**
     * attributes
     */
    private Case [][] array2d;
    private int originX;
    private int originY;
    private int size;

    /**
     * default constructor
     * the default array's size is 200
     */
    public Array2D() {
        size = 200;
        array2d = new Case[size][size];
        originX = 0;
        originY = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array2d[i][j] = new Case(new Point(i,j));
            }
        }
    }

    //TODO  RETOURNER UN NULL OU UN TRY CATCH?
    /**
     *
     * @param x
     * @param y
     * @return the case at (x,y)
     */
    public Case get(int x, int y) {
        if (x + originX >= size || y + originY >= size || y + originY < 0 || y + originY < 0)
            throw new IndexOutOfBoundsException("Out of bound");
        return array2d[x + originX][y + originY];
    }

    /**
     * add a case
     * @param tile
     */
    public void add(Case tile) {
        int x = tile.getCoords().x;
        int y = tile.getCoords().y;

        if ( Math.abs(x) + originX >= size || Math.abs(y) + originY >= size)
            resize();

        if ( x < -originX || y < -originY)
            changeOrigin(x, y);

        array2d[x + originX][y + originY] = tile;
    }

    /**
     * resize the array
     */
    private void resize() {

        Case [][] array2d = new Case[size*2][size*2];

        for (int i = 0; i < size*2; i++) {
            for (int j = 0; j < size*2; j++) {
                array2d[i][j] = new Case(new Point(i,j));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array2d[i + originX][j + originY] = this.array2d[i + originX][j + originY];
            }
        }

        size *= 2;
        this.array2d = array2d;
    }

    /**
     * change the origin of the array in order to handle negative number
     * @param x
     * @param y
     */
    private void changeOrigin(int x, int y) {

        int originXTmp = originX;
        int originYTmp = originY;
        int sizeTmp;

        if (x < -originX)
            originX = -x;
        if (y < -originY)
            originY = -y;


        sizeTmp = Math.max(size + originX, size + originY);
        Case [][] array2d = new Case[sizeTmp][sizeTmp];

        for (int i = size; i < sizeTmp; i++) {
            for (int j = size; j < sizeTmp; j++) {
                array2d[i][j] = new Case(new Point(i,j));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array2d[i + originX][j + originY] = this.array2d[i + originXTmp][j + originYTmp];
            }
        }
        size = sizeTmp;
        this.array2d = array2d;

    }

    public int getSize() {
        return size;
    }

    public int getOriginX(){
        return originX;
    }

    public int getOriginY(){
        return originY;
    }

}
