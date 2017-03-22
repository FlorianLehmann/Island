package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;

import fr.unice.polytech.si3.qgl.iaac.Player;



import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.*;

/**
 * Created by Quentin on 03/02/2017.
 */
public class Drone extends Player {

    /**
     * attributes
     */
    private EnumDirection lastDirection;

    /**
     * default constructor
     * @param orientation
     */
    public Drone(EnumOrientation orientation) {
        super(new Point(0,0), orientation, 3);
        this.lastDirection = EnumDirection.RIGHT ;
    }

    /**
     * Echo
     * @param direction
     * @return the json request
     */
    public String echo(EnumDirection direction){
        return ECHO.toString(changeOrientation(direction).toString());
    }

    /**
     * Scan
     * @return the json request
     */
    public String scan(){
        return SCAN.toString("");
    }

    /**
     * Fly
     * @return the json request
     */
    public String fly(){
        changeCoord();
        return FLY.toString("");
    }

    /**
     * Heading
     * @param direction
     * @return the json request
     */
    public String heading(EnumDirection direction){
        lastDirection = direction;
        changeCoord();
        orientation = changeOrientation(direction);
        changeCoord();
        return HEADING.toString(orientation.toString());
    }

    /**
     * Change the rotate direction
     * @param direction
     * @return the json request
     */
    private EnumOrientation changeOrientation(EnumDirection direction) {
        if (direction == LEFT) {
            return orientation.left();
        }
        else if (direction == FRONT) {
            return orientation.front();
        }
        return orientation.right();
    }

    /**
     *
     * @return the last direction of the drone
     */
    public EnumDirection getLastDirection(){
        return lastDirection;
    }

}
