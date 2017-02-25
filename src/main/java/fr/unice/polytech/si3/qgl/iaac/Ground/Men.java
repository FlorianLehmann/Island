package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.Player;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.awt.*;

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

    public String explore() {
        return EnumJSON.EXPLORE.toString("");
    }

    public String exploit(EnumResources resource) {
        return EnumJSON.EXPLOIT.toString(resource.toString());
    }

}
