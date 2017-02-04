package fr.unice.polytech.si3.qgl.iaac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum EnumOrientation {

    /**
     * Facing direction ( Direction, left, right, back)
     */

    NORTH("N", "W", "E", "S"),
    SOUTH("S", "E", "W", "N"),
    WEST("W", "S", "N", "E"),
    EST("E", "N", "S", "W");

    String direction, left, right, back;
    static Map<String, EnumOrientation> map = new HashMap();

    /**
     *
     * initialize la hashmap
     *
     */
    static {
        Arrays.asList(EnumOrientation.values()).forEach(cw -> map.put(cw.direction, cw));
    }

    /**
     * Constructeur de l'enum
     * @param direction
     * @param left
     * @param right
     * @param back
     */
    EnumOrientation(String direction, String left, String right, String back) {

        this.direction = direction;
        this.left = left;
        this.right = right;
        this.back = back;

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
     * @return EnumOrientation
     */
    public static EnumOrientation getEnumDirection(String direction) {
        return map.get(direction);

    }

    /**
     * Donne la direction opposée
     * @param direction
     * @return
     */
    public static EnumOrientation getOppose(EnumOrientation direction) {
        return EnumOrientation.getEnumDirection(direction.back());
    }


}