/**
 * Created by seb on 08/11/16 .
 */
public class Drone {

    private String direction;
    private String result;
    private int nbCase;
    private int etat;
    private String action ;
    private int nbCaseFace;
    private int nbCaseLeft;
    private int nbCaseRight;

    public Drone(){
	direction = new String();
	result = new String();
	action = new String();
    }

    public Drone(String direction){
        this.direction=direction;
        this.etat=0;
	result = new String();
	action = new String();
    }

    public Drone(String direction, String result, int nbCase){
        this.direction=direction;
        this.nbCase=nbCase;
        this.result=result;
        this.etat=0;
    }

    public String left(String direction){
        if(direction.equals("N")){ return "W";}
        else if (direction.equals("S")){ return "E";}
        else if(direction.equals("E")){ return "N";}
        else{ return "S";}
    }

    public String right(String direction){
        if(direction.equals("N")){ return "E";}
        else if (direction.equals("S")){ return "W";}
        else if(direction.equals("E")){ return "S";}
        else{ return "N";}
    }

    /*public boolean findIsland(){

        switch (etat){

            case 0:
                action ="{ \"action\": \"scan\" }" ;
                etat=1;
                break;
            case 1:
                if (result.equals("GROUND")){ etat=10;}
                else {etat=2;}


            case 2:
                action = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+direction+"\" } }";
                etat=3;
                break;

            case 3:
                if(result.equals("GROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else {
                    if (nbCase == 0) {
                        etat = 10;
                    }
                    else {
                        etat = 4;
                    }
                }

            case 4:
                String directionLeft=left(direction);
                action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\""+directionLeft+"\" } }";
                break;

            case 5:
                if(result.equals("GROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else{
                    etat=6;
                    nbCaseLeft=nbCase;
                }


            case 6:
                String directionRight= right(direction);
                action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\""+directionRight+"\" } }";
                break;

            case 7:
                if(result.equals("GROUND")){
                    etat=9;
                    nbCaseFace=nbCase;
                }
                else{
                    etat=8;
                    nbCaseRight=nbCase;
                }

            case 8:
                if(nbCaseLeft>nbCaseRight){
                String directionLeft2=left(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\""+directionLeft2+"\" } }";
                etat=1;
                }
                else{
                String directionRight2=right(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\""+directionRight2+"\" } }";
                etat=1;
                }
                break;

            case 9:
                action= "{ \"action\": \"fly\" }";
                nbCaseFace--;
                if(nbCaseFace==0){etat=10;}
                break;

            case 10:
                action="{ \"action\": \"stop\" }";
                return true;
        }
        return false;

    }*/

    public void setNbCase(int nbCase){
        this.nbCase=nbCase;
    }

    public void setResult(String result){
        this.result=result;
    }

    public String getAction(){return this.action;}

    public boolean findIsland() {

        if (etat == 0) {
            action = "{ \"action\": \"scan\" }";
            etat = 1;
            return false;
        }

        if (etat == 1) {
            if (result.equals("GROUND")) {
                etat = 10;
            } else {
                etat = 2;
            }
        }

        if (etat == 2) {
            action = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
            etat = 3;
            return false;
        }

        if (etat == 3) {
            if (result.equals("GROUND")) {
                etat = 9;
                nbCaseFace = nbCase;
            } else {
                if (nbCase == 0) {
                    etat = 10;
                } else {
                    etat = 4;
                }
            }
        }

        if (etat == 4) {
            String directionLeft = left(direction);
            action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" + directionLeft + "\" } }";
            return false;
        }

        if (etat == 5) {
            if (result.equals("GROUND")) {
                etat = 9;
                nbCaseFace = nbCase;
            } else {
                etat = 6;
                nbCaseLeft = nbCase;
            }
        }

        if (etat == 6) {
            String directionRight = right(direction);
            action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" + directionRight + "\" } }";
            return false;
        }

        if (etat == 7) {
            if (result.equals("GROUND")) {
                etat = 9;
                nbCaseFace = nbCase;
            } else {
                etat = 8;
                nbCaseRight = nbCase;
            }
        }

        if (etat == 8) {
            if (nbCaseLeft > nbCaseRight) {
                String directionLeft2 = left(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + directionLeft2 + "\" } }";
                etat = 1;
            } else {
                String directionRight2 = right(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + directionRight2 + "\" } }";
                etat = 1;
            }
            return false;
        }

        if (etat == 9) {
            action = "{ \"action\": \"fly\" }";
            nbCaseFace--;
            if (nbCaseFace == 0) {
                etat = 10;
            }
            return false;
        }

        if (etat == 10) {
            action = "{ \"action\": \"stop\" }";
            return true;
        }
    return false;
    }

}
