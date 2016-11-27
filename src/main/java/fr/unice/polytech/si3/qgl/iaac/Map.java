package fr.unice.polytech.si3.qgl.iaac;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seb on 08/11/16.
 */
public class Map {
    private static List<Case> map=new ArrayList<Case>();

    public boolean addCase(Point coords){
        for(int i=0;i<map.size();i++){
            if(coords.equals(map.get(i).getCoords()))return false;
        }
        map.add(new Case(coords));
        return true;
    }

    public int sizemap(){return map.size();}

    public List<Case> getCaseRessource(String ressource){
        List<Case> cases=new ArrayList<Case>();
        for(int i=0;i<map.size();i++){
            for(int j=0;j<map.get(i).getRessources().size();j++){
                if(map.get(i).getRessources().get(j).equals(ressource)){
                    cases.add(map.get(i));
                }
            }
        }
        return cases;
    }

    /**
     * Retourne la liste des cases contenant une crique
     * @return List
     */

    public List getcreek(){
        List<Case> cases=new ArrayList<Case>();
        for(int i=0;i<map.size();i++){
            if(map.get(i).creek()==true){
                cases.add(map.get(i));
            }
        }
        return cases;
    }

    /**
     * Retourne la Case contenant le point d'urgence
     * @return Case
     */
    public Case getEmergency(){
        for(int i=0;i<map.size();i++){
            if(map.get(i).pu()==true){
                return map.get(i);
            }
        }
        return null;
    }
    public void setBiomeCase(Point coords,String biome){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setBiomes(biome);
            }
        }

    }
    public void setBiomeCase(Point coords,ArrayList<String> biomes){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setBiomes(biomes);
            }
        }
    }

    public void setRessourceCase(Point coords,String ressource){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setRessources(ressource);
            }
        }

    }
    public void setRessourceCase(Point coords,ArrayList<String> ressources){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setRessources(ressources);
            }
        }
    }

    public void setRessourcesQCase(Point coords,String quantity){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setRessources_quantity(quantity);
            }
        }

    }
    public void setRessourcesQCase(Point coords,ArrayList<String> ressourcesq){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setRessources_quantity(ressourcesq);
            }
        }
    }

    public List getBiomes(Point coords){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                return map.get(i).getBiomes();
            }
        }
        return null;
    }
    public List getRessource(Point coords){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                return map.get(i).getRessources();
            }
        }
        return null;
    }
    public List getRessourceq(Point coords){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                return map.get(i).getRessourcesQ();
            }
        }
        return null;
    }

    /**
     * stocke l'id de la crique dans la case
     * @param coords
     * @param idcreek
     */
    public void setCreek(Point coords,String idcreek){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setcreek();
                map.get(i).setIdCreek(idcreek);
                break;
            }
        }
    }

    /**
     * Stocke l'id du point d'urgence dans la case.
     * @param coords
     * @param idpu
     */
    public void setPu(Point coords,String idpu){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).setpu();
                map.get(i).setIdPu(idpu);
                break;
            }
        }
    }

    /**
     * retourne l'id du point d'urgence
     * @return
     */
    public String getIdPu(){
        for(int i=0;i<map.size();i++){
            if(map.get(i).pu()==true){
                return getIdPu();
            }
        }
        return null;
    }

    /**
     * Renvoie la crique la plus proche du point d'urgence
     * @return
     */
    public String getNearestCreek(){
        List<Case> creeks=getcreek();
        int x=creeks.get(0).getCoords().x;
        int y=creeks.get(0).getCoords().y;
        int a=getEmergency().getCoords().x;
        int b=getEmergency().getCoords().y;
        double distance=Math.sqrt((x-a)*(x-a)+(y-b)*(y-b));
        Case nearest=creeks.get(0);
        for(int i=1;i<creeks.size();i++){
            x=creeks.get(i).getCoords().x;
            y=creeks.get(i).getCoords().y;
            double d=Math.sqrt((x-a)*(x-a)+(y-b)*(y-b));
            if(d<distance){
                distance=d;
                nearest=creeks.get(i);
            }


        }
        return nearest.getIdcreek();
    }
}
