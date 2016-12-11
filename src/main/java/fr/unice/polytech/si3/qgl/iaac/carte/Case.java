package sample.bot.carte;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import sample.bot.carte.poi.*;

public class Case {

    private Point coords;
    private List<POI> poi;

    /**
     * default constructor
     *
     */
    public Case(Point point){
	poi = new LinkedList();
	coords = point;
    }

    /**
     * @return Point point
     *
     */
    public Point getCoords(){
        return coords;
    }

    /**
     * set a POI
     * @param POI poi
     */
    public void setPOI(POI poi2) {
	poi.add(poi2);
    }

    /**
     * has a PU?
     * @return boolean
     */
    public boolean hasPU() {
	for (int i = 0; i < poi.size(); i++)
	    if (poi.get(i) instanceof PU)
		return true;
	return false;
    }

    /**
     * has a Creek?
     * @return boolean
     */
    public boolean hasCreek() {
	for (int i = 0; i < poi.size(); i++)
	    if (poi.get(i) instanceof Creek)
		return true;
	return false;
    }

    /**
     * return PU
     *
     */
    public POI getPU() {
	for (int i = 0; i < poi.size(); i++)
	    if (poi.get(i) instanceof PU)
		return poi.get(i);
	return null;

    }

    /**
     * return PU
     *
     */
    public POI getCreek() {
	for (int i = 0; i < poi.size(); i++)
	    if (poi.get(i) instanceof Creek)
		return poi.get(i);
	return null;
    }

}
