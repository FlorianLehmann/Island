package fr.unice.polytech.si3.qgl.iaac.carte;

import fr.unice.polytech.si3.qgl.iaac.carte.poi.POI;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Carte {

    private List<Case> cases;


    /**
     * default constructor
     */
    public Carte() {
        cases = new LinkedList();
    }

    /**
     * Add case to the map
     *
     * @param Point point
     */
    public void addCase(Point point) {
        cases.add(new Case(new Point((int) point.getX(), (int) point.getY())));//
    }

    /**
     * Set a new POI
     *
     * @param POI   poi
     * @param Point point
     */
    public void setPOI(POI poi, Point point) {
        int i = 0;
        while (!cases.get(i).getCoords().equals(point))
            i++;
        cases.get(i).setPOI(poi);
    }

    /**
     * @return boolean
     * true if there is a PU
     */
    public boolean hasPU() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasPU())
                return true;
        return false;
    }

    /**
     * @return boolean
     * true if there is a Creek
     */
    public boolean hasCreek() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasCreek())
                return true;
        return false;
    }

    /**
     * @return POI pu
     */
    public Case getCasePU() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasPU())
                return cases.get(i);
        return null;
    }

    /**
     * @return POI crique
     */
    public String getCaseCreek() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasCreek())
                return cases.get(i).getCreek().toString();
        return null;
    }


    public Point getCoordCreek() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasCreek())
                return cases.get(i).getCoords();
        return null;
    }

    /**
     * @return String id of pu
     */
    public String getPU() {
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasPU())
                return cases.get(i).getPU().toString();
        return null;
    }

    /**
     * get nearest creek from PU
     */
    public String getNearestCreekPU() {
        Point pu = getCasePU().getCoords();
        String id = "";
        double normeMin = Integer.MAX_VALUE;
        double norme;

        for (int i = 0; i < cases.size(); i++) {
            if (cases.get(i).hasCreek()) {
                norme = Point2D.distance(cases.get(i).getCoords().getX(), cases.get(i).getCoords().getY(), pu.getX(), pu.getY());
                if (norme < normeMin) {
                    normeMin = norme;
                    id = cases.get(i).getCreek().toString();// + "N:" +norme + "\n";
                }
            }
        }
        return id;

    }

    /**
     * get the number of Creeks
     *
     * @return int
     */
    public int getNbCreek() {
        int j = 0;
        for (int i = 0; i < cases.size(); i++)
            if (cases.get(i).hasCreek() == true)
                j++;
        return j;

    }

}


		   
