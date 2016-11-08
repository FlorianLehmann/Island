/**
 * Created by seb on 08/11/16 .
 */
public class Drone {
    private  String direction;
    private  String result;
    private  int nbCase;
    private int etat;
    private String action ;
    private int nbCaseFace;
    private int nbCaseLeft;
    private int nbCaseRight;

    public Drone(String direction1, String result, int nbCase){
        direction=direction1;
        this.nbCase=nbCase;
        this.result=result;
        this.etat=0;
    }

    public String left(String direction){
        if(direction.equals("N")){ return "E";}
        else if (direction.equals("S")){ return "W";}
        else if(direction.equals("E")){ return "S";}
        else{ return "N";}
    }

    public String right(String direction){
        if(direction.equals("N")){ return "W";}
        else if (direction.equals("S")){ return "E";}
        else if(direction.equals("E")){ return "N";}
        else{ return "S";}
    }

    public boolean findIsland(){
        switch (etat){
            case 0: action ="{ \"action\": \"scan\" }" ;
                if (result.equals("GROUND")){ etat=10;}
                else {etat=1;}
                break;
            case 1: action = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+direction+"\" } }";
                if(result.equals("CROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else{etat=2;}
                break;
            case 2: String directionLeft=left(direction);
                action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\""+directionLeft+"\" } }";
                if(result.equals("CROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else{
                    etat=3;
                    nbCaseLeft=nbCase;
                }
                break;
            case 3: String directionRight= right(direction);
                action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\""+directionRight+"\" } }";
                if(result.equals("CROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else{
                    etat=4;
                    nbCaseRight=nbCase;
                }
                break;
            case 4: if(nbCaseLeft>nbCaseRight){
                String directionLeft2=left(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\""+directionLeft2+"\" } }";
                etat=1;
                }
                else{
                String directionRight2=right(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\""+directionRight2+"\" } }";
                etat=1;
                }
            case 9: action= "{ \"action\": \"fly\" }";
                nbCaseFace--;
                if(nbCaseFace==0){etat=10;}
                break;
            case 10: action="{ \"action\": \"stop\" }";
                return true;
        }
        return false;

    }

    public void setNbCase(int nbCase){
        this.nbCase=nbCase;
    }

    public void setResult(String result){
        this.result=result;
    }
}
