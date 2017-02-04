package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;

/**
 * Created by Quentin on 03/02/2017.
 */
public class Drone {

    private Point coord;
    private EnumOrientation orientation;

    public Drone(EnumOrientation orientation) {
        this.coord = new Point(0,0);
        this.orientation = orientation;
    }


    public String echo(EnumDirection direction){
        orientation = direction.getDirection(orientation);
        return ECHO.toString(orientation.toString());
    }


    public String scan(){
        return SCAN.toString();
    }


    public String fly(){
        this.changeCoord();
        return FLY.toString(orientation.direction());
    }


    public String heading(EnumDirection direction){
        this.changeCoord();
        orientation = direction.getDirection(orientation);
        this.changeCoord();
        return HEADING.toString(orientation.direction());
    }


    public void changeCoord(){
        switch (orientation) {
            case NORTH:
                coord.setLocation(coord.getX(), coord.getY() + 1);
                break;
            case SOUTH:
                coord.setLocation(coord.getX(), coord.getY() - 1);
                break;
            case WEST:
                coord.setLocation(coord.getX() - 1, coord.getY());
                break;
            case EST:
                coord.setLocation(coord.getX() + 1, coord.getY());
                break;
            default:
                break;
        }
    }

}
