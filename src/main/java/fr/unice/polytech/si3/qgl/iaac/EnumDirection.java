package fr.unice.polytech.si3.qgl.iaac;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public enum EnumDirection {

    /**
     * Facing direction ( Direction, left, right, back)
     */

    NORD("N", "W", "E", "S"),
    SUD("S", "E", "W", "N"),
    WEST("W", "S", "N", "E"),
    EST("E", "N", "S", "W");

    String direction, left, right, back;
    static Map<String, EnumDirection> map = new HashMap();

    /**
     *
     * initialize la hashmap
     *
     */
    static {
        Arrays.asList(EnumDirection.values()).forEach(cw -> map.put(cw.direction, cw));
    }

    /**
     * Constructeur de l'enum
     */
    EnumDirection(String direction, String left, String right, String back) {

        this.direction = direction;
        this.left = left;
        this.right = right;
        this.back = back;

    }

    /**
     * Donne la direction courante
     *
     * @return String front
     */
    public String front() {
        return direction;

    }

    /**
     * Donne la direction pour tourner à gauche
     *
     * @return String left
     */
    public String left() {

        return left;

    }

    public String back() {
        return back;
    }

    /**
     * Donne la direction pour tourner à droite
     *
     * @return String right
     */
    public String right() {

        return right;
    }

    /**
     * Donne la direction courante
     *
     * @return String direction
     */
    public String direction() {

        return direction;

    }

    /**
     * Retourne l'enum direction associe
     *
     * @return EnumDirection
     */
    public static EnumDirection getEnumDirection(String direction) {
        return map.get(direction);

    }

    /**
     *
     */
    public static EnumDirection getOppose(EnumDirection direction) {
        return EnumDirection.getEnumDirection(direction.back());
    }


}
