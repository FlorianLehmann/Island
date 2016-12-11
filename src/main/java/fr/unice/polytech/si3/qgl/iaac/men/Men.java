package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;

import fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class Men {

    private State state;
    private Carte carte;
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
        this.point = point;
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
        /*if (budget < 21) {
            return new State11();
        }*/
        return state;
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
}
