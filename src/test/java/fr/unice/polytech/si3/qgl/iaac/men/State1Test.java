package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;


/**
 * Created by Quentin on 14/01/2017.
 */
public class State1Test {
    private Men men;
    private Carte carte;
    private Point point;

    @Before
    public void init() {
        carte = new Carte();
        point = new Point(0, 0);
        men = new Men(carte, point);
        men.setState(new State1());
    }

    @Test
    public void waitTest(){
        men.getState().wait(men);
        assertTrue(men.getState() instanceof State11);
    }


}
