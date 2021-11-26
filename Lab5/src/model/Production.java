package model;

import java.util.List;

public class Production {
    private List<String> symbolList;
    private List<List<String>> ruleList;

    public Production(List<String> symbolList, List<List<String>> ruleList) {
        this.symbolList = symbolList;
        this.ruleList = ruleList;
    }

    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }

    public List<List<String>> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<List<String>> ruleList) {
        this.ruleList = ruleList;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        symbolList.forEach(item -> stringBuilder.append(item).append(" "));
        stringBuilder.append("-> ");
        ruleList.forEach((item) -> { item.forEach (rule -> stringBuilder.append(rule).append(" ")); });
        return  stringBuilder.toString();
    }
}
