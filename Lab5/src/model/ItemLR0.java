package model;

import java.util.ArrayList;
import java.util.List;

public class ItemLR0 {

    protected int dotPointer;
    private List<String> rule;
    private String symbol;

    public ItemLR0(Rule r) {
        //todo
    }

    public ItemLR0(String leftSide, String[] rightSide, int dotPointer) {
        //todo
    }

    public ItemLR0(ItemLR0 item) {
        //todo
    }


    @Override
    public String toString() {
        //todo
        return "";
    }


    public int getDotPointer() {
        return dotPointer;
    }

    boolean goTo() {
//        if (dotPointer >= rule.getRightHandSided.length) {
//            return false;
//        }
//        dotPointer++;
        return true;
    }

    String getCurrentTerminal() {
//        if(dotPointer == rightSide.length){
//            return null;
//        }
        return "";
    }

}
