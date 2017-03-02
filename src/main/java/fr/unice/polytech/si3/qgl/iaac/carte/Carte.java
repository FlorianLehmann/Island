package fr.unice.polytech.si3.qgl.iaac.carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 06/02/2017.
 */
public class Carte {


    private Array2D map;
    private ReadJSON json;


    public Carte(ReadJSON readJSON) {
        json = readJSON;
        map = new Array2D();
    }

    public void addAirCase(Point coords) {

        try {
            map.get(coords.x,coords.y).update(json);
        } catch (IndexOutOfBoundsException exception) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Case tile = new Case(new Point(coords.x + i, coords.y  + j));
                    tile.update(json);
                    map.add(tile);
                }
            }
        }

    }


    /* todo
    TEMPORARY
     */
    public boolean tmp_hasAcrique() {
        for (int i = 0; i < map.getSize(); i++)
            for (int j = 0; j < map.getSize(); j++)
                if (map.get(i,j).hasCreek())
                    return true;
        return false;
    }

    public void addGroundCase(Point coord) {
        throw new UnsupportedOperationException();
    }

    public boolean hasResource(EnumResources name) {
        for (int i = 0; i < map.getSize(); i++)
            for (int j = 0; j < map.getSize(); j++)
                if (map.get(i,j).containsResource(name))
                    return true;
        return false;
    }

    public Point getACreek() {
        for (int i = 0; i < map.getSize(); i++)
            for (int j = 0; j < map.getSize(); j++)
                if (map.get(i,j).hasCreek())
                    return map.get(i,j).getCoords();
        throw new RuntimeException("Aucune crique n'est présente");
    }

    public String getCreekID() {
        for (int i = 0; i < map.getSize(); i++)
            for (int j = 0; j < map.getSize(); j++)
                if (map.get(i,j).hasCreek())
                    return map.get(i,j).getIdCreek();
        throw new RuntimeException("Aucune crique n'est présente");
    }

    public Point getResource(EnumResources name) {
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.get(i, j).containsResource(name)) {
                    Case tile = map.get(i,j);
                    tile.removeResource(name);
                    return tile.getCoords();
                }
            }
        }
        throw new RuntimeException("No resources");
    }



}
