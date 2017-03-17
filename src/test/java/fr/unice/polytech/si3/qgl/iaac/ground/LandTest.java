package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.LAND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by florian on 19/02/2017.
 */
public class LandTest {

    private State land;


    @Before
    public void defineContext() {
        land = new Land(9);
    }

    @Test
    public void shouldLandWith7Men() {
        Carte carte = mock(Carte.class);
        when(carte.getCreekID()).thenReturn("ID");
        assertEquals(LAND.toString("ID", 7), land.execute(new Men(new Point(0,0)), new Contracts(), carte ));
    }

    @Ignore
    @Test
    public void shouldReturnDefineWayState() {
        ReadJSON json = mock(ReadJSON.class);
        assertTrue(land.wait(json) instanceof DefineWay);
    }


}
