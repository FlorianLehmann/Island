package fr.unice.polytech.si3.qgl.iaac.json;

/**
 * Created by Quentin on 03/02/2017.
 */
public enum EnumJSON {

    SCAN("{ \"action\": \"scan\" }", "", ""),
    ECHO("{ \"action\": \"echo\", \"parameters\": { \"direction\":\"", "", "\" } }"),
    FLY("{ \"action\": \"fly\" }", "", ""),
    STOP("{ \"action\": \"stop\" }", "", ""),
    HEADING("{ \"action\": \"heading\", \"parameters\": { \"direction\":\"", "", "\" } }"),
    LAND("{ \"action\": \"land\", \"parameters\": { \"creek\": \"", "\", \"people\": ", " }}"),
    MOVETO("{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"", "", "\" } }"),
    SCOUT("{ \"action\": \"scout\", \"parameters\": { \"direction\": \"", "", "\" } }"),
    GLIMPSE("{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"", "\", \"range\": ", " } }"),
    EXPLORE("{ \"action\": \"explore\" }", "", ""),
    EXPLOIT("{ \"action\": \"exploit\", \"parameters\": { \"resource\": \"", "", "\" }}"),
    TRANSFORM("{ \"action\": \"transform\", \"parameters\": { \"","\": "," }}");

    String debut, milieu, fin;


    /**
     * default constructor
     * @param debut
     * @param milieu
     * @param fin
     */
    EnumJSON(String debut, String milieu, String fin) {
        this.debut = debut;
        this.fin = fin;
        this.milieu = milieu;
    }

    /**
     * Retourne la requete JSON en String
     *
     * @return String
     */
    public String toString(String direction) {

        return debut + direction + fin;

    }

    /**
     *
     * @param parametre1
     * @param parametre2
     * @return
     */
    public String toString(String parametre1, int parametre2) {
        return debut + parametre1 + milieu + parametre2 + fin;
    }

    //TODO
    public String toStringTranform(String ressource1,String ressource2,int nbRessource1,int nbRessource2){
        return debut+ressource1+milieu+nbRessource1+", \""+ressource2+milieu+nbRessource2+fin;
    }

}
