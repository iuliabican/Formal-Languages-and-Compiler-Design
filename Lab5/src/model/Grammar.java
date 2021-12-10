package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grammar {
    private List<String> setOfNonTerminals;
    private List<String> setOfTerminals;
    private List<Rule> rules;
    private String startingSymbol;
    private String fileName;
    private List<Production> setOfProductions;
    private Boolean cfgFlag = true;
    List<String> symbols;

    public Grammar(String fileName) {
        this.fileName = fileName;
        this.setOfNonTerminals = new ArrayList<String>();
        this.setOfTerminals = new ArrayList<String>();
        this.setOfProductions = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.startingSymbol = "";
        this.symbols = new ArrayList<>();
    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File(this.fileName);
        Scanner scanner = new Scanner(file);

        // Set of Non-Terminals
        String setOfNonTerminalsText = scanner.nextLine();
        String setOfNonTerminals = scanner.nextLine();
        this.setOfNonTerminals = Arrays.asList(setOfNonTerminals.split(","));

        // Set of Terminals
        String setOfTerminalsText = scanner.nextLine();
        String setOfTerminals = scanner.nextLine();
        this.setOfTerminals = Arrays.asList(setOfTerminals.split(","));

        // Productions
        String productionsText = scanner.nextLine();
        String line = "";

        //  As long as we have productions, we should read them
        while (true) {
            line = scanner.nextLine();
            if (line.equals("STARTING SYMBOL")) {
                break;
            }

            var leftHandSide = line.split("->")[0];
            var rightHandSide = line.split("->")[1];

            List<List<String>> rules = new ArrayList<>();

            this.symbols.addAll(Arrays.asList(leftHandSide.split(" ")));


            for (String rule: rightHandSide.split("\\|")){  //  ["a b", "b C s"]
                ArrayList<String> items = new ArrayList<>();
                for (String item: rule.split(" ")){         // ["a", "b"]
                    items.add(item);
                }
                Rule ruleObject = new Rule(symbols.get(symbols.size()-1), items);
                this.rules.add(ruleObject);
                rules.add(items);
            }


            if(symbols.size()>1) // if there isn't a single symbol on the left-hand side
                cfgFlag = false;

            setOfProductions.add(new Production(symbols, rules));
        }

        // Starting Symbol
        this.startingSymbol = scanner.nextLine();
        this.rules.add(0,new Rule("$", List.of(startingSymbol)));
        scanner.close();
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public HashSet<Rule> getRulesBySymbol(String symbol){
        HashSet<Rule> rulesBySymbol = new HashSet<>();
        for (Rule rule: this.rules){
            if (rule.getLhs().equals(symbol)){
                rulesBySymbol.add(rule);
            }
        }
        return rulesBySymbol;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public int findRuleIndex(Rule rule){
        return this.rules.indexOf(rule);
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<String> getSetOfNonTerminals() {
        return setOfNonTerminals;
    }

    public void setSetOfNonTerminals(List<String> setOfNonTerminals) {
        this.setOfNonTerminals = setOfNonTerminals;
    }

    public List<String> getSetOfTerminals() {
        return setOfTerminals;
    }

    public void setSetOfTerminals(List<String> setOfTerminals) {
        this.setOfTerminals = setOfTerminals;
    }

    public List<Production> getSetOfProductions() {
        return  setOfProductions;
    }

    public void setSetOfProductions(List<Production> setOfProductions) { this.setOfProductions = setOfProductions; }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean isCFG(){return cfgFlag;}
}
