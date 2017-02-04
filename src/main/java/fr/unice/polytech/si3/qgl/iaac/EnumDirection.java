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


    public EnumOrientation getDirection(EnumOrientation orientation){
        switch (orientation) {
            case NORTH:
                return directionNorth;

            case SOUTH:
                return directionSouth;

            case WEST:
                return directionWest;

            case EST:
                return directionEst;

        }
        return null;
    }

}
