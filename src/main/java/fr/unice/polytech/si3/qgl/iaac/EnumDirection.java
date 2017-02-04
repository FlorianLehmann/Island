package fr.unice.polytech.si3.qgl.iaac;

import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.*;

/**
 * Created by Quentin on 03/02/2017.
 */
public enum EnumDirection {

    RIGHT(NORTH,WEST,SOUTH,EST),
    LEFT(SOUTH,EST,NORTH,WEST);


    EnumOrientation directionEst, directionNorth, directionWest, directionSouth;

    EnumDirection(EnumOrientation directionEst, EnumOrientation directionNorth, EnumOrientation directionWest, EnumOrientation directionSouth){
        this.directionEst = directionEst;
        this.directionNorth = directionNorth;
        this.directionWest = directionWest;
        this.directionSouth = directionSouth;
    }

    public EnumOrientation getDirectionEst(){
        return directionEst;
    }

    public EnumOrientation getDirectionWest() {
        return directionWest;
    }

    public EnumOrientation getDirectionNorth() {
        return directionNorth;
    }

    public EnumOrientation getDirectionSouth() {
        return directionSouth;
    }


}
