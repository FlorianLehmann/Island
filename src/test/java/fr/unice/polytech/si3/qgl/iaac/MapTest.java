package fr.unice.polytech.si3.qgl.iaac;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 12/11/2016.
 */
public class MapTest {
    Map map=new Map();

    @Test
    public void returnCreek(){
        //Map map=new Map();
        map.addCase(new Point(0,0));
        map.addCase(new Point(0,2));
        map.setCreek(new Point(0,0));
        assertEquals(map.getcreek().size(),1);
    }

    @Test
    public void returnPU(){
        //Map map=new Map();
        map.addCase(new Point(0,1));
        map.setPu(new Point(0,1));
        assertEquals(map.getEmergency().getCoords(),new Point(0,1));
    }

    @Test
    public void setbiome(){
        List<String> biomes=new ArrayList<String>();
        biomes.add("FOREST");
        biomes.add("BEACH");
        map.setBiomeCase(new Point(0,2), (ArrayList<String>) biomes);
        assertEquals(map.getBiomes(new Point(0,2)),biomes);
    }
    @Test
    public void setressource(){
        List<String> ressource=new ArrayList<>();
        List<String> ressourceq=new ArrayList<>();
        ressource.add("wood");
        ressourceq.add("1000");
        map.setRessourceCase(new Point(0,1), (ArrayList<String>) ressource);
        map.setRessourcesQCase(new Point(0,1), (ArrayList<String>) ressourceq);
        assertEquals(map.getRessource(new Point(0,1)),ressource);
        assertEquals(map.getRessourceq(new Point(0,1)),ressourceq);
    }

    @Test
    public void nearestCreek(){
        map.addCase(new Point(0,0));
        map.addCase(new Point(10,0));
        map.addCase(new Point(20,0));
        map.setPu(new Point(0,0));
        map.setCreek(new Point(10,0));
        map.setCreek(new Point(20,0));
        assertEquals(map.getNearestCreek().getCoords(),new Point(0,0));
    }
}
