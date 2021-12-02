package model;

import java.util.ArrayList;
import java.util.List;

public class ItemLR0 {

    private int dotPointer;
    private Rule rule;
    private String symbol;

    public ItemLR0(int dotPointer){
        this.dotPointer = dotPointer;
    }

    public ItemLR0(Rule rule) {
        this.rule = rule;
    }

    public ItemLR0(String leftSide, List<String> rightSide, int dotPointer) {
        this.rule = new Rule(leftSide, rightSide);
        this.dotPointer = dotPointer;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(rule.getLhs() + " -> ");

        for(int i=0; i<rule.getRhs().size(); i++){
            if (i == dotPointer)
                result.append(". ");
            result.append(rule.getRhs().get(i) + " ");
        }

        if (rule.getRhs().size() == dotPointer)
            result.append(".");

        return result.toString();
    }


    public int getDotPointer() {
        return dotPointer;
    }

    boolean goTo() {
        if (dotPointer >= rule.getRhs().size())
            return false;

        dotPointer++;
        return true;
    }

    String getCurrentTerminal() {
        if (dotPointer == rule.getRhs().size())
            return null;

        return rule.getRhs().get(dotPointer);
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setDotPointer(int dotPointer) {
        this.dotPointer = dotPointer;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
