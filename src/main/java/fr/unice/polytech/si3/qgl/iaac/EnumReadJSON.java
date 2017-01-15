package fr.unice.polytech.si3.qgl.iaac;

/**
 * Created by florian on 15/01/2017.
 */
public enum EnumReadJSON {

    HEADING("heading"),
    BUDGET("budget"),
    CONTRACTS("contracts"),
    EXTRAS("extras"),
    RANGE("range"),
    FOUND("found"),
    CREEKS("creeks"),
    SITES("sites"),
    BIOMES("biomes"),
    AMOUNT("amount"),
    COST("cost"),
    RESOURCES("resources"),
    MEN("men");

    String word;

    /**
     * default constructor
     * @param word
     */
    EnumReadJSON(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
