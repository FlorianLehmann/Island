package fr.unice.polytech.si3.qgl.iaac.Carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 06/02/2017.
 */
public class Carte {

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
                Case square = new Case(new Point((coords.x * 3) + i, (coords.y * 3) + j));
                square.update(json);
                carte.add(square);
            }
        }
    }

    public boolean tmp_hasAcrique() {
        for (Case i : carte)
            if (i.hasCreek())
                return true;
        return false;
    }
    /*public Case getCase(Point coords) {
        for (Case i : carte) {
            if (i.getCoords().getLocation().equals(new Point((coords.x) * 3, (coords.y) * 3))) return i;
        }
        return null;
    }*/


}
