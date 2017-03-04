package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by lehmann on 12/02/17.
 */
public class TounerRond implements State {

    private static EnumOrientation direction = EnumOrientation.EST;
    private static int compteurNbMove;
    private static int compteurNbMove2;
    private static int etat;

    public TounerRond() {
        //direction = EnumOrientation.EST;
        //compteurNbMove = 1;
        //compteurNbMove2 = 1;
        //etat = 0;
    }

    public static void init() {
        compteurNbMove = 1;
        compteurNbMove2 = 1;
        etat = 0;
    }

    public String execute(Men men, Contracts contracts, Carte carte) {
        String str = new String();
        switch (etat) {
            case 0:
                compteurNbMove--;
                str =  men.moveTo(direction);
                if (compteurNbMove <= 0) {
                    direction = EnumOrientation.getEnumDirection(direction.left().toString());
                    etat = 1;
                    compteurNbMove = compteurNbMove2;
                }
                break;
            case 1:
                compteurNbMove--;
                str = men.moveTo(direction);
                if (compteurNbMove <= 0) {
                    direction = EnumOrientation.getEnumDirection(direction.left().toString());
                    etat = 0;
                    compteurNbMove2++;
                    compteurNbMove = compteurNbMove2;
                }
                break;
        }
        return str;
    }

    @Override
    public State wait(ReadJSON json) {
        return new Explore();
    }
}
