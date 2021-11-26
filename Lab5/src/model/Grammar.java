package model;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grammar {
    private List<String> setOfNonTerminals;
    private List<String> setOfTerminals;
    private String startingSymbol;
    private String fileName;
    private List<Production> setOfProductions;
    private Boolean cfgFlag = true;

    public Grammar(String fileName) {
        this.fileName = fileName;
        this.setOfNonTerminals = new ArrayList<String>();
        this.setOfTerminals = new ArrayList<String>();
        this.setOfProductions = new ArrayList<>();
        this.startingSymbol = "";
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
            List<String> symbols = new ArrayList<>();


            for (String rule: rightHandSide.split("\\|")){  //  ["a b", "b C s"]
                ArrayList<String> items = new ArrayList<>();
                for (String item: rule.split(" ")){         // ["a", "b"]
                    items.add(item);
                }
                rules.add(items);
            }

            symbols.addAll(Arrays.asList(leftHandSide.split(" ")));

            if(symbols.size()>1) // if there isn't a single symbol on the left-hand side
                cfgFlag = false;

            setOfProductions.add(new Production(symbols, rules));
        }

        // Starting Symbol
        this.startingSymbol = scanner.nextLine();

        scanner.close();
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
