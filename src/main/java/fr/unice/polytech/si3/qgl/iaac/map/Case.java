package fr.unice.polytech.si3.qgl.iaac.map;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
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

    public boolean containOcean() {
        for (int i = 0; i < biomes.size(); i++) {
            if (biomes.get(i) != OCEAN)
                return false;
        }
        return true;
    }
}