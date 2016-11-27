package fr.unice.polytech.si3.qgl.iaac;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seb on 08/11/16.
 */
public class Case {
    protected Point coords;
    protected List<String> biomes=new ArrayList<String>();
    protected List<String> ressources=new ArrayList<String>();
    protected List<String> ressources_quantity=new ArrayList<String>();
    private List<MiniCase>   miniCase=new ArrayList<MiniCase>();
    protected boolean creek=false;
    protected boolean pu=false;
    String idcreek;
    String idpu;


    public Case(Point coords){
        this.coords=coords;
        /*
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                miniCase.add(new MiniCase(new Point(i,j)));
            }
        }*/

    }
    public Case(){

    }


    public Point getCoords(){
        return coords;
    }


    public List getBiomes(){
        return biomes;
    }
    public List getRessources(){
        return ressources;
    }
    public List getRessourcesQ(){
        return ressources_quantity;
    }

    public boolean creek(){
        return creek;
    }
    public String getIdcreek() {return idcreek;}

    public String getIdpu() {return getIdpu();}
    public boolean pu(){
        return pu;
    }


    public void setBiomes(ArrayList<String> biomes) {
        for (int i = 0; i < biomes.size(); i++){
            this.biomes.add(biomes.get(i));
        }
    }
    public void setBiomes(String biome){
        biomes.add(biome);
    }
    public void setRessources(ArrayList<String> ressources){
        for (int i=0;i<ressources.size();i++){
            this.ressources.add(ressources.get(i));
        }
    }
    public void setRessources(String ressource){
        ressources.add(ressource);
    }
    public void setRessources_quantity(ArrayList<String> ressources_quantity){
        for (int i=0;i<ressources_quantity.size();i++){
            this.ressources_quantity.add(ressources_quantity.get(i));
        }
    }
    public void setRessources_quantity(String ressource_quantity){
        ressources_quantity.add(ressource_quantity);
    }

    public void setcreek(){creek=true;}

    public void setpu(){pu=true;}

    public void setIdCreek(String idcreek){this.idcreek=idcreek;}

    public void setIdPu(String idpu){this.idpu=idpu;}

}
