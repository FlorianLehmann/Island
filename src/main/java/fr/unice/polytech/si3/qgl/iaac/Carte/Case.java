package fr.unice.polytech.si3.qgl.iaac.Carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sebde on 06/02/2017.
 */
public class Case {

    private Point coords;
    private ArrayList biomes;
    private String idCreek="";
    private String idPu="";
    private ArrayList<EnumResources> ressources;
    private ArrayList<Integer> nbRessources;

    public Case(Point coords){
        this.coords=coords;
        biomes=new ArrayList();
    }

    public Point getCoords(){
        return new Point(coords.x,coords.y);
    }

    public void updateBiomes(ReadJSON json){
        biomes=json.getBiomes();
    }

    public void updateCreek(ReadJSON json){
        idCreek=json.getCreek();
    }

    public void updatePu(ReadJSON json){
        idPu=json.getPu();
    }

    public void updateRessources(ReadJSON json){
        ressources=json.getResources();
    }

    public void updateNbRessources(ReadJSON json){
        nbRessources=json.getNbRessources();
    }

    public void removeNbRessource(EnumResources item, int nb){
        for(int i=0;i<ressources.size();i++){
            if(ressources.get(i).equals(item))nbRessources.set(i,nbRessources.get(i)-nb);
        }
    }

}
