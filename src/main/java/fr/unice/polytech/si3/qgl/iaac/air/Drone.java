package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.Ground.DefineWay;
import fr.unice.polytech.si3.qgl.iaac.Player;


import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;

/**
 * Created by Quentin on 03/02/2017.
 */
public class Drone extends Player {


    private EnumDirection lastDirection;

    public Drone(EnumOrientation orientation) {
        super(new Point(0,0), orientation, 3);
        this.lastDirection = EnumDirection.RIGHT ;
    }


    public String echo(EnumDirection direction){
        return ECHO.toString(changeOrientation(direction).toString());
    }


    public String scan(){
        return SCAN.toString("");
    }


    public String fly(){
        changeCoord();
        return FLY.toString("");
    }


    public String heading(EnumDirection direction){
        lastDirection = direction;
        changeCoord();
        orientation = changeOrientation(direction);
        changeCoord();
        return HEADING.toString(orientation.toString());
    }

    private EnumOrientation changeOrientation(EnumDirection direction) {
        if (direction == LEFT) {
            return orientation.left();
        }
        else if (direction == FRONT) {
            return orientation.front();
        }
        return orientation.right();
    }

    public EnumDirection getLastDirection(){
        return lastDirection;
    }

}
