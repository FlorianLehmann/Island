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

    String front, left, right, back;
    static Map<String, EnumOrientation> map = new HashMap();

    /**
     *
     * initialize la hashmap
     *
     */
    static {
        Arrays.asList(EnumOrientation.values()).forEach(cw -> map.put(cw.front, cw));
    }

    /**
     * Constructeur de l'enum
     * @param front
     * @param left
     * @param right
     * @param back
     */
    EnumOrientation(String front, String left, String right, String back) {

        this.front = front;
        this.left = left;
        this.right = right;
        this.back = back;

    }
    

    /**
     * Donne la direction pour tourner à gauche
     *
     * @return String left
     */
    public EnumOrientation left() {

        return getEnumDirection(left);

    }

    public EnumOrientation back() {
        return getEnumDirection(back);
    }

    /**
     * Donne la direction pour tourner à droite
     *
     * @return String right
     */
    public EnumOrientation right() {

        return getEnumDirection(right);
    }

    /**
     * Donne la direction courante
     *
     * @return String direction
     */
    public EnumOrientation front() {

        return getEnumDirection(front);

    }

    /**
     * Retourne l'enum direction associe
     *
     * @return EnumOrientation
     */
    public static EnumOrientation getEnumDirection(String direction) {
        return map.get(direction);

    }

    @Override
    public String toString() {
        return front;
    }

}