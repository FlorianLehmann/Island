package fr.unice.polytech.si3.qgl.iaac;

public enum EnumJSON {

    SCAN( "{ \"action\": \"scan\" }","" ),
    ECHO("{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" , "\" } }"),
    FLY( "{ \"action\": \"fly\" }", "" ),
    STOP( "{ \"action\": \"stop\" }", ""),
    HEADING("{ \"action\": \"heading\", \"parameters\": { \"direction\":\"", "\" } }");

    String debut, fin;

    /**
     *
     * default constructor of the enum
     *
     */
    EnumJSON(String debut, String fin) {
	this.debut = debut;
	this.fin = fin;
    }

    /**
     *
     * Retourne la requete JSON en String
     * @return String
     */
    public String toString(String direction) {

	return debut + direction + fin;
	
    }



		    
}
