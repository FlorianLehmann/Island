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
    public void setInfoCase(Point coord,String info){

    }
    public void setInfoCase(Point coord, ArrayList<String> info){

    }
}
