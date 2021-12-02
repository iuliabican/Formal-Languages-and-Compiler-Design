package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ParserLR0 {

    private ArrayList<StateLR0> canonicalCollection;
    private Grammar grammar;
    protected HashMap<String, Integer>[] goToTable;
    protected HashMap<String, Action>[] actionTable;

    public ParserLR0(ArrayList<StateLR0> canonicalCollecion, Grammar grammar) {
        this.canonicalCollection = canonicalCollecion;
        this.grammar = grammar;
    }

    public ParserLR0(Grammar grammar) {
        this.grammar = grammar;
    }

    public ArrayList<StateLR0> getCanonicalCollection() {
        return canonicalCollection;
    }

    public void setCanonicalCollection(ArrayList<StateLR0> canonicalCollection) {
        this.canonicalCollection = canonicalCollection;
    }

    public boolean parse(){
        // todo
        return true;
    }

    private boolean createActionTableForLR0() {
        actionTable = new HashMap[canonicalCollection.size()];
        for (int i = 0; i < goToTable.length; i++) {
            actionTable[i] = new HashMap<>();
        }
//        for (int i = 0; i < canonicalCollection.size(); i++) {
//            for (String s : canonicalCollection.get(i).getTransition().keySet()) {
//                if (grammar.getSetOfTerminals().contains(s)) {
//                    actionTable[i].put(s, new Action(ActionType.SHIFT, findStateIndex(canonicalCollection.get(i).getTransition().get(s))));
//                }
//            }
//        }
        for (int i = 0; i < canonicalCollection.size(); i++) {
            for (ItemLR0 item : canonicalCollection.get(i).getItems()) {
                if (item.getDotPointer() == item.getRule().getRhs().size()) {
                    if (item.getRule().getRhs().equals("S'")) {
                        actionTable[i].put("$", new Action(ActionType.ACCEPT, 0));
                    } else {
                        HashSet<String> terminals = (HashSet<String>) grammar.getSetOfTerminals();
                        terminals.add("$");
                        Rule rule = new Rule(item.getRule().getLhs(), item.getRule().getRhs());
                        //int index = grammar.findRuleIndex(rule); //todo
                        //Action action = new Action(ActionType.REDUCE, index);
//                        for (String str : terminals) {
//                            if (actionTable[i].get(str) != null) {
//                                System.out.println("it has a REDUCE-" + actionTable[i].get(str).getType() + " confilct in state " + i);
//                                return false;
//                            } else {
//                                actionTable[i].put(str, action);
//                            }
//                        }
                    }
                }
            }
        }
        return true;
    }

    private int findStateIndex(StateLR0 state) {
        for (int i = 0; i < this.canonicalCollection.size(); i++) {
            if (canonicalCollection.get(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }
}
