package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;

/**
 * Created by Quentin on 03/02/2017.
 */
public class Drone {

    private Point coord;
    private EnumOrientation orientation;
    private EnumDirection lastDirection;

    public Drone(EnumOrientation orientation) {
        this.coord = new Point(0,0);
        this.orientation = orientation;
        this.lastDirection = EnumDirection.RIGHT ;
    }


    public String echo(EnumDirection direction){
        changeOrientation(direction);
        return ECHO.toString(orientation.toString());
    }


    public String scan(){
        return SCAN.toString();
    }


    public String fly(){
        changeCoord();
        return FLY.toString(orientation.toString());
    }


    public String heading(EnumDirection direction){
        lastDirection = direction;
        changeCoord();
        changeOrientation(direction);
        changeCoord();
        return HEADING.toString(orientation.toString());
    }


    private void changeCoord(){
        switch (orientation) {
            case NORTH:
                coord.setLocation(coord.x, coord.y + 3);
                break;
            case SOUTH:
                coord.setLocation(coord.x, coord.y - 3);
                break;
            case WEST:
                coord.setLocation(coord.x - 3, coord.y);
                break;
            case EST:
                coord.setLocation(coord.x + 3, coord.y);
                break;
            default:
                break;
        }
    }

    private void changeOrientation(EnumDirection direction) {
        if (direction == LEFT) {
            orientation = orientation.left();
        }
        else if (direction == FRONT) {
            orientation = orientation.front();
        }
        else {
            orientation = orientation.right();
        }
    }

    public Point getCoord() {
        return new Point(coord.x, coord.y);
    }

}
