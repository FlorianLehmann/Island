package sample.bot.drone;

import sample.bot.EnumDirection;
import sample.bot.EnumJSON;
import sample.bot.ReadJSON;
import sample.bot.carte.Carte;
import sample.bot.carte.poi.Creek;
import sample.bot.carte.poi.PU;
import sample.bot.carte.poi.ressource.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static sample.bot.EnumJSON.HEADING;


public class Drone {

    /**
     * Attributes
     */
    private Map<String, Res> res;

    private State state;
    private int budget;
    private int budgetInit;
    private int nbAllerRetour;
    private String action;
    private Carte carte;
    private EnumDirection direction;
    private Point point;
    private String lastDirection;
    private int CaseToTarget;
    private int NbCaseRight;
    private int NbCaseLeft;
    private boolean lastGround;
    private int nbMen;
    private boolean end;

    /**
     * default constructor
     */
    public Drone(EnumDirection direction, Carte carte) {
        this.carte = carte;
        nbMen = (int) ReadJSON.getInformations().get("men");
        //carte = new Carte();
        state = new State0();
        action = new String();
        nbAllerRetour = 0;
        end = false;
        budgetInit = (int) ReadJSON.getInformations().get("budget");
        budget = (int) ReadJSON.getInformations().get("budget");
        point = new Point(0, 0);
        this.lastDirection = "R"; // a suppr
        this.direction = direction;
        CaseToTarget = 0;
        NbCaseLeft = 0;
        NbCaseRight = 0;
        lastGround = true;
        res = new HashMap();
        res.put("FISH", new Fish());
        res.put("FLOWER", new Flower());
        res.put("FRUITS", new Fruits());
        res.put("FUR", new Fur());
        res.put("ORE", new Ore());
        res.put("QUARTZ", new Quartz());
        res.put("SUGAR_CANE", new SugarCane());
        res.put("WOOD", new Wood());
    }

    /**
     * Set the current state
     *
     * @param State state
     */
    public void setState(State state) {
        this.state = state;
    }

    public void setEnd() {
        end = true;
    }

    public boolean getEnd() {
        return end;
    }

    /**
     * get the current state
     */
    public State getState() {
        if (state instanceof State25)
            nbAllerRetour++;
        if (budget < 1000) {
            return new State11();
        }
        return state;
    }

    /**
     * @return if of the first crique
     */
    public String getACrique() {
        return carte.getCaseCreek();
    }

    /**
     * get Men
     */
    public int getNbMen() {
        return nbMen;
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
        this.lastGround = lastGround;
    }


    public String getOpposeLastDirection() {

        if (lastDirection.equals("R")) {
            return "G";
        } else
            return "R";
    }

    /**
     * get Lastground
     */
    public boolean getLastGround() {
        return lastGround;
    }

    /**
     * @return boolean
     */
    public boolean hasPU() {
        return carte.hasPU();
    }

    /**
     * @return boolean
     */
    public boolean hasCreek() {
        return carte.hasCreek();
    }

    /**
     *
     */
    public void changeCoord(EnumJSON req, EnumDirection direction) {
        if (req == HEADING) {
            switch (direction) {
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
        switch (this.direction) {
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


    public void setCoord() {

        this.point = carte.getCoordCreek();
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
     */
    public int getNbCreek() {
        return carte.getNbCreek();
    }

    public String getCaseCreek() {
        return carte.getCaseCreek().toString();
    }

    public boolean hasFR() {
        return res.get((String) ReadJSON.getContracts().get(0)).hasR();

    }

    public int getBudgetInit() {
        return budgetInit;
    }

    public int getNbAllerRetour() {
        return nbAllerRetour;
    }

    public void setNbAllerRetour(int nbAllerRetour) {
        this.nbAllerRetour = nbAllerRetour;
    }
}
