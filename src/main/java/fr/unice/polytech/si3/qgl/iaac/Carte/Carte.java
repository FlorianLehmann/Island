package fr.unice.polytech.si3.qgl.iaac.Carte;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 06/02/2017.
 */
public class Carte {
    List<Case> carte;

    public Carte(){
        carte=new ArrayList<>();
    }

public void addCase(Point coords){
    for(Case i : carte){
        if(i.getCoords().getLocation().equals(new Point((coords.x)*3,(coords.y)*3)))return;
    }
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            carte.add(new Case(new Point((coords.x)*3+i,(coords.y)*3+j)));
        }
    }
}

public Case getCase(Point coords){
    for(Case i : carte){
        if(i.getCoords().getLocation().equals(new Point((coords.x)*3,(coords.y)*3)))return i;
    }
    return null;
}




}
