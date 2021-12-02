package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StateLR0 {
    ArrayList<ItemLR0> items;
    HashMap<String, ItemLR0> transition;

    public StateLR0(ArrayList<ItemLR0> items, HashMap<String, ItemLR0> transition) {
        this.items = items;
        this.transition = transition;
    }

    public ArrayList<ItemLR0> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemLR0> items) {
        this.items = items;
    }

    public HashMap<String, ItemLR0> getTransition() {
        return transition;
    }

    public void setTransition(HashMap<String, ItemLR0> transition) {
        this.transition = transition;
    }

    private void addTransition(String symbol, ItemLR0 state){
        transition.put(symbol, state);
    }

    private void closure(Grammar grammar){
        do {
            HashSet<ItemLR0> temporaryItems = new HashSet<>();

            for (ItemLR0 item: this.items){
                if (item.getCurrentTerminal() != null){
                    HashSet<Rule> rules = grammar.getRulesBySymbol(item.getCurrentTerminal());
                    temporaryItems.addAll(createItem(rules));
                }
            }

            if (!this.items.containsAll(temporaryItems))
                this.items.addAll(temporaryItems);
            else break;

        } while (true);
    }

    private HashSet<ItemLR0> createItem(HashSet<Rule> rules){
        HashSet<ItemLR0> results = new HashSet<>();
        for (Rule rule: rules){
            results.add(new ItemLR0(rule));
        }

        return results;
    }

}
