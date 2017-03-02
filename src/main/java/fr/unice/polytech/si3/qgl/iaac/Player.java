package fr.unice.polytech.si3.qgl.iaac;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.*;

/**
 * Created by florian on 24/02/2017.
 */
public abstract class Player {

    protected Point coord;
    protected EnumOrientation orientation;
    protected final int nbCaseUsedForAction;

    protected Player(Point coord, int nbCaseUsedForAction) {
        this.coord = coord;
        this.nbCaseUsedForAction = nbCaseUsedForAction;
    }

    protected Player(Point coord, EnumOrientation orientation, int nbCaseUsedForAction) {
        this.coord = coord;
        this.orientation = orientation;
        this.nbCaseUsedForAction = nbCaseUsedForAction;
    }

    protected void changeCoord(){
        switch (orientation) {
            case NORTH:
                coord.setLocation(coord.x, coord.y + nbCaseUsedForAction);
                break;
            case SOUTH:
                coord.setLocation(coord.x, coord.y - nbCaseUsedForAction);
                break;
            case WEST:
                coord.setLocation(coord.x - nbCaseUsedForAction, coord.y);
                break;
            case EST:
                coord.setLocation(coord.x + nbCaseUsedForAction, coord.y);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @return
     */
    public Point getCoord() {
        return new Point(coord.x, coord.y);
    }

}