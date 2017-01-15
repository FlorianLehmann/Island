package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLOIT;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLORE;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.MOVETO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Quentin on 14/01/2017.
 */
public class ContinueSearchTest {
    private ReadJSON read;
    private Men men;
    private Carte carte;
    private Point point;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        point = new Point(0, 0);
        men = new Men(carte, point);
        men.setState(new State4());
        men.setBudget(1000);
    }

    @Test
    public void case0EstTest(){
        men.getState().execute(men);
        assertEquals(MOVETO.toString("S"),men.getAction());
    }

    @Test
    public void case1NorthTest(){
        men.getState().execute(men);
        men.getState().execute(men);
        assertEquals(MOVETO.toString("N"),men.getAction());
    }

    @Test
    public void case0WestTest(){
        men.getState().execute(men);
        men.getState().execute(men);
        men.getState().execute(men);
        assertEquals(MOVETO.toString("E"),men.getAction());
    }

    @Test
    public void case1SouthTest(){
        men.getState().execute(men);
        men.getState().execute(men);
        men.getState().execute(men);
        men.getState().execute(men);
        men.getState().execute(men);
        assertEquals(MOVETO.toString("S"),men.getAction());
    }

    @Test
    public void waitTest(){
        read.read("{\"cost\": 12}");
        men.getState().wait(men);
        assertTrue(men.getState() instanceof State2);
    }

}




