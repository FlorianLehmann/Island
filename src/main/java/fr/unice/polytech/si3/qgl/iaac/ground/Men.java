package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.json.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.Player;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
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
    public String transform(EnumPrimaryResources resources1,EnumPrimaryResources ressource2,int nbRessource1,int nbRessource2){
        return EnumJSON.TRANSFORM.toStringTranform(resources1.toString(),ressource2.toString(),nbRessource1,nbRessource2);
    }



}
