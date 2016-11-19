package fr.unice.polytech.si3.qgl.iaac;
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
    private Map map = new Map();
    private String idCrique;
    private String idPU;
    private String lastDirection;
    private boolean lastGround = true;

    public Drone(){
        direction = new String();
        result = new String();
        action = new String();
    }

    public Drone(String direction){
        this.direction=direction;
        this.etat=0;
        this.lastDirection="R";
        result = new String();
        action = new String();
    }

    public Drone(String direction, String result, int nbCase){
        this.direction=direction;
        this.nbCase=nbCase;
        this.result=result;
        this.etat=0;
        this.lastDirection="R";
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

     bre
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
            etat = 5;
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
            etat = 7;
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
                direction = left(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
                etat = 1;
            } else {
                direction = right(direction);
                action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
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
            //action = "{ \"action\": \"stop\" }";
            etat=0;
            return true;
        }
        return false;
    }

    //=====================================================================

    private String oppose(String direction){
        if(direction.equals("R")){ return "G";}
        else{ return "R";}
    }

    public void setIdCrique(String idCrique){
        this.idCrique=idCrique;
    }

    public void setIdPU(String idPU){
        this.idPU=idPU;
    }


    public boolean piEtat0(){
        action = "{ \"action\": \"scan\" }";
        etat=1;
        return false;
    }
    public void piEtat1(){
        if(idCrique!=null && idPU!=null)etat=100;
        else{
            etat=2;
        }
    }
    public boolean piEtat2(){
        action = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
        etat=3;
        return false;
    }
    public void piEtat3(){
        if(result.equals("GROUND") || lastGround ) {
            etat=4;
            if( !result.equals("GROUND") )
            {lastGround = false;}
            if( result.equals("GROUND") )
            {lastGround = true;}
        
        }
        else{
            etat=5;
        }
        
    }
    public boolean piEtat4(){
        action = "{ \"action\": \"fly\" }";
        etat=0;
        return false;
    }
    public boolean piEtat100(){
        action = "{ \"action\": \"stop\" }";
        return true;
    }

    public boolean piEtat10(){
        action = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
        etat=11;
        return false;
    }
    public void piEtat11(){
        if(result.equals("GROUND")){
            nbCaseFace=nbCase;
            etat=12;
        }
        else{
            etat=13;
        }
    }
    public boolean piEtat12(){
        
        action = "{ \"action\": \"fly\" }";
        nbCaseFace--;
        if (nbCaseFace <= 0) {
            etat = 0;
        }
        return false;
    }


    public boolean parcourirIle(){
        if(etat==0){piEtat0(); return false;}
        if(etat==1){piEtat1();}
        if(etat==2){piEtat2(); return false;}
        if(etat==3){piEtat3();}
        if(etat==4){piEtat4();return false;}
        if(etat==5){piEtat5(); return false;}
        if(etat==6){piEtat6();}
        if(etat==7){piEtat7(); return false;}
        if(etat==8){piEtat8(); return false;}
        if(etat==9){piEtat9(); return false;}
        if(etat==10){piEtat10(); return false;}
        if(etat==11){piEtat11(); }
        if(etat==12){piEtat12(); return false;}
        if(etat==13){piEtat13(); return false;}
        if(etat==14){piEtat14(); return false;}
        if(etat==15){piEtat15(); return false;}
        if(etat==16){piEtat16(); return false;}
        if(etat==17){piEtat17();return false;}
        if(etat==100){return piEtat100();}
        return false;
    }


    public boolean piEtat5(){
        String opposeLastDirection = oppose(lastDirection);
        String direction1;
        if(oppose(lastDirection).equals("R"))
        {
            direction1 = left(direction);
            //lastDirection = "R";
        }
        else
        {
            direction1 = right(direction);
            //lastDirection = "G";
        }
            
        //String opposeLastDirection = oppose(lastDirection);
        action = "{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }";
        etat=6;
        return false;
    }

    public void piEtat6(){
        if (result.equals("GROUND") && nbCase<3) {
            etat = 7;
        } else {
            etat = 8;
        }
    }

    public boolean piEtat7(){
        action = "{ \"action\": \"fly\" }";
        etat=5;
        return false;
    }

    public boolean piEtat8(){
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
        etat=9;
        return false;
    }

    public boolean piEtat9(){
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
        etat=10;
        lastDirection=oppose(lastDirection);
        return false;
    }

    public boolean piEtat13(){
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" +direction + "\" } }";
        etat=14;
        return false;
    }

    public boolean piEtat14(){
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
        etat=15;
        return false;
    }

    public boolean piEtat15(){
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
        etat=16;
        return false;
    }

    public boolean piEtat16(){
        action = "{ \"action\": \"fly\" }";
        etat=17;
        return false;
    }

    public boolean piEtat17() {
        if (lastDirection.equals("R")){
            direction =right(direction);}
        else direction=left(direction);
        action = "{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction + "\" } }";
        etat = 10;
        lastDirection = oppose(direction);
        return false;
    }
    
    public String getIdCrique(){return idCrique;}
    
    public String getIdPU(){return idPU;}

    //Pour Test
    public int getEtat(){return etat;}
}
