package fr.unice.polytech.si3.qgl.iaac.map;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome.OCEAN;

/**
 * Created by sebde on 06/02/2017.
 */
public class Case {


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

    public Case(Case tile){
        this.coords = tile.coords;
        this.biomes = tile.biomes;
        this.idCreek = tile.idCreek;
        this.idPu = tile.idPu;
        this.ressources = tile.ressources;
        this.nbRessources = tile.nbRessources;
    }

    public Point getCoords(){
        return new Point(coords.x,coords.y);
    }

    public void updateBiomes(ReadJSON2 json){
        if (json.getAnswer().getBiomes() != null)
            biomes=json.getAnswer().getBiomes();
    }

    public void updateCreek(ReadJSON2 json){
        if (json.getAnswer().getCreeks() != null)
            idCreek=json.getAnswer().getCreeks();
    }

    public void updatePu(ReadJSON2 json){
        if (json.getAnswer().getSites() != null)
            idPu=json.getAnswer().getSites();
    }

    /*public void updateRessources(ReadJSON2 json){
        ressources=json.getResources();
    }

    public void updateNbRessources(ReadJSON2 json){
        nbRessources=json.getNbRessources();
    }*/

    public void removeNbRessource(EnumResources item, int nb){
        for(int i=0;i<ressources.size();i++){
            if(ressources.get(i).equals(item))
                nbRessources.set(i,nbRessources.get(i)-nb);
        }
    }

    public void removeResource(EnumResources resource){
        for(int i=0;i<ressources.size();i++){
            if(ressources.get(i).equals(resource)) {
                nbRessources.remove(i);
                break;
            }
        }
    }

    public void update(ReadJSON2 json) {
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
            for (int j = 0; j < resource.size(); j++) {
                if (resource.get(j).equals(name))
                    return true;
            }
        }
        return false;
    }

    public String getIdCreek(){
        return new String(idCreek);
    }

    public int containsGround() {
        for (int i = 0; i < biomes.size(); i++) {
            if (biomes.get(i) != OCEAN)
                return 1;
        }
        return 0;
    }

    public boolean containOcean() {
        for (int i = 0; i < biomes.size(); i++) {
            if (biomes.get(i) != OCEAN)
                return false;
        }
        return true;
    }
}
//le drone s'arrête quand il y a un site et non une crique