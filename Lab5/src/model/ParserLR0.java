package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ParserLR0 {

    private ArrayList<StateLR0> canonicalCollection;
    private Grammar grammar;
    private HashMap<String, Integer>[] goToTable;
    private HashMap<String, Action>[] parsingTable;

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

    public void parse(){
        this.createStates();
//        this.createGoToTable();
//        return this.createParsingTable();
    }

    private void createStates(){
        this.canonicalCollection = new ArrayList<>();
        HashSet<ItemLR0> start = new HashSet<>();
        start.add(new ItemLR0(grammar.getRules().get(0)));

        StateLR0 startState = new StateLR0(grammar, start);
        this.canonicalCollection.add(startState);

        for (int i=0; i<canonicalCollection.size(); i++){
            HashSet<String> stringWithDot = new HashSet<>();

            for (ItemLR0 itemLR0: this.canonicalCollection.get(i).getItems()){
                if (itemLR0.getCurrentTerminal() != null){
                    stringWithDot.add(itemLR0.getCurrentTerminal());
                }
            }

            for (String str: stringWithDot){
                HashSet<ItemLR0> nextStateItems = new HashSet<>();

                for (ItemLR0 itemLR0: this.canonicalCollection.get(i).getItems()){
                    if (itemLR0.getCurrentTerminal() != null && itemLR0.getCurrentTerminal().equals(str)){
                        ItemLR0 temporaryItem = new ItemLR0(itemLR0);
                        temporaryItem.goTo();
                        nextStateItems.add(temporaryItem);
                    }
                }

                StateLR0 nextState = new StateLR0(grammar, nextStateItems);
                boolean exists = false;
                for (int j = 0; j < canonicalCollection.size(); j++) {
                    if (canonicalCollection.get(j).getItems().containsAll(nextState.getItems())
                            && nextState.getItems().containsAll(canonicalCollection.get(j).getItems())) {
                        exists = true;
                        canonicalCollection.get(i).addTransition(str, canonicalCollection.get(j));
                    }
                }
                if (!exists) {
                    canonicalCollection.add(nextState);
                    canonicalCollection.get(i).addTransition(str, nextState);
                }
            }

        }
    }

    private void createGoToTable(){
        this.goToTable = new HashMap[canonicalCollection.size()];

        for (int i = 0; i < goToTable.length; i++) {
            goToTable[i] = new HashMap<>();
        }
    }

//    private boolean createParsingTable() {
//        parsingTable = new HashMap[canonicalCollection.size()];
//        for (int i = 0; i < goToTable.length; i++) {
//            parsingTable[i] = new HashMap<>();
//        }
//
//        for (int i = 0; i < canonicalCollection.size(); i++) {
//            for (String s : canonicalCollection.get(i).getTransition().keySet()) {
//                if (grammar.getSetOfTerminals().contains(s)) {
//                    parsingTable[i].put(s, new Action(ActionType.SHIFT, findStateIndex(canonicalCollection.get(i).getTransition().get(s))));
//                }
//            }
//        }
//
//        for (int i = 0; i < canonicalCollection.size(); i++) {
//            for (ItemLR0 item : canonicalCollection.get(i).getItems()) {
//                if (item.getDotPointer() == item.getRule().getRhs().size()) {
//                    if (item.getRule().getRhs().equals("S'")) {
//                        parsingTable[i].put("$", new Action(ActionType.ACCEPT, 0));
//                    } else {
//                        HashSet<String> terminals = (HashSet<String>) grammar.getSetOfTerminals();
//                        terminals.add("$");
//                        Rule rule = new Rule(item.getRule().getLhs(), item.getRule().getRhs());
//                        //int index = grammar.findRuleIndex(rule); //todo
//                        //Action action = new Action(ActionType.REDUCE, index);
////                        for (String str : terminals) {
////                            if (actionTable[i].get(str) != null) {
////                                System.out.println("it has a REDUCE-" + actionTable[i].get(str).getType() + " confilct in state " + i);
////                                return false;
////                            } else {
////                                actionTable[i].put(str, action);
////                            }
////                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }

    private int findStateIndex(StateLR0 state) {
        for (int i = 0; i < this.canonicalCollection.size(); i++) {
            if (canonicalCollection.get(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }
}
