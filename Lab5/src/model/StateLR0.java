package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class StateLR0 {
    LinkedHashSet<ItemLR0> items;
    HashMap<String, StateLR0> transition;

    public StateLR0(LinkedHashSet<ItemLR0> items, HashMap<String, StateLR0> transition) {
        this.items = items;
        this.transition = transition;
    }

    public StateLR0(Grammar grammar, HashSet<ItemLR0> itemLR0s){
        this.items = new LinkedHashSet<>(itemLR0s);
        transition = new HashMap<>();
        closure(grammar);
    }

    public LinkedHashSet<ItemLR0> getItems() {
        return items;
    }

    public void setItems(LinkedHashSet<ItemLR0> items) {
        this.items = items;
    }

    public HashMap<String, StateLR0> getTransition() {
        return transition;
    }

    public void setTransition(HashMap<String, StateLR0> transition) {
        this.transition = transition;
    }

    public void addTransition(String symbol, StateLR0 state){
        transition.put(symbol, state);
    }

    private void closure(Grammar grammar){
        boolean flag = false;
        do {
            flag = false;
            HashSet<ItemLR0> temporaryItems = new HashSet<>();

            for (ItemLR0 item: this.items){
                if (item.getCurrentTerminal() != null){
                    HashSet<Rule> rules = grammar.getRulesBySymbol(item.getCurrentTerminal());
                    temporaryItems.addAll(createItem(rules));
                }
            }
            boolean condition = !this.items.containsAll(temporaryItems);
            // todo check manually if the temp items exist in this.items since this returns true all the time
            if (condition){
                this.items.addAll(temporaryItems);
                flag = true;
            }

        } while (flag);
    }

    private HashSet<ItemLR0> createItem(HashSet<Rule> rules){
        HashSet<ItemLR0> results = new HashSet<>();
        for (Rule rule: rules){
            results.add(new ItemLR0(rule));
        }

        return results;
    }

}
