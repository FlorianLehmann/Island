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

    /**
     * attributes
     */
    private Map<Point, Case> carte;
    private ReadJSON json;

    /**
     * default constructor
     * @param readJSON
     */
    public Carte(ReadJSON readJSON) {
        json = readJSON;
        carte = new HashMap<>();
    }

    /**
     * create 9 tile
     * @param coords
     */
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

    /**
     * add a tile
     * @param coord
     */
    public void addGroundCase(Point coord) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return true if there is a creek on the map
     */
    public boolean hasAcrique() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return true;
        return false;

    }

    /**
     *
     * @param name
     * @return true if the resource is present on the map
     */
    public boolean hasResource(EnumResources name) {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().containsResource(name))
                return true;
        return false;

    }

    /**
     *
     * @return the coord of a creek
     */
    public Point getACreek() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return tile.getValue().getCoords();

        throw new RuntimeException("Aucune crique n'est présente");
    }

    /**
     * return the ID of a creek
     * @return
     */
    public String getCreekID() {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().hasCreek())
                return tile.getValue().getIdCreek();

        throw new RuntimeException("Aucune crique n'est présente");
    }

    /**
     *
     * @param name
     * @return the coord of a biome containning a resource
     */
    public Point getResource(EnumResources name) {

        for (Map.Entry<Point, Case> tile: carte.entrySet())
            if (tile.getValue().containsResource(name)) {
                Point point = new Point(tile.getValue().getCoords());
                carte.remove(tile.getKey());
                return point;
            }

        throw new RuntimeException("No resources");

    }


    /**
     *
     * @param name
     * @return the coord of the nearest biome containning a resource
     */
    public Point getNearestResource(EnumResources name, Point location) {
        int distanceMin = Integer.MAX_VALUE;
        Case tile = null;
        for (Map.Entry<Point, Case> nearestTile: carte.entrySet())
            if (nearestTile.getValue().containsResource(name) && distance(nearestTile.getValue().getCoords(), location) < distanceMin && distance(nearestTile.getValue().getCoords(), location) > 0) {
                distanceMin = distance(nearestTile.getValue().getCoords(), location);
                tile = nearestTile.getValue() ;
            }

        if (tile == null)
            throw new RuntimeException();

        carte.remove(tile.getCoords());
        return tile.getCoords();
        //throw new RuntimeException("No resources");
    }

    /**
     *
     * @param coords
     * @param location
     * @return the Manathan distance between coords and location
     */
    private int distance(Point coords, Point location) {
        return Math.abs(location.x - coords.x) + Math.abs(location.y - coords.y);
    }

    /**
     * @return the map
     */
    public Map<Point, Case> getCases() {
        return carte;
    }

}