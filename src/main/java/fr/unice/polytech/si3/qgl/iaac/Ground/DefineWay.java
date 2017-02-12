package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.Stop;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lehmann on 12/02/17.
 */
public class DefineWay implements State{

    private boolean wayDefine;
    private Deque<String> stack;

    public DefineWay() {
        wayDefine = false;
        stack = new ArrayDeque<>();
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        EnumOrientation direction = EnumOrientation.NORTH;
        String tmp;
        int coordX;
        int coordY;
        Point point;
        while(!contracts.isCompleted() && !(carte.hasResource(contracts.getContract().getName()/*recevoir un contrat*/))) {
            contracts.remove(contracts.getContract().getName());
        }
        if (!contracts.isCompleted()) {
            if(!wayDefine) {
                tmp = contracts.getContract().getName().toString();
                if ("FISH".equals(tmp)) {
                    point = carte.getACreek();
                } else {
                    point = carte.getResource(contracts.getContract().getName());
                }
                coordX = point.x - men.getCoord().x;
                coordY = point.y - men.getCoord().y;

                if (coordX < 0)
                    direction = EnumOrientation.WEST;
                else
                    direction = EnumOrientation.EST;

                for (int i = 0; i < Math.abs(coordX) ; i++) {
                    stack.push(men.moveTo(direction.front()));
                }
                if (coordY < 0)
                    direction = EnumOrientation.SOUTH;
                else
                    direction = EnumOrientation.NORTH;
                for (int i = 0; i < Math.abs(coordY); i++) {
                    stack.push(men.moveTo(direction.front()));
                }
                wayDefine = true;
            }
            if (stack.isEmpty()) {
                return men.explore();
            }
            else {
                return stack.pop();
            }
        } else {
            return EnumJSON.STOP.toString();
        }
    }

    @Override
    public State wait(ReadJSON json) {
        if (stack.isEmpty())
            return new Explore();
        return this;
    }

}
