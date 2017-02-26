package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.Player;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.BACK;
import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.FRONT;
import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.LEFT;

/**
 * Created by lehmann on 11/02/17.
 */
public class Men extends Player{



    /**
     * default constructor
     * @param coord
     */
    public Men(Point coord) {
        super(coord, 1);
    }

    public String moveTo(EnumOrientation orientation) {
        super.orientation = orientation;
        changeCoord();
        return EnumJSON.MOVETO.toString(orientation.toString());
    }

    public String glimpse(EnumOrientation orientation, int nbCase){
        return EnumJSON.GLIMPSE.toString(orientation.toString(),nbCase);
    }

    public String scout(EnumOrientation orientation){
        return EnumJSON.SCOUT.toString(orientation.toString());
    }

    public String explore() {
        return EnumJSON.EXPLORE.toString("");
    }

    public String exploit(EnumResources resource) {
        return EnumJSON.EXPLOIT.toString(resource.toString());
    }


    public String transform(EnumPrimaryResources resources,int nb){
        return EnumJSON.TRANSFORM.toString(resources.toString(),nb);
    }



}
