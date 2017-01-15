package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.EST;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.MOVETO;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

/**
 * Created by dev on 17/12/2016.
 */
public class TournerRond implements State {

    private static EnumDirection direction = EST;
    private static int compteurNbMove = 1;
    private static int compteurNbMove2 = 1;

    private static int etat = 0;

    /**
     * tourner en rond autour du biome
     * @param men
     */
    @Override
    public void execute(Men men) {
        switch (etat) {
            case 0:

                men.setAction(MOVETO.toString(direction.front()));
                men.setCoord(direction);
                compteurNbMove--;
                if (compteurNbMove <= 0) {
                    direction = EnumDirection.getEnumDirection(direction.left());
                    etat = 1;
                    compteurNbMove = compteurNbMove2;
                }
                break;
            case 1:
                men.setAction(MOVETO.toString(direction.front()));
                men.setCoord(direction);
                compteurNbMove--;
                if (compteurNbMove <= 0) {
                    direction = EnumDirection.getEnumDirection(direction.left());

                    etat = 0;
                    compteurNbMove2++;
                    compteurNbMove = compteurNbMove2;
                }
                break;
        }

    }

    /**
     * init the attributes
     */
    public static void init() {
        compteurNbMove = 1;
        compteurNbMove2 = 1;
        etat = 0;
    }

    /**
     * Analyse du résultat
     * @param men
     */
    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get(COST.toString()));

        men.setState(new Explore());
    }
}
