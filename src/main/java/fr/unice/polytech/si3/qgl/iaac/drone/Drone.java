package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;



public class Drone {

    /**
     *
     * Attributes
     *
     */
    private State state;
    private String action;
    private EnumDirection direction;
    //private EnumDirection lastDirection;
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
    public Drone(EnumDirection direction) {
	state = new State0();
	action = new String();
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

    public boolean hasPU() {
	return false; // A modif
    }

    public boolean hasCreek() {
	return false; // A modif
    }
}
