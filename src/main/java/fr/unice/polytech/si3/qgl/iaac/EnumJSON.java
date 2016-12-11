package fr.unice.polytech.si3.qgl.iaac;

public enum EnumJSON {

    SCAN( "{ \"action\": \"scan\" }","","" ),
    ECHO("{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" ,"", "\" } }"),
    FLY( "{ \"action\": \"fly\" }","", "" ),
    STOP( "{ \"action\": \"stop\" }","", ""),
    HEADING("{ \"action\": \"heading\", \"parameters\": { \"direction\":\"","", "\" } }"),
    LAND("{ \"action\": \"land\", \"parameters\": { \"creek\": \"","\", \"people\": "," }}");

    String debut, milieu, fin;

    /**
     *
     * default constructor of the enum
     *
     */
    EnumJSON(String debut,String milieu, String fin) {
	this.debut = debut;
	this.fin = fin;
    this.milieu=milieu;
    }

    /**
     *
     * Retourne la requete JSON en String
     * @return String
     */
    public String toString(String direction) {

	return debut + direction + fin;
	
    }

    public String toString(String parametre1,int parametre2){
        return debut + parametre1 + milieu + parametre2 + fin;
    }


		    
}
