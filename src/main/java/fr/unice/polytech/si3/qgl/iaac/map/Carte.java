package fr.unice.polytech.si3.qgl.iaac.map;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebde on 06/02/2017.
 */
public class Carte {

    private Map<Point, Case> carte;
    private ReadJSON json;


    public Carte(ReadJSON readJSON) {
        json = readJSON;
        carte = new HashMap<>();
    }

    public void addAirCase(Point coords) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point point = new Point(coords.x + i, coords.y + j);
                Case square = new Case(point);
                square.update(json);
                carte.put(point, square);
            }
        }

    }


    /* todo
    TEMPORARY
     */
    public boolean tmp_hasAcrique() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return true;
        return false;

    }

    public void addGroundCase(Point coord) {
        throw new UnsupportedOperationException();
    }

    public boolean hasResource(EnumResources name) {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().containsResource(name))
                return true;
        return false;

    }

    public Point getACreek() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return tile.getValue().getCoords();

        throw new RuntimeException("Aucune crique n'est présente");
    }

    public String getCreekID() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return tile.getValue().getIdCreek();

        throw new RuntimeException("Aucune crique n'est présente");
    }

    public Point getResource(EnumResources name) {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().containsResource(name)) {
                carte.remove(tile.getKey());
                return tile.getValue().getCoords();
            }

        throw new RuntimeException("No resources");

    }

    //TODO
    public Map<Point, Case> getCases() {
        return carte;
    }

}