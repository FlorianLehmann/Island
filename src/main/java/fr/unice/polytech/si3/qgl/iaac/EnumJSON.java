package sample.bot;

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
    EXPLOIT("{ \"action\": \"exploit\", \"parameters\": { \"resource\": \"", "", "\" }}");

    String debut, milieu, fin;


    /**
     * default constructor of the enum
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

    public String toString(String parametre1, int parametre2) {
        return debut + parametre1 + milieu + parametre2 + fin;
    }


}
