package fr.unice.polytech.si3.qgl.iaac.carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 06/02/2017.
 */
public class Carte {

    //// TODO: 13/02/2017
    private static final Logger logger = LogManager.getLogger(Carte.class);


    private List<Case> carte;
    private ReadJSON json;


    public Carte(ReadJSON readJSON) {
        json = readJSON;
        carte = new ArrayList<>();
    }

    public void addAirCase(Point coords) {

        for (Case i : carte) {
            if (coords.x == i.getCoords().x && coords.y == i.getCoords().y) {
                i.update(json);
                return;
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Case square = new Case(new Point(coords.x + i, coords.y  + j));
                square.update(json);
                carte.add(square);
            }
        }
    }


    /* todo
    TEMPORARY
     */
    public boolean tmp_hasAcrique() {
        for (Case i : carte)
            if (i.hasCreek())
                return true;
        return false;
    }

    public void addGroundCase(Point coord) {
        throw new UnsupportedOperationException();
    }

    public boolean hasResource(EnumResources name) {
        for (Case i : carte)
            if (i.containsResource(name)) {
                logger.info("Carte: hasResource looking for" +name +" " + true);

                return true;
            }
        logger.info("Carte: hasResource looking for" +name +" " + false + carte.size());

        return false;
    }

    public Point getACreek() {
        for (int i = 0; i < carte.size(); i++) {
            if (carte.get(i).hasCreek())
                return carte.get(i).getCoords();
        }
        throw new RuntimeException("Aucune crique n'est présente");
        /*for (Case i : carte)
            if (i.hasCreek())
                return i.getCoords();*/

    }

    public String getCreekID() {
        for (int i = 0; i < carte.size(); i++) {
            if (carte.get(i).hasCreek())
                return carte.get(i).getIdCreek();
        }
        throw new RuntimeException("Aucune crique n'est présente");
    }

    public Point getResource(EnumResources name) {
        for (int i = 0; i < carte.size(); i++) {
            if (carte.get(i).containsResource(name))
                return carte.get(i).getCoords();
        }
        throw new RuntimeException("No resources");

    }



}
