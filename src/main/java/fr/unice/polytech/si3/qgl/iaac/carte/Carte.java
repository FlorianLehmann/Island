package fr.unice.polytech.si3.qgl.iaac.carte;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.POI;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;
import java.awt.geom.Point2D;

public class Carte {

    private List<Case> cases;


    /**
     * default constructor
     *
     */
    public Carte() {
	cases = new LinkedList();
        //a suppr pour test
        //cases.add((new Case(new Point(0,0))));
    }
    
    /**
     * Add case to the map
     * @param Point point
     */
    public void addCase(Point point) {
        cases.add(new Case(new Point((int)point.getX(),(int)point.getY())));//
    }
    
    /**
     * Set a new POI
     * @param POI poi
     * @param Point point
     */
    public void setPOI(POI poi, Point point) {
	int i = 0;
	while( !cases.get(i).getCoords().equals(point) )
	    i++;
	cases.get(i).setPOI(poi);
    }

    /**
     * @return boolean
     * true if there is a PU
     */
    public boolean hasPU() {
	for (int i = 0; i < cases.size() ; i++)
	    if (cases.get(i).hasPU() == true)
		return true;
	return false;
    }

    /**
     * @return boolean
     * true if there is a Creek
     */
    public boolean hasCreek() {
	for (int i = 0; i < cases.size() ; i++)
	    if (cases.get(i).hasCreek() == true)
		return true;
	return false;
    }

    /**
     * @return POI pu
     *
     */
    public Case getCasePU() {
	for (int i = 0; i < cases.size() ; i++)
	    if (cases.get(i).hasPU() == true)
		return cases.get(i);
	return null;
    }
    
    /**
     * @return String id of pu
     *
     */
    public String getPU() {
        for (int i = 0; i < cases.size() ; i++)
            if (cases.get(i).hasPU() == true)
                return cases.get(i).getPU().toString();
        return null;
    }

    /**
     * get nearest creek from PU
     *
     */
    public String getNearestCreekPU() {
	Point pu = getCasePU().getCoords();
	String id = new String();
	double normeMin = Integer.MAX_VALUE;
	double norme = 0;
	
	for (int i = 0; i < cases.size() ; i++) {
	    if(cases.get(i).hasCreek()) {
		norme = Point2D.distance( cases.get(i).getCoords().getX(), cases.get(i).getCoords().getY(), pu.getX(), pu.getY());
		if (norme < normeMin) {
		    normeMin = norme;
            id = cases.get(i).getCreek().toString();// + "N:" +norme + "\n";
		}
	    }
	}
	return id;

    }

}


		   