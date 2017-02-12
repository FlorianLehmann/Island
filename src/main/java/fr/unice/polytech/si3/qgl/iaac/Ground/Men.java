package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.awt.*;

/**
 * Created by lehmann on 11/02/17.
 */
public class Men {

    private Point coord;
    private EnumOrientation orientation;

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

    public String moveTo(EnumOrientation orientation) {
        this.orientation = orientation;
        changeCoord();
        return EnumJSON.MOVETO.toString(orientation.toString());
    }

    public String explore() {
        return EnumJSON.EXPLORE.toString("");
    }

    public String exploit(EnumResources resource) {
        return EnumJSON.EXPLOIT.toString(resource.toString());
    }


    private void changeCoord(){
        switch (orientation) {
            case NORTH:
                coord.setLocation(coord.x, coord.y + 1);
                break;
            case SOUTH:
                coord.setLocation(coord.x, coord.y - 1);
                break;
            case WEST:
                coord.setLocation(coord.x - 1, coord.y);
                break;
            case EST:
                coord.setLocation(coord.x + 1, coord.y);
                break;
            default:
                break;
        }
    }

}
