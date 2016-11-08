import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seb on 08/11/16.
 */
public class Map {
    private static List<Case> map=new ArrayList<Case>();

    public void addCase(Point coords){
        map.add(new Case(coords));
    }

    public List getCaseRessource(String ressource){
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

    public List getcreek(){
        List<Case> cases=new ArrayList<Case>();
        for(int i=0;i<map.size();i++){
            if(map.get(i).creek()==true){
                cases.add(map.get(i));
            }
        }
        return cases;
    }

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
                map.get(i).setRessources(ressourcesq);
            }
        }
    }


    public void setCreek(Point coords){
        for(int i=0;i<map.size();i++){
            if(map.get(i).getCoords().equals(coords)){
                map.get(i).creek();
                break;
            }
        }
    }

    public Case getNearestCreek(){
        List<Case> creeks=getcreek();
        double distance=0;
        Case nearest=null;
        int a=getEmergency().getCoords().x;
        int b=getEmergency().getCoords().y;
        for(int i=0;i<creeks.size();i++){
            int x=creeks.get(i).getCoords().x;
            int y=creeks.get(i).getCoords().y;
            double d=Math.sqrt((x-a)*(x-a)+(y-b)*(y-b));
            if(d<distance){
                distance=d;
                nearest=creeks.get(i);
            }


        }
        return nearest;
    }
}
