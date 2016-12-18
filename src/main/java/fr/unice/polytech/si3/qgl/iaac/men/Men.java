package sample.bot.men;

import sample.bot.EnumDirection;
import sample.bot.carte.Carte;

import sample.bot.carte.poi.ressource.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class Men {

    private State state;
    private Carte carte;
    private static int budget;
    private Point point;
    private String action;
    private Map<String, Res> res;
    /**
     * default constructor
     *
     */
    public Men(Carte carte, Point point) {
        
        this.carte = carte;
        state = new State0();
        this.point = new Point((int)point.getX(), (int)point.getY());
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
    
    public State getState() {
        if (budget < 1000) {
            return new State11();
        }
        return state;
    }

    public Point getACrique() {
        return carte.getCoordCreek();
    }


    public void setAction(String tmp) {
        action = tmp;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setState(State state) {
        
        this.state = state;
    }

    public Point getPoint() {
        
        return point;
    }
    
    public Res getRessource(String tmp) {
        
        return res.get(tmp);
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void subBudget(int cost) {
        budget = budget -cost;
    }

    public void setCoord(EnumDirection direc) {
        switch (direc) {
            case NORD:
                point.setLocation(point.getX(), point.getY() + 1);
                break;
            case EST:
                point.setLocation(point.getX()+1, point.getY());
                break;
            case SUD:
                point.setLocation(point.getX(), point.getY() - 1);
                break;
            case WEST:
                point.setLocation(point.getX()-1, point.getY());
                break;
        }
    }
}
