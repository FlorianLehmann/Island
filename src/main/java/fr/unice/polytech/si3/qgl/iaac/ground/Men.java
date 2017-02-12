package fr.unice.polytech.si3.qgl.iaac.ground;

import java.awt.*;

/**
 * Created by lehmann on 11/02/17.
 */
public class Men {

    private Point coord;

    /**
     * default constructor
     * @param coord
     */
    public Men(Point coord) {
        this.coord = coord;
    }


    /**
     *
     * @return
     */
    public Point getCoord() {
        return new Point(coord.x, coord.y);
    }



}
