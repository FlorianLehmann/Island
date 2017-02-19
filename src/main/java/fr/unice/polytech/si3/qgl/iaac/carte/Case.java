package fr.unice.polytech.si3.qgl.iaac.carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 06/02/2017.
 */
public class Case {

    //// TODO: 13/02/2017
    private static final Logger logger = LogManager.getLogger(Carte.class);
    //comment
    //always use List instead of ArrayList or LinkedList
    private Point coords;
    private List<EnumBiome> biomes;
    private String idCreek;
    private String idPu;
    private List<EnumResources> ressources;
    private List<Integer> nbRessources;

    public Case(Point coords){
        this.coords=coords;
        biomes=new ArrayList<>();
    }

    public Point getCoords(){
        return new Point(coords.x,coords.y);
    }

    public void updateBiomes(ReadJSON json){
        if (json.getBiomes() != null)
            biomes=json.getBiomes();
    }

    public void updateCreek(ReadJSON json){
        if (json.getCreekID() != null)
            idCreek=json.getCreekID();
    }

    public void updatePu(ReadJSON json){
        idPu=json.getSiteID();
    }

    /*public void updateRessources(ReadJSON json){
        ressources=json.getResources();
    }

    public void updateNbRessources(ReadJSON json){
        nbRessources=json.getNbRessources();
    }*/

    public void removeNbRessource(EnumResources item, int nb){
        for(int i=0;i<ressources.size();i++){
            if(ressources.get(i).equals(item))nbRessources.set(i,nbRessources.get(i)-nb);
        }
    }

    public void update(ReadJSON json) {
        updateBiomes(json);
        updateCreek(json);
        updatePu(json);
    }

    public boolean hasCreek() {
        return idCreek != null;
    }

    public boolean containsResource(EnumResources name) {
        List<EnumResources>  resource;
        for (int i = 0; i < biomes.size(); i++) {
            resource = biomes.get(i).getResources();
            logger.info("resource" + biomes.get(i).getResources().size() + biomes.get(i));
            for (int j = 0; j < resource.size(); j++) {
                logger.info("resource" + resource.size() + biomes.get(i) +"j" + j);
                //logger.info("Carte: hasResource looking for" +name +" " + true);
                if (resource.get(j).equals(name))
                    return true;
            }
        }
        return false;
    }

    public String getIdCreek(){
        return new String(idCreek);
    }
}
//le drone s'arrête quand il y a un site et non une crique