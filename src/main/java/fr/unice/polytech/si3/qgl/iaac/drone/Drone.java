package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.Creek;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.PU;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import java.awt.*;


public class Drone {

    /**
     *
     * Attributes
     *
     */
    private State state;
    private int budget;
    private String action;
    private Carte carte;
    private EnumDirection direction;
    private Point point;
    private String lastDirection;
    private int CaseToTarget;
    private int NbCaseRight;
    private int NbCaseLeft;
    private boolean lastGround;
    /**
     *
     * default constructor
     *
     */
    public Drone(EnumDirection direction, Carte carte) {
    this.carte = carte;
    //carte = new Carte();
	state = new State0();
	action = new String();
    budget =  (int) ReadJSON.getInformations().get("budget");
    point = new Point(0,0);
    this.lastDirection = "R"; // a suppr
        this.direction = direction;
	CaseToTarget = 0;
	NbCaseLeft = 0;
	NbCaseRight = 0;
	lastGround = true;
    }

    /**
     *
     * Set the current state
     * @param State state
     *
     */
    public void setState(State state) {
	this.state = state;
    }

    /**
     *
     * get the current state
     *
     *
     */
    public State getState() {
        if (budget < 21) {
            return new State11();
        }
	return state;
    }


    /**
     * get Case to target
     */
    public int getCaseToTarget() {
	return CaseToTarget;
    }

    /**
     * set CaseToTarget
     */
    public void setCaseToTarget(int CaseToTarget) {
	this.CaseToTarget = CaseToTarget;
    }

    /**
     * get direction
     */
    public EnumDirection getDirection() {
	return direction;
    }

    /**
     * set direction
     */
    public void setDirection(EnumDirection direction) {
	this.direction = direction;
    }

    /**
     * Set NbCaseRight
     */
    public void setNbCaseRight(int NbCaseRight) {
	this.NbCaseRight = NbCaseRight;
    }

    /**
     * get NbCaseRight
     */
    public int getNbCaseRight() {
	return NbCaseRight;
    }

    /**
     * Set NbCaseLeft
     */
    public void setNbCaseLeft(int NbCaseLeft) {
	this.NbCaseLeft = NbCaseLeft;
    }

    /**
     * get NbCaseLeft
     */
    public int getNbCaseLeft() {
	return NbCaseLeft;
    }

    /**
     * Set action
     */
    public void setAction(String action) {
	this.action = action;
    }

    /**
     * Get action
     */
    public String getAction() {
	return action;
    }

    /**
     * Get LastDirection
     */
    public String getLastDirection() {
	return lastDirection;
    }

    /**
     * Set LastDirection
     */
    public void setLastDirection(String lastDirection) {
	this.lastDirection = lastDirection;
    }

    /**
     * set LastGround
     */
    public void setLastGround(boolean lastGround) {
	this.lastGround= lastGround;
    }

   
    
    public String getOpposeLastDirection() {
        
        if (lastDirection.equals("R")) {
            return "G";
        }
            else
            return "R";
    }
    
    /**
     * get Lastground
     */
    public boolean getLastGround() {
	return lastGround;
    }

    /**
     *  @return boolean
     */
    public boolean hasPU() {
        return carte.hasPU();
    }
    
    /**
     *  @return boolean
     */
    public boolean hasCreek() {
        return carte.hasCreek();
    }
    
    /**
     *
     */
    public void changeCoord(EnumJSON req, EnumDirection direction){
        if (req == HEADING) {
            switch(direction) {
                case NORD:
                    point.setLocation(point.getX(), point.getY() + 1);
                    break;
                case SUD:
                    point.setLocation(point.getX(), point.getY() - 1);
                    break;
                case WEST:
                    point.setLocation(point.getX() - 1, point.getY());
                    break;
                case EST:
                    point.setLocation(point.getX() + 1, point.getY());
                    break;
            }
        }
        switch(this.direction) {
            case NORD:
                point.setLocation(point.getX(), point.getY() + 1);
                break;
            case SUD:
                point.setLocation(point.getX(), point.getY() - 1);
                break;
            case WEST:
                point.setLocation(point.getX() - 1, point.getY());
                break;
            case EST:
                point.setLocation(point.getX() + 1, point.getY());
                break;
        }
    }
    
    /**
     * get Point
     */
    public Point getPoint() {
        return point;
    }
    
    /**
     * Add a case to the map
     */
    public void addCase() {
        carte.addCase(point);
    }
    
    /**
     * Set a PU
     */
    public void setPU(String id) {
        carte.setPOI(new PU(id), point);
    }
    
    /**
     * Set a creek
     */
    public void setCreek(String id) {
        carte.setPOI(new Creek(id), point);
    }
    
    /**
     * get the budget
     */
    public int getBudget() {
        return budget;
    }
    
    /**
     * budget - x
     */
    public void subBudget(int x) {
        budget = budget - x;
    }

    /**
     * return the number of creeks
     *
     */
    public int getNbCreek() {
	return carte.getNbCreek();
    }

    public String getCaseCreek(){
        return carte.getCaseCreek().toString();
    }
}
