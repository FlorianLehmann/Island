package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class DefineWay implements State {

    private boolean wayDefine;
    private Deque<String> stack;
    private Contracts contracts;



    public DefineWay() {
        wayDefine = false;
        stack = new ArrayDeque<>();
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        this.contracts=contracts;
        EnumOrientation direction;
        String tmp;
        int coordX;
        int coordY;
        Point point;

        while(!contracts.isPrimaryCompleted() && !(carte.hasResource(contracts.getContract().getName()))) {
            contracts.remove(contracts.getContract().getName());
        }
        if (!contracts.isPrimaryCompleted()) {
            if(!wayDefine) {
                tmp = contracts.getContract().getName().toString();
                if ("FISH".equals(tmp)) {
                    //point = carte.getACreek();
                    point = carte.getResource(contracts.getContract().getName());

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

            if (stack.size() == 0) {
                return men.explore();
            }
            else {
                return stack.pop();
            }

        } else {
            return men.explore();
        }


    }

    @Override
    public State wait(ReadJSON json) {
        if(contracts.isPrimaryCompleted())return new DefineWaySecond();
        if (stack.size() == 0)
            return new Explore();
        return this;
    }

}
