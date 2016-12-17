package sample.bot.men;

import sample.bot.EnumDirection;

import static sample.bot.EnumDirection.EST;
import static sample.bot.EnumJSON.EXPLOIT;
import static sample.bot.EnumJSON.MOVETO;

/**
 * Created by dev on 17/12/2016.
 */
public class State4 implements State {

    private static EnumDirection direction = EST;
    private static int compteur = 2;
    private static int compteurNbMove = 1;
    private static int compteurNbMove2 = 1;

    private static int etat = 0;

    @Override
    public void execute(Men men) {
        switch (etat) {
            case 0:

                men.setAction(MOVETO.toString(direction.front()));
                compteurNbMove--;
                if(compteurNbMove <= 0) {
                    direction = EnumDirection.getEnumDirection(direction.left());
                    etat = 1;
                    compteurNbMove = compteurNbMove2;
                }
                break;
            case 1:
                men.setAction(MOVETO.toString(direction.front()));
                compteurNbMove--;
                if(compteurNbMove <= 0) {
                    direction = EnumDirection.getEnumDirection(direction.left());

                    etat = 0;
                    compteurNbMove2++;
                    compteurNbMove = compteurNbMove2;
                }
                break;
        }
        /*if (compteur == 0){
            compteurNbMove2++;
            compteurNbMove = compteurNbMove2;

        }
        if (compteurNbMove > 0 && compteur > 0) {
            //avancer dans la même direction
            compteurNbMove--;
            if (compteurNbMove == 0)
                compteur--;
        }
        else {
            compteurNbMove = compteurNbMove2 + 1;
            compteurNbMove2++;
        }

        direction = EnumDirection.getEnumDirection(direction.left());*/

    }

    @Override
    public void wait(Men men) {
        men.setState(new State2());
    }
}
