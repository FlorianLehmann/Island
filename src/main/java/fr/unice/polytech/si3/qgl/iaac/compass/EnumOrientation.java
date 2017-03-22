package fr.unice.polytech.si3.qgl.iaac.compass;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.checkerframework.checker.nullness.qual.Nullable;

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

    private String front, left, right, back;
    private static Map<String, EnumOrientation> map = new HashMap();

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
     *
     * @return left direction
     */
    public EnumOrientation left() {

        return getEnumDirection(left);

    }

    /**
     *
     * @return back direction
     */
    public EnumOrientation back() {
        return getEnumDirection(back);
    }

    /**
     *
     * @return right direction
     */
    public EnumOrientation right() {
        return getEnumDirection(right);
    }

    /**
     *
     * @return current direction
     */
    public EnumOrientation front() {

        return getEnumDirection(front);

    }

    /**
     *
     * @return EnumOrientation
     */
    @JsonCreator
    public static EnumOrientation getEnumDirection(String direction) {
        return map.get(direction);
    }

    @Override
    public String toString() {
        return front;
    }

}